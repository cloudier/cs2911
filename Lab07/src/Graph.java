import java.util.*;

public interface Graph<E> { // Graph has objects of arbitrary type E
	public void addNode(E a);
	public void removeNode(E a);
	public void addConnection(E from, E to, Integer weight);
	public void removeConnection(E from, E to);
	public boolean contains(E a); // handy because standard Java term
	public boolean isConnected(E a, E b); // a and b are graph Node
	public void getConnections(E a); // used for debugging
	public int size();
	public List<E> greedy(E source, E destination, Heuristic<E> h);
	public List<E> aStar(E source, E destination, Heuristic<E> h);
}
