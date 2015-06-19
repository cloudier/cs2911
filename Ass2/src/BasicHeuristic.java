import java.util.*;

/**
This heuristic is admissible because we can be certain that the weights of an
 * edge will definitely be in the total cost (of weights and delays) when the
 * search has completed, i.e. it will never overestimate the cost from the
 * current state to the goal state.
 * 
 * In addition, this heuristic is consistent. In consistent heuristics, the
 * estimated distance from the current node to the goal is less than or equal to
 * the known distance to any neighbour node plus the estimated distance from
 * that neighbour to the goal. In other words,
 * <tt>EstimatedDistance(node, goal) <=
 * Distance(neighbour) + EstimatedDistance(neighbour, goal)</tt>. The distance
 * between a given node and its neighbour equals the weight of the edge (length
 * of the flight) plus the delay at the current node. However, I do not include
 * delay in my heuristic estimate. As a result, our estimated
 * distance will always be less than the distance between the current node and its
 * neighbour plus the estimated distance from the neighbour to the goal unless
 * we are at the last node.
 * 
 * The running time of this heuristic is O(E) where E is the number of remaining
 * edges for a given state.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class BasicHeuristic implements Heuristic {

	@Override
	/**
	 * Estimates the distance left by summing the weights of the remaining edges.
	 */
	public int distanceLeft(Node n, HashSet<Edge> edgesLeft, Graph g) {
		int totalWeight = 0;
		for (Edge e : edgesLeft) {
			totalWeight += e.getWeight();
		}
		return totalWeight;
	}
}