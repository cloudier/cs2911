package enrol;

import java.util.*;

public class Student {
	private List<Session> timetable;
	private HashMap<String, PastCourse> pastCourses; // string is the course code
	
	public Student() {
		this.timetable = new ArrayList<Session>();
		this.pastCourses = new HashMap<String, PastCourse>();
	}
	
	public List<Session> getTimetable() {
		return timetable;
	}
	
	public void addTimetable(Session s) {
		timetable.add(s);
	}
	
	public HashMap<String, PastCourse> getPastCourses() {
		return new HashMap<String, PastCourse>(pastCourses);
	}
	
	public void addPastCourse(PastCourse p) {
		pastCourses.put(p.getCourseCode(), p);
	}
	
	public Grade getGrade(String courseCode) {
		return pastCourses.get(courseCode).getGrade();
	}
	
	public boolean enrol(Course c, Session s) {
		if (!c.doesSessionExist(s)) return false;
		if (!c.checkPrerequisites(this)) return false;
		if (s.doesSessionOverlap(this)) return false;
		
		c.addStudent(this);
		this.addTimetable(s);
		return true;
	}
}
