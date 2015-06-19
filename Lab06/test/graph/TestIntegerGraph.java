package graph;

import static org.junit.Assert.*;
import graph.AdjacencyListGraph;
import graph.Graph;

import org.junit.Test;

public class TestIntegerGraph {

	@Test
	public void testAddNode() {
		Graph<Integer> f = new AdjacencyListGraph<Integer>();
		
		// make nodes and check size
		assertEquals(0, f.size());
		f.addNode(3);
		f.addNode(4);
		assertEquals(2, f.size());
		f.addNode(27);
		f.addNode(50);
		assertEquals(4, f.size());
		
		// check that nodes exist
		assertTrue(f.contains(3));
		assertTrue(f.contains(4));
		assertTrue(f.contains(27));
		assertTrue(f.contains(50));
		
		// make connections
		f.addConnection(3, 50);
		f.addConnection(4, 27);
		f.addConnection(4, 50);
		
		// check that connections exist
		assertTrue(f.isConnected(3, 50));
		assertTrue(f.isConnected(4, 27));
		assertTrue(f.isConnected(50, 4));
		
		// remove connections
		f.removeConnection(3, 50);
		f.removeConnection(4, 27);
		f.removeConnection(4, 50);
		
		// check that connections don't exist
		assertFalse(f.isConnected(3, 50));
		assertFalse(f.isConnected(4, 27));
		assertFalse(f.isConnected(50, 4));
		
		// remove nodes and check size
		f.removeNode(3);
		f.removeNode(4);
		assertEquals(2, f.size());
		f.removeNode(27);
		f.removeNode(50);
		assertEquals(0, f.size());
	}
	
	@Test
	public void testAddRemoveConnections() {
		Graph<Integer> f = new AdjacencyListGraph<Integer>();
		
		// make nodes
		f.addNode(3);
		f.addNode(4);
		f.addNode(27);
		f.addNode(50);
		
		// make connections
		f.addConnection(3, 50);
		f.addConnection(4, 27);
		f.addConnection(4, 50);
		
		// check that connections exist
		assertTrue(f.isConnected(3, 50));
		assertTrue(f.isConnected(4, 27));
		assertTrue(f.isConnected(50, 4));
		
		// remove connections
		f.removeConnection(3, 50);
		f.removeConnection(4, 27);
		f.removeConnection(4, 50);
		
		// check that connections don't exist
		assertFalse(f.isConnected(3, 50));
		assertFalse(f.isConnected(4, 27));
		assertFalse(f.isConnected(50, 4));
	}
	
	@Test
	public void testRemoveNodes() {
		Graph<Integer> f = new AdjacencyListGraph<Integer>();
		
		// make nodes
		f.addNode(3);
		f.addNode(4);
		f.addNode(27);
		f.addNode(50);
		
		// remove nodes and check size
		f.removeNode(3);
		f.removeNode(4);
		assertEquals(2, f.size());
		f.removeNode(27);
		f.removeNode(50);
		assertEquals(0, f.size());
	}
}
