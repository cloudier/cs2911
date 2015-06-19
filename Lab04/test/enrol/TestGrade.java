package enrol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestGrade {

	@Test
	public void testEqual() {
		Grade highd = new Grade("HD");
		Grade highd2 = new Grade("HD");
		Grade dist = new Grade("DN");
		Grade dist2 = new Grade("DN");
		Grade cred = new Grade("CR");
		Grade cred2 = new Grade("CR");
		Grade pass = new Grade("PS");
		Grade pass2 = new Grade("PS");
		Grade fail = new Grade("FL");
		Grade fail2 = new Grade("FL");

		assertEquals(0, highd.compareTo(highd2));
		assertEquals(0, dist.compareTo(dist2));
		assertEquals(0, cred.compareTo(cred2));
		assertEquals(0, pass.compareTo(pass2));
		assertEquals(0, fail.compareTo(fail2));
	}

	@Test
	public void testGreaterThan() {
		Grade highd = new Grade("HD");
		Grade dist = new Grade("DN");
		Grade cred = new Grade("CR");
		Grade pass = new Grade("PS");
		Grade fail = new Grade("FL");
		
		assertEquals(1, highd.compareTo(dist));
		assertEquals(1, highd.compareTo(cred));
		assertEquals(1, highd.compareTo(pass));
		assertEquals(1, highd.compareTo(fail));

		assertEquals(1, dist.compareTo(cred));
		assertEquals(1, dist.compareTo(pass));
		assertEquals(1, dist.compareTo(fail));

		assertEquals(1, cred.compareTo(pass));
		assertEquals(1, cred.compareTo(fail));

		assertEquals(1, pass.compareTo(fail));
	}
	
	@Test
	public void testLessThan() {
		Grade highd = new Grade("HD");
		Grade dist = new Grade("DN");
		Grade cred = new Grade("CR");
		Grade pass = new Grade("PS");
		Grade fail = new Grade("FL");
		
		assertEquals(-1, dist.compareTo(highd));
		assertEquals(-1, cred.compareTo(highd));
		assertEquals(-1, pass.compareTo(highd));
		assertEquals(-1, fail.compareTo(highd));
			
		assertEquals(-1, cred.compareTo(dist));
		assertEquals(-1, pass.compareTo(dist));
		assertEquals(-1, fail.compareTo(dist));
			
		assertEquals(-1, pass.compareTo(cred));
		assertEquals(-1, fail.compareTo(cred));
			
		assertEquals(-1, fail.compareTo(pass));
	}
}
