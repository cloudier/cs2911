import java.util.*;

/**
 * Implements an expansion strategy that chooses a required path (or flight)
 * that is connected to the current node if it exists. Otherwise, it creates a
 * state to the beginning node of a required path and creates a subsequent state
 * to the end node of the required path. This second state is added to the list
 * of states that are returned.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class SkipExpansion implements Expansion {
	@Override
	public List<State> nextStates(State s, Heuristic h, Graph gr) {
		List<State> states = new ArrayList<State>();
		State n = null;
		State m = null;
		int g, estimate;

		// Indicates if there are required edges connected to this node.
		boolean areEdgesConnected = false;

		// Add required paths connected to the current node if they exist.
		for (Edge e : s.getEdgesLeft()) {
			if (e.from().equals(s.getNode())) {
				areEdgesConnected = true;
				HashSet<Edge> newEdgesLeft = new HashSet<Edge>(s.getEdgesLeft());
				newEdgesLeft.remove(e);
				estimate = h.distanceLeft(e.to(), newEdgesLeft, gr);
				
				// Ignore delay if this is the last node.
				if (newEdgesLeft.isEmpty()) {
					g = s.g() + e.getWeight();
				} else {
					g = s.g() + e.getWeight() + e.to().getDelay();
				}
				
				n = new State(newEdgesLeft, s, g, estimate, e.to());
				states.add(n);
			}
		}

		// If no required paths are connected to the current node,
		// add the first node of all the required paths.

		if (!areEdgesConnected) {
			for (Edge e : s.getEdgesLeft()) {
				HashSet<Edge> newEdgesLeft = new HashSet<Edge>(s.getEdgesLeft());
				// Create state to the first node of the required path e
				estimate = h.distanceLeft(e.from(), newEdgesLeft, gr);
				g =  s.g() + s.getNode().distanceTo(e.from()) + e.from().getDelay();
				n = new State(newEdgesLeft, s, g, estimate, e.from());
				
				// Create second state to the final node of the required path e
				newEdgesLeft.remove(e);
				estimate = h.distanceLeft(e.from(), newEdgesLeft, gr);
				
				// Ignore delay if this is the last node
				if (newEdgesLeft.isEmpty()) {
					g = n.g() + e.getWeight();
				} else {
					g = n.g() + e.getWeight() + e.to().getDelay();
				}
				
				m = new State(newEdgesLeft, n, g, estimate, e.to());
				states.add(m);
			}
		}

		return states;
	}
}
