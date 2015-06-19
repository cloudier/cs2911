package enrol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestSession {

	@Test
	public void testOverlapsWith() {
		Session s1 = new Session(1, 2015, 3, "Keith Burrows", 1400, 1600);
		Session s2 = new Session(1, 2015, 3, "Mathews A", 1400, 1500);
		assertTrue(s1.overlapsWith(s2));
		Session s3 = new Session(2, 2014, 3, "Physics Theatre", 1200, 1400);
		assertFalse(s1.overlapsWith(s3));
		assertFalse(s2.overlapsWith(s3));
		Session s4 = new Session(2, 2015, 3, "Physics Theatre", 1200, 1400);
		assertFalse(s1.overlapsWith(s4));
		assertFalse(s2.overlapsWith(s4));
		Session s5 = new Session(1, 2015, 2, "Physics Theatre", 1200, 1400);
		assertFalse(s1.overlapsWith(s5));
		assertFalse(s2.overlapsWith(s5));
		Session s6 = new Session(1, 2015, 3, "Physics Theatre", 1200, 1400);
		assertFalse(s1.overlapsWith(s6));
		assertFalse(s2.overlapsWith(s6));
		Session s7 = new Session(1, 2015, 3, "Physics Theatre", 1500, 1800);
		assertTrue(s1.overlapsWith(s7));
		assertFalse(s2.overlapsWith(s7));
		Session s8 = new Session(1, 2015, 3, "Physics Theatre", 1400, 1600);
		assertTrue(s1.overlapsWith(s8));
		assertTrue(s2.overlapsWith(s8));
	}

}
