package sets;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import java.io.*;

public class TestSet {

	@Test
	public void testContains() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		
		assertTrue(ts1.contains(1));
		assertTrue(ts1.contains(2));
	}

	@Test
	public void testSize() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		
		assertEquals(0, ts1.getSize());
		
		ts1.add(1);
		ts1.add(2);
		
		assertEquals(2, ts1.getSize());
	}
	
	@Test
	public void testRemove() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		
		ts1.remove(1);
		assertEquals(1, ts1.getSize());
		ts1.remove(2);
		assertEquals(0, ts1.getSize());
	}
	
	@Test
	public void testIntersect() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		Set<Integer> ts2 = new MySet<Integer>(Integer.class);
		Set<Integer> ts3 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		ts2.add(2);
		ts2.add(0);
		ts2.add(3);
		ts3 = ts1.intersect(ts2);

		assertEquals(1, ts3.getSize());
		assertTrue(ts3.contains(2));
		assertFalse(ts3.contains(1));
		assertFalse(ts3.contains(0));
		assertFalse(ts3.contains(3));
	}
	
	@Test
	public void testSubset() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		Set<Integer> ts2 = new MySet<Integer>(Integer.class);
		Set<Integer> ts3 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		ts2.add(2);
		ts2.add(0);
		ts2.add(3);
		ts3 = ts1.intersect(ts2);

		assertTrue(ts1.subset(ts3));
		assertTrue(ts2.subset(ts3));
		assertFalse(ts3.subset(ts1));
		assertFalse(ts3.subset(ts2));
		
		ts3 = ts1.union(ts2);
		
		assertTrue(ts3.subset(ts1));
		assertTrue(ts3.subset(ts2));
		ts1.remove(1);
		assertTrue(ts3.subset(ts1));
	}
	
	@Test
	public void testUnion() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		Set<Integer> ts2 = new MySet<Integer>(Integer.class);
		Set<Integer> ts3 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		ts2.add(2);
		ts2.add(0);
		ts2.add(3);
		ts3 = ts1.intersect(ts2);
		ts3 = ts1.union(ts2);
		
		assertEquals(4, ts3.getSize());
		assertTrue(ts3.contains(2));
		assertTrue(ts3.contains(1));
		assertTrue(ts3.contains(0));
		assertTrue(ts3.contains(3));		
	}
	
	@Test
	public void testGetItems() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		Set<Integer> ts2 = new MySet<Integer>(Integer.class);
		Set<Integer> ts3 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		ts1.add(2);
		ts2.add(2);
		ts2.add(0);
		ts2.add(3);
		ts3 = ts1.intersect(ts2);
		ts3 = ts1.union(ts2);
		ts1.remove(1);
		
		// test get items
			// check size is equal
			// check elements are the same
		List<Integer> it1 = ts1.getItems();
		assertEquals(it1.size(), ts1.getSize());
		for (Integer i: it1) {
			assertTrue(ts1.contains(i));
		}
		
		List<Integer> it2 = ts2.getItems();
		assertEquals(it2.size(), ts2.getSize());
		for (Integer i: it2) {
			assertTrue(ts2.contains(i));
		}
		
		List<Integer> it3 = ts3.getItems();
		assertEquals(it3.size(), ts3.getSize());
		for (Integer i: it3) {
			assertTrue(ts3.contains(i));
		}
	}
	
	@Test
	public void testEquals() {
		Set<Integer> ts1 = new MySet<Integer>(Integer.class);
		Set<Integer> ts2 = new MySet<Integer>(Integer.class);
		
		ts1.add(1);
		
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));

		ts2.add(1);
		
		assertTrue(ts1.equals(ts2));
		assertTrue(ts2.equals(ts1));
		
		ts1.add(2);
		
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));
		
		ts2.add(2);
		
		assertTrue(ts1.equals(ts2));
		assertTrue(ts2.equals(ts1));
		
		ts1.add(3);
		
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));

		ts2.add(3);
		
		assertTrue(ts1.equals(ts2));
		assertTrue(ts2.equals(ts1));
		
		ts1.add(5);

		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));

		ts1.add(5);
		
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));

		ts2.add(5);

		assertTrue(ts1.equals(ts2));
		assertTrue(ts2.equals(ts1));
		
		ts2.add(6);
		
		assertFalse(ts1.equals(ts2));
		assertFalse(ts2.equals(ts1));
	}
	
	@Test
	public void testScanner() {
		ArrayList<Set<Character>> sets = new ArrayList<Set<Character>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader("testScanner.txt"));
			for (String line = br.readLine(); line != null; line = br.readLine()) {
				Set<Character> set = new MySet<Character>(Character.class);
				for (int i = 0; i < line.length(); i++) {
					set.add(line.charAt(i));
				}
				sets.add(set);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
