package enrol;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestCourse {

	@Test
	public void testCheckPrerequisites() {
		Session s1 = new Session(1, 2015, 3, "Keith Burrows", 1400, 1600);
		
		Course comp1917 = new Course("COMP1917");
		Course comp1927 = new Course("COMP1927");
		comp1927.addPrerequisite(comp1917, new Grade("PS"));
		Course comp2911 = new Course("COMP2911");
		comp2911.addPrerequisite(comp1927, new Grade("PS"));
		comp2911.addSession(s1);
		Course comp2121 = new Course("COMP2121");
		comp2121.addPrerequisite(comp1927, new Grade("PS"));
		Student claudia = new Student();
		claudia.addPastCourse(new PastCourse("COMP1917", new Grade("DN"), 2013, 1));
		claudia.addPastCourse(new PastCourse("COMP1927", new Grade("DN"), 2013, 2));
		assertTrue(comp1917.checkPrerequisites(claudia));
		assertTrue(comp1927.checkPrerequisites(claudia));
		assertTrue(comp2911.checkPrerequisites(claudia));
		assertTrue(comp2121.checkPrerequisites(claudia));

		Course comp3891 = new Course("COMP3891");
		comp3891.addPrerequisite(comp1927, new Grade("CR"));
		comp3891.addPrerequisite(comp2121, new Grade("CR"));
		assertFalse(comp3891.checkPrerequisites(claudia));
	}

}
