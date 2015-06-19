package graph;
import java.text.Collator;
import java.util.*;

public class StringBFS implements StringSearch {
	public List<String> search(String source, String destination, Graph<String> g) {
		// find source node
		Node<String> sourceNode = null;
		for (Node<String> n: g.getNodes()) {
			if (n.getNodeObj().equals(source)) {
				sourceNode = n;
			}
		}
		
		// initialise queue and add source node onto the queue
		// initialise visited hash set and parents hash map
		ArrayDeque<Node<String>> q = new ArrayDeque<Node<String>>();
		HashSet<Node<String>> nodesVisited = new HashSet<Node<String>>();
		HashMap<Node<String>, Node<String>> parents = new HashMap<Node<String>, Node<String>>(); // map child to parent
		if (sourceNode != null) {
			q.add(sourceNode);
		}
		
		// loop as long as queue is not empty
		Node<String> curr = null;
		while (!q.isEmpty()) {
			curr = q.remove();
			// break if destination reached
			if (curr.getNodeObj().equals(destination)) {
				break;
			}
			
			// add to visited hash set
			nodesVisited.add(curr);
			
			// find all the children
			List<Node<String>> connections = curr.getConnections();
			final Collator col = Collator.getInstance();
			Comparator<Node<String>> com = new Comparator<Node<String>>() {
				@Override
				public int compare(Node<String> n, Node<String> m) {
					if (col.compare(n.getNodeObj(), m.getNodeObj()) == -1) return -1;
					if (n.getNodeObj().equals(m.getNodeObj())) return 0;
					if (col.compare(n.getNodeObj(), m.getNodeObj()) == 1) return 1;
					System.out.println("Node<String> objects cannot be compared");
					return 0;
				}
			};
			Collections.sort(connections, com);
			
			// add children to queue if they're unvisited
			for (Node<String> n: connections) {
				if (!nodesVisited.contains(n)) {
					q.add(n);
					parents.put(n, curr);
				}
			}
		}
		
		List<String> route = new ArrayList<String>();
		// backtrack through parents hash map to find route
		Node<String> backtrack = curr; // curr should be the destination here
		while (!backtrack.getNodeObj().equals(source)) {
			route.add(backtrack.getNodeObj());
			backtrack = parents.get(backtrack);
		}
		route.add(source);
		Collections.reverse(route);
		return route;
	}

}
