package graph;
import java.util.*;

public class AdjacencyListGraph<E> implements Graph<E>{
	private int size;
	private HashMap<E, Node<E>> nodes;
	
	public AdjacencyListGraph() {
		super();
		this.size = 0;
		this.nodes = new HashMap<E, Node<E>>();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void addNode(E a) {
		Node<E> n = new Node<E>(a);
		nodes.put(n.getNodeObj(), n);
		size++;
	}

	@Override
	public void removeNode(E a) {
		List<Node<E>> connections = nodes.get(a).getConnections();
		for (Node<E> n: connections) {
			n.removeConnection(nodes.get(a));
		}
		nodes.remove(nodes.get(a));	
		size--;
	}

	@Override
	public void addConnection(E from, E to) {
		nodes.get(from).addConnection(nodes.get(to));
		nodes.get(to).addConnection(nodes.get(from));
	}

	@Override
	public void removeConnection(E from, E to) {
		nodes.get(from).removeConnection(nodes.get(to));
		nodes.get(to).removeConnection(nodes.get(from));
	}

	@Override
	public boolean contains(E a) {
		return nodes.get(a) != null;			
	}

	@Override
	public boolean isConnected(E a, E b) {
		return nodes.get(a).isConnected(b);			
	}
	
	public Set<Node<E>> getNodes() {
		return new HashSet<Node<E>>(nodes.values());
	}
}

class Node<E>{
	private E nodeObj;
	private HashMap<E, Node<E>> connections;
	
	public Node(E nodeObj) {
		super();
		this.nodeObj = nodeObj;
		this.connections = new HashMap<E, Node<E>>();
	}
	
	public List<Node<E>> getConnections() {
		return new ArrayList<Node<E>>(connections.values());
	}
	
	public boolean isConnected(E a){
		return connections.containsKey(a);
	}
	
	public boolean isConnected(Node<E> n) {
		return connections.containsValue(n);
	}
	
	public void addConnection(Node<E> connectedNode) {
		connections.put(connectedNode.getNodeObj(), connectedNode);
	}
	
	public void removeConnection(Node<E> connectedNode) {
		connections.remove(connectedNode);
	}
	
	public E getNodeObj() {
		return nodeObj;
	}
}