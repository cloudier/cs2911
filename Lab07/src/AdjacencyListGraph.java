import java.util.*;

public class AdjacencyListGraph<E> implements Graph<E>{
	private int size;
	private List<Node<E>> nodes;
	
	public AdjacencyListGraph(int size) {
		super();
		this.size = size;
		this.nodes = new ArrayList<Node<E>>();
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void addNode(E a) {
		Node<E> n = new Node<E>(a);
		nodes.add(n);
		size++;
	}

	@Override
	public void removeNode(E a) {
		Node<E> nodeToRemove = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(a)) {
				nodeToRemove = n;
			}
		}
		
		for (Node<E> n: nodes) {
			if (n.isConnected(a)) {
				for (Node<E> m: n.getConnections().keySet()) {
					if (m.getNodeObj().equals(a)) {
						m.removeConnection(nodeToRemove);
					}
				}
			}
		}
		
		if (nodeToRemove != null) {
			nodes.remove(nodeToRemove);
			size--;
		}
	}

	@Override
	public void addConnection(E from, E to, Integer weight) {
		Node<E> fromNode = null;
		Node<E> toNode = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(from)) {
				fromNode = n;
			}
			if (n.getNodeObj().equals(to)) {
				toNode = n;
			}
		}
		
		if (fromNode != null && toNode != null) {
			fromNode.addConnection(toNode, weight);
			toNode.addConnection(fromNode, weight);
		}
	}

	@Override
	public void removeConnection(E from, E to) {
		Node<E> fromNode = null;
		Node<E> toNode = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(from)) {
				fromNode = n;
			}
			if (n.getNodeObj().equals(to)) {
				toNode = n;
			}
		}
		if (fromNode != null && toNode != null) {
			fromNode.removeConnection(toNode);
			toNode.removeConnection(fromNode);
		}
	}

	@Override
	public boolean contains(E a) {
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(a)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isConnected(E a, E b) {
		Node<E> nodeToCheck = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(a)) {
				nodeToCheck = n;
			}
		}
		if (nodeToCheck != null) {
			return nodeToCheck.isConnected(b);
		} else {
			System.out.println("No node exists to check");
			return false;
		}
	}
	
	public void getConnections(E a) {
		Node<E> aNode = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(a)) {
				aNode = n;
			}
		}
		
		for (Node<E> n: aNode.getConnections().keySet()) {
			System.out.println(n.getNodeObj());
		}
	}

	public List<E> greedy(E source, E destination, Heuristic<E> h) {
		HashSet<Node<E>> visited = new HashSet<Node<E>>();
		PriorityQueue<Node<E>> pq = new PriorityQueue<Node<E>>();
		HashMap<Node<E>, Node<E>> parents = new HashMap<Node<E>, Node<E>>(); // map node to parent

		Node<E> sourceNode = null;
		Node<E> destNode = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(source)) {
				sourceNode = n;
			}
			if (n.getNodeObj().equals(destination)) {
				destNode = n;
			}
		}
		
		if (sourceNode == null) {
			System.out.println("Source node does not exist");
			return null;
		}
		
		if (destNode == null) {
			System.out.println("Destination node does not exist");
			return null;
		}

		sourceNode.setG(0);
		sourceNode.setF(h.distanceLeft(sourceNode, destNode));
		pq.add(sourceNode);
		
		Node<E> curr = null;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			if (curr.getNodeObj().equals(destination)) {
				break;
			}
			
			visited.add(curr);
			for (Node<E> n: curr.getConnections().keySet()) {
				if (visited.contains(n)) {
					continue;
				}

				int temp_g = curr.getG() + curr.getConnections().get(n);
				if (!pq.contains(n) || temp_g < n.getG()) {
					parents.put(n, curr);
					n.setG(temp_g);
					n.setF(h.distanceLeft(n, destNode));
					if (!pq.contains(n)) {
						pq.add(n);
					}
				}
			}
		}

		if (curr == null) {
			System.out.println("Current node should not be null");
			return null;
		}

		List<E> routes = new ArrayList<E>();
		while (curr != sourceNode) {
			routes.add(0, curr.getNodeObj());
			curr = parents.get(curr);
		}
		routes.add(0, curr.getNodeObj());
		return routes;
	}

	public List<E> aStar(E source, E destination, Heuristic<E> h) {
		HashSet<Node<E>> visited = new HashSet<Node<E>>();
		PriorityQueue<Node<E>> pq = new PriorityQueue<Node<E>>();
		HashMap<Node<E>, Node<E>> parents = new HashMap<Node<E>, Node<E>>(); // map node to parent

		Node<E> sourceNode = null;
		Node<E> destNode = null;
		for (Node<E> n: nodes) {
			if (n.getNodeObj().equals(source)) {
				sourceNode = n;
			}
			if (n.getNodeObj().equals(destination)) {
				destNode = n;
			}
		}
		
		if (sourceNode == null) {
			System.out.println("Source node does not exist");
			return null;
		}
		
		if (destNode == null) {
			System.out.println("Destination node does not exist");
			return null;
		}

		sourceNode.setG(0);
		sourceNode.setF(sourceNode.getG() + h.distanceLeft(sourceNode, destNode));
		pq.add(sourceNode);
		
		Node<E> curr = null;
		while (!pq.isEmpty()) {
			curr = pq.poll();
			System.out.printf("%s g: %d f: %d\n", curr.getNodeObj(), curr.getG(), curr.getF());
			if (curr.getNodeObj().equals(destination)) {
				break;
			}
			
			visited.add(curr);
			for (Node<E> n: curr.getConnections().keySet()) {
				if (visited.contains(n)) {
					continue;
				}

				int temp_g = curr.getG() + curr.getConnections().get(n);
				if (!pq.contains(n) || temp_g < n.getG()) {
					parents.put(n, curr);
					n.setG(temp_g);
					n.setF(n.getG() + h.distanceLeft(n, destNode));
					if (!pq.contains(n)) {
						pq.add(n);
					}
				}
			}
		}

		if (curr == null) {
			System.out.println("Current node should not be null");
			return null;
		}

		List<E> routes = new ArrayList<E>();
		while (curr != sourceNode) {
			routes.add(0, curr.getNodeObj());
			curr = parents.get(curr);
		}
		routes.add(0, curr.getNodeObj());
		return routes;
	}
}

