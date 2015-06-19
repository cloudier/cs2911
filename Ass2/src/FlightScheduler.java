import java.io.*;
import java.util.*;

/**
 * Assignment 2 - COMP2911.
 * 
 * This program implements a state space search. The initial state is in Sydney
 * with a given number of required flights. The goal state can occur in any
 * node, but all the required flights must have been traversed. The path cost is
 * the delay at each node (excluding the first and last nodes). At each node,
 * you are able to move to any other node.
 * 
 * The running time of this program can be improved in two main ways.
 * 
 * 1: Heuristic. Changing the way the heuristic is calculated reduces the number
 * of states the program needs to search by appropriately sorting the states in
 * the PriorityQueue. As a result, the program will be able to expand the
 * least-cost path earlier, reducing the total number of states it needs to
 * expand and hence increasing the speed of the program. However, the heuristic
 * must remain admissible so that the search will find the lowest cost path in
 * all cases.
 * 
 * My heuristic is implemented in the BasicHeuristic class. It sums the
 * remaining weights of remaining required edges. This heuristic is admissible
 * because we can be certain that the weights of an edge will definitely be in
 * the total cost (of weights and delays) when the search has completed, i.e. it
 * will never overestimate the cost from the current state to the goal state.
 * 
 * In addition, this heuristic is consistent. In consistent heuristics, the
 * estimated distance from the current node to the goal is less than or equal to
 * the known distance to any neighbour node plus the estimated distance from
 * that neighbour to the goal. In other words,
 * <tt>EstimatedDistance(node, goal) <=
 * Distance(neighbour) + EstimatedDistance(neighbour, goal)</tt>. The distance
 * between a given node and its neighbour equals the weight of the edge (length
 * of the flight) plus the delay at the current node. However, I do not include
 * delay in my heuristic estimate. As a result, our estimated distance will
 * always be less than the distance between the current node and its neighbour
 * plus the estimated distance from the neighbour to the goal unless we are at
 * the last node.
 * 
 * The running time of this heuristic is O(E) where E is the number of required
 * flights. The running time is more accurately described as a function of the
 * remaining edges at a given state, so in the first state you would sum through
 * all the required flights.
 * 
 * 2: Expansion. Carefully choosing which states to expand can reduce the number
 * of expansions hence making the program faster. We can use a visited set to
 * ignore any states we have already visited. Since the heuristic I use is
 * consistent, identical states that are pushed onto the PriorityQueue later
 * always have a higher cost.
 * 
 * My expansion is implemented in the SkipExpansion class.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class FlightScheduler {

	/**
	 * Takes in a text file that lists the airports, the delays incurred at each
	 * airport, the travel time between each pair of airports and the required
	 * flights. Then it performs an A* search to determine the lowest cost
	 * sequence of flights between the airports that covers all the required
	 * flights. This cost and the number of nodes expanded is printed out, as
	 * well as the sequence of flights.
	 * 
	 * @param args
	 *            [0] Location of text file.
	 */
	public static void main(String[] args) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(args[0]));

			Graph g = new Graph();
			HashSet<Edge> requiredEdges = new HashSet<Edge>();
			Heuristic hb = new BasicHeuristic();
			Expansion str = new SkipExpansion();

			// Parse inputs
			for (String line = reader.readLine(); line != null; line = reader
					.readLine()) {
				String[] inputs = line.split(" ");
				if (inputs[0].equals("City")) {
					/*
					 * Creates a new airport with the name CityName. CityDelay
					 * indicates the delay (in minutes) incurred at this airport
					 * before a plane can fly to another airport. Format: City
					 * CityName CityDelay
					 */
					g.addNode(inputs[1], Integer.parseInt(inputs[2]));
				} else if (inputs[0].equals("Time")) {
					/*
					 * Creates a new flight between DepartingCity and
					 * DestinationCity. This flight takes TravelTime minutes.
					 * Format: Time DepartingCity DestinationCity TravelTime
					 */
					g.addEdge(inputs[1], inputs[2], Integer.parseInt(inputs[3]));
				} else if (inputs[0].equals("Flight")) {
					/*
					 * Creates a new required flight between DepartingCity and
					 * DestinationCity. Format: Flight DepartingCity
					 * DestinationCity
					 */
					requiredEdges.add(g.getEdge(inputs[1], inputs[2]));
				}
			}

			FlightScheduler fs = new FlightScheduler();
			fs.search(requiredEdges, str, g, hb);

			// Get the Java runtime
		    Runtime runtime = Runtime.getRuntime();
		    // Run the garbage collector
		    runtime.gc();
		    // Calculate the used memory
		    long memory = runtime.totalMemory() - runtime.freeMemory();
		    System.out.println("Used memory is bytes: " + memory);
		    System.out.println("Used memory is megabytes: "
		        + bytesToMegabytes(memory));
			
			reader.close();
		} catch (Exception e) {
			System.err.format("Exception while trying to read '%s'.", args[0]);
			e.printStackTrace();
		}
	}

	/**
	 * This function implements an A* search using a PriorityQueue. The search
	 * is a state space search. In the initial state, our plane starts in Sydney
	 * airport. In the goal state, there are no required flights left to take.
	 * These required flights are passed in as a HashSet of edges. Airports are
	 * represented as nodes and flights are represented as edges in the Graph.
	 * The search also takes in a strategy used to expand States and a heuristic
	 * used to estimate the cost left until the goal state. In addition, this
	 * search stores a set of visited states and checks that a state has not
	 * already been visited before checking its neighbours. We do not need to
	 * check if a newer state has a lower cost because the heuristic used is
	 * consistent, that is, it only increases as the search reaches the goal
	 * state.
	 * 
	 * @param requiredEdges
	 *            The edges that need to be traversed.
	 * @param str
	 *            The strategy to use to expand States.
	 * @param g
	 *            The graph representation of the airports.
	 * @param h
	 *            The heuristic to use to estimate the cost remaining until the
	 *            goal state.
	 */
	public void search(HashSet<Edge> requiredEdges, Expansion str, Graph g,
			Heuristic h) {
		PriorityQueue<State> pq = new PriorityQueue<State>();
		HashSet<State> visited = new HashSet<State>();

		int numNodes = 0;
		State curr = null;
		State syd = new State(requiredEdges, null, 0, h.distanceLeft(
				g.getNode("Sydney"), requiredEdges, g), g.getNode("Sydney"));
		pq.add(syd);

		while (!pq.isEmpty()) {
			curr = pq.poll();
			if (visited.contains(curr)) {
				continue;
			}
			visited.add(curr);
			numNodes++;
			if (curr.getEdgesLeft().isEmpty()) {
				break;
			}

			List<State> states = str.nextStates(curr, h, g);
			for (State s : states) {
				if (!visited.contains(s)) {
					pq.add(s);
				}
			}
		}

		System.out.println(numNodes + " nodes expanded");
		System.out.println("cost = " + curr.f());

		// Print flights
		List<Edge> routes = new ArrayList<Edge>();
		while (curr != null) {
			if (curr.getPrevState() != null) {
				routes.add(0, g.getEdge(curr.getPrevState().getNode(),
						curr.getNode()));
			}
			curr = curr.getPrevState();
		}
		for (Edge e : routes) {
			System.out.printf("Flight %s to %s\n", e.from().getName(), e.to()
					.getName());
		}
	}
	
	private static final long MEGABYTE = 1024L * 1024L;

	public static long bytesToMegabytes(long bytes) {
		return bytes / MEGABYTE;
	}
}
