package enrol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestStudent {

	@Test
	public void testEnrol() {
		Session s1 = new Session(1, 2015, 3, "Keith Burrows", 1400, 1600);
		
		@SuppressWarnings("unused")
		Course comp1917 = new Course("COMP1917");
		Course comp1927 = new Course("COMP1927");
		Course comp2911 = new Course("COMP2911");
		comp2911.addSession(s1);
		Course comp2121 = new Course("COMP2121");
		Student claudia = new Student();
		claudia.addPastCourse(new PastCourse("COMP1917", new Grade("DN"), 2013, 1));
		claudia.addPastCourse(new PastCourse("COMP1927", new Grade("DN"), 2013, 2));

		Course comp3891 = new Course("COMP3891");
		comp3891.addPrerequisite(comp1927, new Grade("CR"));
		comp3891.addPrerequisite(comp2121, new Grade("CR"));

		assertTrue(comp2911.doesSessionExist(s1));
		assertTrue(comp2911.checkPrerequisites(claudia));
		assertFalse(s1.doesSessionOverlap(claudia));
		assertTrue(claudia.enrol(comp2911, s1));
	}

}
