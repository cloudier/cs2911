
public class ZeroHeuristic<E> implements Heuristic<E>{

	@Override
	public int distanceLeft(Node<E> source, Node<E> dest) {
		return 0;
	}

}
