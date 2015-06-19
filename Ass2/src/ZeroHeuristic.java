import java.util.*;

/**
 * Implements the zero heuristic, which always estimates that the remaining
 * distance between the current state and the goal state is 0.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class ZeroHeuristic implements Heuristic {

	@Override
	/**
	 * Always estimates that the distance between the current state and the goal state is 0.
	 */
	public int distanceLeft(Node n, HashSet<Edge> edgesLeft, Graph g) {
		return 0;
	}

}
