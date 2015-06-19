
public class Run {

	public static void main(String[] args) {
		  Graph<String> g = new AdjacencyListGraph<String>(20);
		  
		  // make nodes
		  g.addNode("Oradea");
		  g.addNode("Zerind");
		  g.addNode("Arad");
		  g.addNode("Timisoara");
		  g.addNode("Lugoj");
		  
		  g.addNode("Mehadia");
		  g.addNode("Dobreta");
		  g.addNode("Craiova");
		  g.addNode("Rimnicu Vilcea");
		  g.addNode("Sibiu");
		  
		  g.addNode("Pitesti");
		  g.addNode("Fagaras");
		  g.addNode("Bucharest");
		  g.addNode("Giurgiu");
		  g.addNode("Hirsova");
		  
		  g.addNode("Eforie");
		  g.addNode("Urziceni");
		  g.addNode("Vaslui");
		  g.addNode("Iasi");
		  g.addNode("Neamt");
		  
		  // test that nodes exist
		  if(!g.contains("Oradea")) System.out.println("Oradea does not exist");
		  if(!g.contains("Zerind")) System.out.println("Zerind does not exist");
		  if(!g.contains("Arad")) System.out.println("Arad does not exist");
		  if(!g.contains("Timisoara")) System.out.println("Timisoara does not exist");
		  if(!g.contains("Lugoj")) System.out.println("Lugoj does not exist");

		  if(!g.contains("Mehadia")) System.out.println("Mehadia does not exist");
		  if(!g.contains("Dobreta")) System.out.println("Dobreta does not exist");
		  if(!g.contains("Craiova")) System.out.println("Craiova does not exist");
		  if(!g.contains("Rimnicu Vilcea")) System.out.println("Rimnicu Vilcea does not exist");
		  if(!g.contains("Sibiu")) System.out.println("Sibiu does not exist");

		  if(!g.contains("Pitesti")) System.out.println("Pitesti does not exist");
		  if(!g.contains("Fagaras")) System.out.println("Fagaras does not exist");
		  if(!g.contains("Bucharest")) System.out.println("Bucharest does not exist");
		  if(!g.contains("Giurgiu")) System.out.println("Giurgiu does not exist");
		  if(!g.contains("Hirsova")) System.out.println("Hirsova does not exist");

		  if(!g.contains("Eforie")) System.out.println("Eforie does not exist");
		  if(!g.contains("Urziceni")) System.out.println("Urziceni does not exist");
		  if(!g.contains("Vaslui")) System.out.println("Vaslui does not exist");
		  if(!g.contains("Iasi")) System.out.println("Iasi does not exist");
		  if(!g.contains("Neamt")) System.out.println("Neamt does not exist");
		  
		  // make connections
		  g.addConnection("Oradea", "Zerind", 71);
		  g.addConnection("Zerind", "Arad", 75);
		  g.addConnection("Arad", "Sibiu", 140);
		  g.addConnection("Sibiu", "Oradea", 151);
		  g.addConnection("Timisoara", "Arad", 118);
		  g.addConnection("Timisoara", "Lugoj", 111);
		  g.addConnection("Lugoj", "Mehadia", 70);
		  g.addConnection("Mehadia", "Dobreta", 75);
		  g.addConnection("Dobreta", "Craiova", 120);
		  g.addConnection("Sibiu", "Fagaras", 99);
		  g.addConnection("Fagaras", "Bucharest", 211);
		  g.addConnection("Sibiu", "Rimnicu Vilcea", 80);
		  g.addConnection("Pitesti", "Rimnicu Vilcea", 97);
		  g.addConnection("Craiova", "Rimnicu Vilcea", 146);
		  g.addConnection("Craiova", "Pitesti", 136);
		  g.addConnection("Pitesti", "Bucharest", 101);
		  g.addConnection("Bucharest", "Giurgiu", 90);
		  g.addConnection("Bucharest", "Urziceni", 85);
		  g.addConnection("Urziceni", "Hirsova", 98);
		  g.addConnection("Hirsova", "Eforie", 86);
		  g.addConnection("Urziceni", "Vaslui", 142);
		  g.addConnection("Vaslui", "Iasi", 92);
		  g.addConnection("Neamt", "Iasi", 87);
		  
		  // check connections
		  if(!g.isConnected("Oradea", "Zerind")) System.out.println("Oradea is not connected to Zerind");
		  if(!g.isConnected("Zerind", "Arad")) System.out.println("Zerind is not connected to Arad");
		  if(!g.isConnected("Arad", "Sibiu")) System.out.println("Arad is not connected to Sibiu");
		  if(!g.isConnected("Sibiu", "Oradea")) System.out.println("Sibiu is not connected to Oradea");
		  if(!g.isConnected("Timisoara", "Arad")) System.out.println("Timisoara is not connected to Arad");
		  if(!g.isConnected("Timisoara", "Lugoj")) System.out.println("Timisoara is not connected to Lugoj");
		  if(!g.isConnected("Lugoj", "Mehadia")) System.out.println("Lugoj is not connected to Mehadia");
		  if(!g.isConnected("Mehadia", "Dobreta")) System.out.println("Mehadia is not connected to Dobreta");
		  if(!g.isConnected("Dobreta", "Craiova")) System.out.println("Dobreta is not connected to Craiova");
		  if(!g.isConnected("Sibiu", "Fagaras")) System.out.println("Sibiu is not connected to Fagaras");
		  if(!g.isConnected("Fagaras", "Bucharest")) System.out.println("Fagaras is not connected to Bucharest");
		  if(!g.isConnected("Sibiu", "Rimnicu Vilcea")) System.out.println("Sibiu is not connected to Rimnicu Vilcea");
		  if(!g.isConnected("Pitesti", "Rimnicu Vilcea")) System.out.println("Pitesti is not connected to Rimnicu Vilcea");
		  if(!g.isConnected("Craiova", "Rimnicu Vilcea")) System.out.println("Craiova is not connected to Rimnicu Vilcea");
		  if(!g.isConnected("Craiova", "Pitesti")) System.out.println("Craiova is not connected to Pitesti");
		  if(!g.isConnected("Pitesti", "Bucharest")) System.out.println("Pitesti is not connected to Bucharest");
		  if(!g.isConnected("Bucharest", "Giurgiu")) System.out.println("Bucharest is not connected to Giurgiu");
		  if(!g.isConnected("Bucharest", "Urziceni")) System.out.println("Bucharest is not connected to Urziceni");
		  if(!g.isConnected("Urziceni", "Hirsova")) System.out.println("Urziceni is not connected to Hirsova");
		  if(!g.isConnected("Hirsova", "Eforie")) System.out.println("Hirsova is not connected to Eforie");
		  if(!g.isConnected("Urziceni", "Vaslui")) System.out.println("Urziceni is not connected to Vaslui");
		  if(!g.isConnected("Vaslui", "Iasi")) System.out.println("Vaslui is not connected to Iasi");
		  if(!g.isConnected("Neamt", "Iasi")) System.out.println("Neamt is not connected to Iasi");
		  
		  Heuristic<String> h0 = new ZeroHeuristic<String>();
		  Heuristic<String> h1 = new StraightLineHeuristic<String>();
		  
		  System.out.println("starting greedy with straight line heuristic");
		  for (String s: g.greedy("Arad", "Bucharest", h1)) {
			  System.out.println(s);
		  }
		  
		  System.out.println("starting a* with zero heuristic");
		  for (String s: g.aStar("Arad", "Bucharest", h0)) {
			  System.out.println(s);
		  }
		  
		  System.out.println("starting a* with straight line heuristic");
		  for (String s: g.aStar("Arad", "Bucharest", h1)) {
			  System.out.println(s);
		  }
	}

}