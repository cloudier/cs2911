/**
 * Edges represent the flights between airports. The from node is the airport
 * that the flight departs from and the to node is the airport that the flight
 * arrives at. The weight of the edge is the length of the flight in minutes (as
 * provided by the input).
 * 
 * @author Claudia Tu z3459448
 *
 */
public class Edge {
	private Node from;
	private Node to;
	private int weight;

	/**
	 * Makes a new directed edge.
	 * 
	 * @param from
	 *            The node (airport) that the edge (flight) starts from.
	 * @param to
	 *            The node that the edge ends at.
	 * @param weight
	 *            The weight of the edge, or length of the flight in minutes.
	 */
	public Edge(Node from, Node to, int weight) {
		this.from = from;
		this.to = to;
		this.weight = weight;
	}

	/**
	 * Returns the node that the edge starts from, or the airport that the
	 * flight departs from.
	 * 
	 * @return The node that the edge starts from.
	 */
	public Node from() {
		return from;
	}

	/**
	 * Returns the node that the edge ends at, or the airport that the flight
	 * arrives at.
	 * 
	 * @return
	 */
	public Node to() {
		return to;
	}

	/**
	 * Returns the weight of the edge, or the flight time in minutes.
	 * 
	 * @return Returns the weight of the edge.
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * Returns true if two edges have the start and end nodes, and false
	 * otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
		if (!(o instanceof Edge)) return false;
		Edge e = (Edge) o;
		if (this.from.equals(e.from()) && this.to.equals(e.to()))
			return true;
		return false;
	}

	/**
	 * Returns a hash code value for the edge. Based on start and end node of
	 * the edge.
	 * 
	 * @return The hash code value for the edge.
	 */
	@Override
	public int hashCode() {
		int hash = 5;
		hash += this.from.hashCode() * 37;
		hash += this.to.hashCode() * 19;
		return hash;
	}
}
