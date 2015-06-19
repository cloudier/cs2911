package enrol;

public class Grade implements Comparable<Grade>{
	private String grade;

	public Grade(String grade) {
		this.grade = grade;
	}

	public String getGrade() {
		return grade;
	}

	@Override
	/**
	 * Order: HD > DN > CR > PS > FL
	 */
	public int compareTo(Grade g) {
		int thisGrade = 0;
		int otherGrade = 0;
		
		if (this.grade.equals("HD")) thisGrade = 5;
		if (this.grade.equals("DN")) thisGrade = 4;
		if (this.grade.equals("CR")) thisGrade = 3;
		if (this.grade.equals("PS")) thisGrade = 2;
		if (this.grade.equals("FL")) thisGrade = 1;
		
		if (g.getGrade().equals("HD")) otherGrade = 5;
		if (g.getGrade().equals("DN")) otherGrade = 4;
		if (g.getGrade().equals("CR")) otherGrade = 3;
		if (g.getGrade().equals("PS")) otherGrade = 2;
		if (g.getGrade().equals("FL")) otherGrade = 1;
		
		if (thisGrade == otherGrade) return 0;
		if (thisGrade > otherGrade) return 1;
		if (thisGrade < otherGrade) return -1;
		else {
			System.out.println("Invalid Grade " + this.grade + " compared to " + g.getGrade());
			return 0;
		}
	}
}
