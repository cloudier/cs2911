package enrol;

import java.util.*;

public class Course {
	private String courseCode;
	private HashMap<Course, Grade> prerequisites;
	private List<Student> students;
	private HashSet<Session> sessions;
	
	public Course(String courseCode) {
		this.courseCode = courseCode;
		this.prerequisites = new HashMap<Course, Grade>();
		this.students = new ArrayList<Student>();
		this.sessions = new HashSet<Session>();
	}

	public String getCourseCode() {
		return courseCode;
	}

	public HashMap<Course, Grade> getPrerequisites() {
		return prerequisites;
	}
	
	public void addPrerequisite(Course c, Grade g) {
		prerequisites.put(c, g);
	}

	public List<Student> getStudents() {
		return students;
	}
	
	public void addStudent(Student s) {
		students.add(s);
	}

	public HashSet<Session> getSessions() {
		return sessions;
	}
	
	public void addSession(Session s) {
		sessions.add(s);
	}
	
	public boolean doesSessionExist(Session s) {
		return sessions.contains(s);
	}
	
	public boolean checkPrerequisites(Student s){
		for (Course c: prerequisites.keySet()) {
			if(!s.getPastCourses().containsKey(c.getCourseCode())) return false;
			if(s.getPastCourses().containsKey(c.getCourseCode()) &&
					s.getGrade(c.getCourseCode()).compareTo(prerequisites.get(c)) == -1) return false;
		}
		return true;
	}
}
