package graph;

public class Run {

	public static void main(String[] args) {
		  Graph<String> g = new AdjacencyListGraph<String>();
		  
		  fillStringGraph(g);
		  
		  StringSearch dfs = new StringDFS();
		  StringSearch bfs = new StringBFS();
		  
		  System.out.println("starting dfs");
		  for (String s: dfs.search("Bucharest", "Iasi", g)) {
			  System.out.println(s);
		  }
		  
		  System.out.println("starting bfs");
		  for (String s: bfs.search("Bucharest", "Iasi", g)) {
			  System.out.println(s);
		  }
	}
	
	public static void fillStringGraph(Graph<String> g) {
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
		  g.addConnection("Oradea", "Zerind");
		  g.addConnection("Zerind", "Arad");
		  g.addConnection("Arad", "Sibiu");
		  g.addConnection("Sibiu", "Oradea");
		  g.addConnection("Timisoara", "Arad");
		  g.addConnection("Timisoara", "Lugoj");
		  g.addConnection("Lugoj", "Mehadia");
		  g.addConnection("Mehadia", "Dobreta");
		  g.addConnection("Dobreta", "Craiova");
		  g.addConnection("Sibiu", "Fagaras");
		  g.addConnection("Fagaras", "Bucharest");
		  g.addConnection("Sibiu", "Rimnicu Vilcea");
		  g.addConnection("Pitesti", "Rimnicu Vilcea");
		  g.addConnection("Craiova", "Rimnicu Vilcea");
		  g.addConnection("Craiova", "Pitesti");
		  g.addConnection("Pitesti", "Bucharest");
		  g.addConnection("Bucharest", "Giurgiu");
		  g.addConnection("Bucharest", "Urziceni");
		  g.addConnection("Urziceni", "Hirsova");
		  g.addConnection("Hirsova", "Eforie");
		  g.addConnection("Urziceni", "Vaslui");
		  g.addConnection("Vaslui", "Iasi");
		  g.addConnection("Neamt", "Iasi");
		  
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
	}
}