package graph;
import java.text.*;
import java.util.*;

public class StringDFS implements StringSearch{
	public List<String> search(String source, String destination, Graph<String> g) {
		// search algorithm should be concrete, e.g. List<String> DFS
		ArrayDeque<Node<String>> stack = new ArrayDeque<Node<String>>();
		Set<Node<String>> nodesVisited = new HashSet<Node<String>>();
		HashMap<Node<String>, Node<String>> parents = new HashMap<Node<String>, Node<String>>(); // map nodes to parents
		
		// put source on stack
		Node<String> sourceNode = null;
		for (Node<String> n: g.getNodes()) {
			if (n.getNodeObj().equals(source)) {
				sourceNode = n;
			}
		}
		if (sourceNode != null) {
			stack.push(sourceNode);			
		} else {
			System.out.println("you fucked up: couldn't find source node");
		}

		Node<String> destinationNode = null;
		
		while (!stack.isEmpty()) {
			// take off a node
			Node<String> curr = stack.pop();
			// check if its the destination
			if (curr.getNodeObj().equals(destination)) {
				destinationNode = curr;
				break;
			}
			
			// mark this node as visited
			nodesVisited.add(curr);
			
			// find all the children
			List<Node<String>> connections = curr.getConnections();
			final Collator myCollator = Collator.getInstance();
			Comparator<Node<String>> myComparator = new Comparator<Node<String>>() {
				@Override
				public int compare(Node<String> n, Node<String> m) {
					if (myCollator.compare(n.getNodeObj(), m.getNodeObj()) == -1) return -1;
					if (n.getNodeObj().equals(m.getNodeObj())) return 0;
					if (myCollator.compare(n.getNodeObj(), m.getNodeObj()) == 1) return 1;
					System.out.println("Node<String> objects cannot be compared");
					return 0;
				}
			};
			Collections.sort(connections, myComparator);
			
			for (Node<String> n: connections) {
				// put them on the stack
				if (!nodesVisited.contains(n)) { // if child is not already visited
					stack.push(n);
					parents.put(n, curr);
				}
			}
		}
		
		List<String> route = new ArrayList<String>();
		// go through parents to fill route
		// get destination node

		Node<String> backtrack = destinationNode;
		while (!backtrack.getNodeObj().equals(source)) {
			route.add(0, backtrack.getNodeObj());
			backtrack = parents.get(backtrack);
		}
		route.add(0, source);
		
		return route;
	}
}
