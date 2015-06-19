package assembler;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestDiscount {

	@Test
	public void test() {
		Item i1 = new Item(3);
		Item i2 = new Item(5);
		Item i3 = new Item(8);
		
		Assembly a1 = new Assembly(7);
		a1.addPart(i1);
		a1.addPart(i2);
		a1.addPart(i3);
		
		Assembly a2 = new Assembly(4);
		a2.addPart(a1);
		
		a1.removePart(i1);
		
		Part id1 = new Discount(new Item(4), 50);
		assertEquals(2, id1.getPrice());
		Part ad1 = new Discount(new Assembly(6), 50);
		assertEquals(3, ad1.getPrice());
		ad1.addPart(a2);
		assertEquals(15, ad1.getPrice());
	}

}
