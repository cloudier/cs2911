import java.util.*;

/**
 * Estimates the distance left by summing the weights of the remaining edges and
 * the minimum distance between directed paths. (See UML.pdf for more details.)
 * BasicHeuristic was used in the FlightScheduler because it ran considerably
 * faster upon testing even though it resulted in a greater number of expanded
 * nodes. This is because our inputs generally have a low number of sets.
 */
public class SetsHeuristic implements Heuristic {

	@Override
	public int distanceLeft(Node n, HashSet<Edge> edgesLeft, Graph g) {
		int totalWeight = 0;
		int setWeight = 0;

		HashSet<Node> checkSets = new HashSet<Node>();
		for (Edge e : edgesLeft) {
			// Put all starting nodes of each edge in HashSet
			checkSets.add(e.from());
			// Sum total weights of remaining edges
			totalWeight += e.getWeight();
		}

		// Remove all end nodes of each edge in HashSet
		for (Edge e : edgesLeft) {
			if (checkSets.contains(e.to()))
				checkSets.remove(e.to());
		}
		/*
		 * At this point, checkSets contains only the start nodes of each
		 * directed path. It excludes any start nodes in directed cycles
		 */

		/*
		 * Find smallest distance from the end node of each edge to all start
		 * nodes
		 */
		int minDist = Integer.MAX_VALUE;
		for (Edge e : edgesLeft) {
			for (Node f : checkSets) {
				if (g.getEdge(e.to(), f) != null
						&& g.getEdge(e.to(), f).getWeight() < minDist) {
					minDist = g.getEdge(e.to(), f).getWeight();
				}
			}
		}

		if (minDist == Integer.MAX_VALUE) {
			minDist = 0;
		}

		if (checkSets.contains(n)) {
			/*
			 * If the node we are currently at is in checkSets, it is a starting
			 * node. Therefore, we don't need to jump to the set we are
			 * currently at again.
			 */
			setWeight = (checkSets.size() - 1) * minDist;
		} else {
			/*
			 * If the node we are currently at is not in checkSets, it is not a
			 * starting node. Therefore, we need to jump to the set we are
			 * currently at again.
			 */
			setWeight = checkSets.size() * minDist;
		}
		return totalWeight + setWeight;
	}
}
