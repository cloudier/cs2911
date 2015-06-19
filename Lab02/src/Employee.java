
public class Employee implements Cloneable {
	private String name;
	private int salary;
	
	/**
	 * Creates a new Employee given a name and salary.
	 * @param startEmployeeName
	 * @param startSalary
	 */
	public Employee (String startEmployeeName, int startSalary) {
		this.name = startEmployeeName;
		this.salary = startSalary;
	}
	
	/** 
	 * Sets the name of an Employee.
	 * @param employeeName The name of the employee.
	 */
	public void setEmployeeName (String employeeName) {
		this.name = employeeName;
	}
	/**
	 * Returns the name of an Employee.
	 * @return the name of the Employee
	 */
	public String getEmployeeName () {
		return name;
	}
	
	/**
	 * Sets the salary of the employee.
	 * @param salary The salary of the employee.
	 */
	public void setSalary (int salary) {
		this.salary = salary;
	}
	/**
	 * Returns the salary of the Employee.
	 * @return the salary of the Employee.
	 */
	public int getSalary () {
		return salary;
	}
	
	/**
	 * Returns a string representation of the Employee detailing the
	 * class name, employee name and employee salary.
	 * @return string representation of Employee.
	 */
	public String toString () {
		return getClass().getName() + this.name + "(" + this.salary + ")";
	}
	
	@Override
	public Object clone () {
		try {
			return super.clone();
		} catch (CloneNotSupportedException exc) {
			exc.printStackTrace();
			return null;
		}
	}
	
	@Override
	public boolean equals (Object obj) {
		if (obj == null){
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Employee other = (Employee) obj;
		return this.name == other.name && this.salary == other.salary;
	}
	
	@Override
	public int hashCode() {
		int hash = 0;
		hash += 7 * name.hashCode();
		hash += 13 * salary;
		return hash;
	}
}
