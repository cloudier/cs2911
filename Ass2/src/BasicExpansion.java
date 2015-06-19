import java.util.*;

/**
 * Implements a basic expansion strategy that chooses a required path (or
 * flight) that is connected to the current node if it exists. Otherwise, it
 * adds states to the first node of all required paths.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class BasicExpansion implements Expansion {

	@Override
	public List<State> nextStates(State s, Heuristic h, Graph gr) {
		List<State> states = new ArrayList<State>();
		State n = null;
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
				estimate = h.distanceLeft(e.from(), newEdgesLeft, gr);
				g = s.g() + s.getNode().distanceTo(e.from()) + e.from().getDelay();
				
				n = new State(newEdgesLeft, s, g, estimate, e.from());
				states.add(n);
			}
		}

		return states;
	}
}
