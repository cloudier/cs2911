package enrol;

public class PastCourse {
	private String courseCode;
	private Grade grade;
	private int year;
	private int semester;
	
	public PastCourse(String courseCode, Grade grade, int year, int semester) {
		this.courseCode = courseCode;
		this.grade = grade;
		this.year = year;
		this.semester = semester;
	}

	public String getCourseCode() {
		return courseCode;
	}

	public Grade getGrade() {
		return grade;
	}

	public int getYear() {
		return year;
	}

	public int getSemester() {
		return semester;
	}
}
