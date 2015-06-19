import java.util.*;

/**
 * States store the current node (airport) of the search as well as the
 * remaining edges (or flights) to traverse and the previous states used to
 * reach this state from the initial state. It is associated with a cost
 * determined by adding the weights and delays at previous nodes (airports).
 * 
 * @author Claudia Tu z3459448
 *
 */
public class State implements Comparable<State> {

	private HashSet<Edge> edgesLeft;
	private State prevState;
	private int f;
	private int g;
	private Node node;

	/**
	 * Creates a new State.
	 * 
	 * @param edgesLeft
	 *            <code>HashSet</code> of edges (or flights) that need to be
	 *            traversed.
	 * @param prevState
	 *            The parent state of the current state, or <code>null</code> if
	 *            this is the initial state.
	 * @param g
	 *            The cost of all states up to this state.
	 * @param h
	 *            A heuristic object used to estimate the distance left.
	 * @param curr
	 *            The current node of this state.
	 */
	public State(HashSet<Edge> edgesLeft, State prevState, int g, int h,
			Node curr) {
		this.edgesLeft = edgesLeft;
		this.prevState = prevState;
		this.f = g + h;
		this.g = g;
		this.node = curr;
	}

	/**
	 * Returns a <code>HashSet</code> of <code>Edge</code>s that need to be
	 * traversed.
	 * 
	 * @return <code>HashSet</code> of <code>Edge</code>s that need to be
	 *         traversed.
	 */
	public HashSet<Edge> getEdgesLeft() {
		return edgesLeft;
	}

	/**
	 * Returns the parent state of this state.
	 * 
	 * @return The parent state of this state.
	 */
	public State getPrevState() {
		return prevState;
	}

	/**
	 * Returns <i>f</i>, the cost of this state. The cost is calculated by
	 * summing the costs of all previous states and the estimated cost left,
	 * that is <i>f = g + h</i>, where <i>g</i> is the cumulative cost of all
	 * previous states and <i>h</i> is the estimated cost left.
	 * 
	 * @return <i>f</i>, the cost of this state.
	 */
	public int f() {
		return f;
	}

	/**
	 * Returns <i>g</i>, the cumulative cost of all previous states.
	 * 
	 * @return <i>g</i>, the cumulative cost of all previous states.
	 */
	public int g() {
		return g;
	}

	/**
	 * Returns the node of the current state.
	 * 
	 * @return The node of the current state.
	 */
	public Node getNode() {
		return node;
	}

	/**
	 * Used to order states by cost (<i>f</i>) in the A* PriorityQueue.
	 */
	@Override
	public int compareTo(State o) {
		if (this.f > o.f)
			return 1;
		if (this.f == o.f)
			return 0;
		if (this.f < o.f)
			return -1;
		return 0;
	}

	/**
	 * Returns true if this state has the same edgesLeft and node, and false
	 * otherwise.
	 * 
	 * @return True if this state has the same edgesLeft and node, and false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof State))
			return false;
		State s = (State) o;
		if (this.edgesLeft.equals(s.getEdgesLeft())
				&& this.node.equals(s.getNode()))
			return true;
		return false;
	}

	/**
	 * Returns a hash code value for the state. Based on the edges left and
	 * current node of the state.
	 * 
	 * @return The hash code value for the state.
	 */
	@Override
	public int hashCode() {
		int hash = 5;
		hash += edgesLeft.hashCode() * 7;
		hash += node.hashCode() * 11;
		return hash;
	}
}
