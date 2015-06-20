package assembler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestAssembly {

	@Test
	public void testGetPrice() {
		Item i1 = new Item(3);
		Item i2 = new Item(5);
		Item i3 = new Item(8);

		assertEquals(3, i1.getPrice());
		assertEquals(5, i2.getPrice());
		assertEquals(8, i3.getPrice());
		
		Assembly a1 = new Assembly(7);
		assertEquals(7, a1.getPrice());
	}

	@Test
	public void testAddRemovePart() {
		Item i1 = new Item(3);
		Item i2 = new Item(5);
		Item i3 = new Item(8);
		
		Assembly a1 = new Assembly(7);
		a1.addPart(i1);
		assertEquals(10, a1.getPrice());
		a1.addPart(i2);
		assertEquals(15, a1.getPrice());
		a1.addPart(i3);
		assertEquals(23, a1.getPrice());
		
		Assembly a2 = new Assembly(4);
		assertEquals(4, a2.getPrice());
		a2.addPart(a1);
		assertEquals(27, a2.getPrice());
		a2.removePart(a1);
		assertEquals(4, a2.getPrice());		
		
		a1.removePart(i1);
		assertEquals(20, a1.getPrice());
		a1.addPart(a2);
		assertEquals(24, a1.getPrice());
	}
}