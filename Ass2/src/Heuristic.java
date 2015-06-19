import java.util.*;

/**
 * Interface used to encapsulate different methods of estimating the distance
 * between a given state and the goal state.
 * 
 * @author Claudia Tu z3459448
 *
 */
public interface Heuristic {
	/**
	 * Returns an estimate of the cost left between the current state and the
	 * goal state.
	 * 
	 * @param edgesLeft
	 *            A HashSet of edges left to traverse.
	 * @return An integer representing the estimated cost left between the
	 *         current state and the goal state.
	 */
	public int distanceLeft(Node n, HashSet<Edge> edgesLeft, Graph g);
}
