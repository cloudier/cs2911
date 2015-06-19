
public class StraightLineHeuristic<E> implements Heuristic<E> {

	@Override
	public int distanceLeft(Node<E> source, Node<E> dest) {
		if (!dest.getNodeObj().equals("Bucharest")) return 0; // Destination is not Bucharest
		if (source.getNodeObj().equals("Oradea")) return 380;
		if (source.getNodeObj().equals("Zerind")) return 374;
		if (source.getNodeObj().equals("Arad")) return 366;
		if (source.getNodeObj().equals("Timisoara")) return 329;
		if (source.getNodeObj().equals("Lugoj")) return 244;

		if (source.getNodeObj().equals("Mehadia")) return 241;
		if (source.getNodeObj().equals("Dobreta")) return 242;
		if (source.getNodeObj().equals("Craiova")) return 160;
		if (source.getNodeObj().equals("Rimnicu Vilcea")) return 193;
		if (source.getNodeObj().equals("Sibiu")) return 253;

		if (source.getNodeObj().equals("Pitesti")) return 98;
		if (source.getNodeObj().equals("Fagaras")) return 178;
		if (source.getNodeObj().equals("Bucharest")) return 0;
		if (source.getNodeObj().equals("Giurgiu")) return 77;
		if (source.getNodeObj().equals("Hirsova")) return 151;

		if (source.getNodeObj().equals("Eforie")) return 161;
		if (source.getNodeObj().equals("Urziceni")) return 80;
		if (source.getNodeObj().equals("Vaslui")) return 199;
		if (source.getNodeObj().equals("Iasi")) return 226;
		if (source.getNodeObj().equals("Neamt")) return 234;
		return 0;
	}

}
