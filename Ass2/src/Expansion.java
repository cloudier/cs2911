import java.util.*;

/**
 * Interface used to encapsulate different methods of expanding nodes.
 * @author Claudia Tu z3459448
 *
 */
public interface Expansion {

	/**
	 * Returns the possible subsequent states from the given state. The concrete
	 * strategy determines which states are added and which states are ignored.
	 * 
	 * @param s
	 *            The state to calculate possible subsequent states from.
	 * @return A list of possible subsequent states.
	 */
	public List<State> nextStates(State s, Heuristic h, Graph gr);
}
