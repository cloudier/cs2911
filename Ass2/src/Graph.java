import java.util.*;

/**
 * This graph provides a representation of the airports, their delays and the
 * length of the flights between airports. Airports are represented using nodes
 * while flights are represented using edges.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class Graph {
	private HashMap<String, Node> nodes;
	private HashSet<Edge> edges;

	/**
	 * Creates a new empty graph.
	 */
	public Graph() {
		this.nodes = new HashMap<String, Node>();
		this.edges = new HashSet<Edge>();
	}

	/**
	 * Adds a node to the graph. The node represents an airport. The name
	 * parameter represents the name of the airport, for example "Sydney". The
	 * delay parameter is the delay experienced in minutes at the airport before
	 * a plane can leave for another airport.
	 * 
	 * @param name
	 *            The name of the node as a string.
	 * @param delay
	 *            The delay experienced at the node as an int.
	 */
	public void addNode(String name, int delay) {
		Node n = new Node(name, delay);
		nodes.put(name, n);
	}

	/**
	 * Returns the node (or airport) with the given name.
	 * 
	 * @param name
	 *            The name of the node as a string.
	 * @return The corresponding node.
	 */
	public Node getNode(String name) {
		return nodes.get(name);
	}

	/**
	 * Returns all the nodes from this graph as a HashMap.
	 * 
	 * @return The nodes from this graph as a HashSet.
	 */
	public HashMap<String, Node> getNodes() {
		return new HashMap<String, Node>(nodes);
	}

	/**
	 * Adds an edge to the graph. The edge represents a flight between two given
	 * airports. The weight of the edge is the length of the flight in minutes.
	 * 
	 * @param from
	 *            The name of the node that the edge starts from as a string.
	 * @param to
	 *            The name of the node that the edge ends at as a string.
	 * @param weight
	 *            The weight of the edge as an int.
	 */
	public void addEdge(String from, String to, Integer weight) {
		Node fromNode = nodes.get(from);
		Node toNode = nodes.get(to);

		fromNode.addConnection(toNode, weight);
		toNode.addConnection(fromNode, weight);

		Edge e = new Edge(fromNode, toNode, weight);
		edges.add(e);

		e = new Edge(toNode, fromNode, weight);
		edges.add(e);
	}

	/**
	 * Returns the edge between the two nodes which are identified using their
	 * names, or the flight between two airports.
	 * 
	 * @param from
	 *            The name of the node at which the edge begins as a string.
	 * @param to
	 *            The name of the node at which the edge ends as a string.
	 * @return The edge between the nodes corresponding to the given names.
	 */
	public Edge getEdge(String from, String to) {
		for (Edge e : edges) {
			if (e.from().getName().equals(from) && e.to().getName().equals(to)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Returns the edge between two given nodes, or the flight between two
	 * airports.
	 * 
	 * @param from
	 *            The node at which the edge begins.
	 * @param to
	 *            The node at which the edge ends.
	 * @return The edge between the given nodes.
	 */
	public Edge getEdge(Node from, Node to) {
		for (Edge e : edges) {
			if (e.from().equals(from) && e.to().equals(to)) {
				return e;
			}
		}
		return null;
	}

	/**
	 * Returns all the edges from this graph as a HashSet.
	 * 
	 * @return All the edges from this graph as a HashSet.
	 */
	public HashSet<Edge> getEdges() {
		return new HashSet<Edge>(edges);
	}

	/**
	 * Returns all the edges from a given node as a HashSet.
	 * 
	 * @param n
	 *            The node to find edges from.
	 * @return All the edges from the given node as a HashSet.
	 */
	public HashSet<Edge> getEdgesFrom(Node n) {
		HashSet<Edge> edgesFrom = new HashSet<Edge>();
		for (Edge e : edges) {
			if (e.from().equals(n))
				edgesFrom.add(e);
		}
		return edgesFrom;
	}
}