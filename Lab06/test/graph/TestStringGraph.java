package graph;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStringGraph {

	@Test
	public void testAddNode() {
		Graph<String> g = new AdjacencyListGraph<String>();
		
		// make nodes and check size
		assertEquals(0, g.size());
		g.addNode("A");
		g.addNode("B");
		assertEquals(2, g.size());
		g.addNode("C");
		g.addNode("D");
		assertEquals(4, g.size());
		
		// check that nodes exist
		assertTrue(g.contains("A"));
		assertTrue(g.contains("B"));
		assertTrue(g.contains("C"));
		assertTrue(g.contains("D"));
	}

	@Test
	public void testAddRemoveConnection() {
		Graph<String> g = new AdjacencyListGraph<String>();
		
		// make nodes
		g.addNode("A");
		g.addNode("B");
		g.addNode("C");
		g.addNode("D");
		
		// make connections
		g.addConnection("A", "B");
		g.addConnection("A", "D");
		g.addConnection("C", "D");
		
		// check that connections exist
		assertTrue(g.isConnected("A", "B"));
		assertTrue(g.isConnected("A", "D"));
		assertTrue(g.isConnected("C", "D"));
		
		// remove connections
		g.removeConnection("A", "B");
		g.removeConnection("A", "D");
		g.removeConnection("C", "D");
		
		// check that connections don't exist
		assertFalse(g.isConnected("A", "B"));
		assertFalse(g.isConnected("A", "D"));
		assertFalse(g.isConnected("C", "D"));
	}
	
	@Test
	public void testRemoveNode() {
		Graph<String> g = new AdjacencyListGraph<String>();
		
		// make nodes
		g.addNode("A");
		g.addNode("B");
		g.addNode("C");
		g.addNode("D");
		
		// remove nodes and check size
		g.removeNode("A");
		g.removeNode("B");
		assertEquals(2, g.size());
		g.removeNode("C");
		g.removeNode("D");
		assertEquals(0, g.size());
	}
}
