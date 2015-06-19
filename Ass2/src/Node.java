import java.util.*;

/**
 * This node class represents the airports. Each node contains a name (e.g.
 * "Sydney"), the delay at the airport in minutes and a HashMap of flights that
 * this airport is a part of. The HashMap maps flights to their lengths.
 * 
 * @author Claudia Tu z3459448
 *
 */
public class Node {
	private String name;
	private HashMap<Node, Integer> connections;
	private int delay;

	/**
	 * Creates a new node. This node represents an airport. The node's name is
	 * the name of the airport. The delay of the node is the delay in minutes
	 * experienced at the airport before the plane can fly to another airport.
	 * 
	 * @param name
	 *            The name of the node as a string.
	 * @param delay
	 *            The delay experience at this node as an int.
	 */
	public Node(String name, int delay) {
		this.name = name;
		this.connections = new HashMap<Node, Integer>();
		this.delay = delay;
	}

	/**
	 * Returns the name of the node (or airport) as a string, e.g. "Sydney".
	 * 
	 * @return The name of the node as a string.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the delay in minutes experienced at the node (or airport) as an
	 * int.
	 * 
	 * @return The delay experienced at the node as an int.
	 */
	public int getDelay() {
		return this.delay;
	}

	/**
	 * Returns the weight of the edge between this node and another given node,
	 * or the length of the flight in minutes between this airport and another
	 * given airport.
	 * 
	 * @param n
	 *            The other node in the edge whose weight you wish to find.
	 * @return An int indicating the weight of the edge between this node and
	 *         the other node.
	 */
	public int distanceTo(Node n) {
		return connections.get(n);
	}

	/**
	 * Adds a connection between this node (or airport) and a given node with
	 * the given weight (or flight time in minutes).
	 * 
	 * @param connectedNode
	 *            The other node that this node will be connected to.
	 * @param weight
	 *            The weight of the edge between this node and the given node.
	 */
	public void addConnection(Node connectedNode, Integer weight) {
		connections.put(connectedNode, weight);
	}

	/**
	 * Returns whether or not two nodes have the same name and delay.
	 * 
	 * @return True if two nodes have the same name and delay, and false
	 *         otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (!(o instanceof Node))
			return false;
		Node n = (Node) o;
		if (this.name.equals(n.getName()) && this.delay == n.getDelay())
			return true;
		return false;
	}

	/**
	 * Returns a hash code value for the node. Based on name and delay of the
	 * node.
	 * 
	 * @return The hash code value for the node.
	 */
	@Override
	public int hashCode() {
		int hash = 5;
		hash += this.name.hashCode() * 7;
		hash += this.delay * 11;
		return hash;
	}
}