package graph;
import java.util.Set;

public interface Graph<E> { // Graph has objects of arbitrary type E
	// Basic graph operations
	//    Accessors (getters)
	public int size();
	public Set<Node<E>> getNodes();	
	//    Mutator (setters)
	public void addNode(E a);
	public void removeNode(E a);
	public void addConnection(E from, E to);
	public void removeConnection(E from, E to);	
	// Complex graph operations
	public boolean contains(E a); // handy because standard Java term
	public boolean isConnected(E a, E b); // a and b are graph Nodes
}
