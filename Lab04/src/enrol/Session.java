package enrol;

public class Session {
	private int semester;
	private int year;
	private String room;
	private int day;
	private int startTime;
	private int endTime;
	
	public Session(int semester, int year, int day, String room, int startTime,
			int endTime) {
		this.semester = semester;
		this.year = year;
		this.day = day;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
	}

	public int getSemester() {
		return semester;
	}

	public int getYear() {
		return year;
	}

	public String getRoom() {
		return room;
	}

	public int getDay() {
		return day;
	}
	
	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}
	
	public boolean doesSessionOverlap(Student s) {
		boolean sessionOverlap = false;
		for (Session n: s.getTimetable()) {
			if (this.overlapsWith(n)) {
				sessionOverlap = true;
			}
		}
		return sessionOverlap;
	}
	
	public boolean overlapsWith(Session s) {
		if (this.year != s.getYear()) return false;
		if (this.semester != s.getSemester()) return false;
		if (this.day != s.getDay()) return false;
		
		if (this.startTime <= s.getStartTime() && s.getStartTime() < this.endTime) return true;
		if (s.getStartTime() <= this.startTime && this.startTime < s.getEndTime()) return true;
		return false;
	}
}
