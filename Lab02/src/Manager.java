import java.util.Calendar;

public class Manager extends Employee {
	private Calendar hireDate;
	
	/**
	 * Creates a new Manager given a name, salary and hire date.
	 * @param startEmployeeName
	 * @param startSalary
	 * @param hireDate
	 */
	public Manager (String startEmployeeName, int startSalary, Calendar hireDate) {
		super (startEmployeeName, startSalary);
		this.hireDate = hireDate;
	}
	
	/**
	 * Sets the hire date.
	 * @param hireDate
	 */
	public void setHireDate (Calendar hireDate) {
		this.hireDate = hireDate;
	}
	/**
	 * Returns the hire date.
	 * @return the hire date of the manager as a calendar
	 */
	public Calendar getHireDate () {
		return hireDate;
	}
	
	/**
	 * Returns the hire date of the Manager as a string.
	 * @return the hire date of the Manager as a string
	 */
	public String getHireDateString () {
		int year = hireDate.get(Calendar.YEAR);
		int month = hireDate.get(Calendar.MONTH);
		int day = hireDate.get(Calendar.DAY_OF_MONTH);
		String str = String.format("%d/%d/%d", year, month, day);
		return str;
	}
	
	@Override
	public String toString () {
		return super.toString() + this.getHireDateString();
	}
	
	@Override
	public Manager clone () {
		Manager clone = (Manager) super.clone();
		clone.setHireDate(this.getHireDate());
		return clone;
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Manager other = (Manager) obj;
		return this.hireDate == other.hireDate && super.equals(other);
	}
	
	@Override
	public int hashCode() {
		int hash = super.hashCode();
		hash += 11 * hireDate.hashCode();
		return hash;
	}
}