class Node<E> implements Comparable<Node<E>>{
	private E nodeObj;
	private int f; // knowledge + heuristic
	private int g; // knowledge
	private HashMap<Node<E>, Integer> connections;
	
	public Node(E nodeObj) {
		super();
		this.nodeObj = nodeObj;
		this.connections = new HashMap<Node<E>, Integer>();
		this.f = 0;
		this.g = 0;
	}
	
	public int getF() {
		return this.f;
	}
	
	public void setF(int f) {
		this.f = f;
	}
	
	public int getG() {
		return this.g;
	}
	
	public void setG(int g) {
		this.g = g;
	}
	
	public HashMap<Node<E>, Integer> getConnections() {
		return new HashMap<Node<E>, Integer>(connections);
	}
	
	public boolean isConnected(E a){
		for (Node<E> n: connections.keySet()) {
			if (n.getNodeObj().equals(a)) {
				return true;
			}
		}
		return false;
	}
	
	public void addConnection(Node<E> connectedNode, Integer weight) {
		connections.put(connectedNode, weight);
	}
	
	public void removeConnection(Node<E> connectedNode) {
		connections.remove(connectedNode);
	}
	
	public E getNodeObj() {
		return nodeObj;
	}

	@Override
	public int compareTo(Node<E> o) {
		if (this.f > o.f) return 1;
		if (this.f == o.f) return 0;
		if (this.f < o.f) return -1;
		return 0;
	}
}