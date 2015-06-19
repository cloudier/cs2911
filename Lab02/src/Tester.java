import java.util.Calendar;

public class Tester {
	public static void main(String args[]) {
		
		// Set up test Employees (first, second) and Manager (third). 
		Employee first = new Employee("Bob", 1500);
		Employee second = new Employee("Bob", 1500);

		Calendar hireDate = Calendar.getInstance();
		hireDate.set(Calendar.YEAR, 2000);
		hireDate.set(Calendar.MONTH, 5);
		hireDate.set(Calendar.DATE, 22);
		Manager third = new Manager("Bob", 1500, hireDate);

		System.out.println("======== Test Employee getters and toString");
		System.out.println("Expected: class Employee, Result: " + first.getClass());
		System.out.println("Expected: 1500, Result: " + first.getSalary());
		System.out.println("Expected: Bob, Result: " + first.getEmployeeName());
		System.out.println("Expected: Employee.Bob(1500), Result: " + first.toString());
		System.out.println("Expected: class Employee, Result: " + second.getClass());
		System.out.println("Expected: 1500, Result: " + second.getSalary());
		System.out.println("Expected: Bob, Result: " + second.getEmployeeName());
		System.out.println("Expected: Employee.Bob(1500), Result: " + second.toString());

		System.out.println("======== Lab Questions");
		System.out.println("	What output do you expect when getClass().getName() is called in the toString method of Employee with a Manager object?");
		System.out.println("Expected: class Manager, Result: " + third.getClass());
		System.out.println("	What do you expect when you test whether an Employee is equal to a clone of the Employee?");
		Employee firstClone = (Employee) first.clone();
		System.out.println("Expected: true, Result: " + first.equals(firstClone));
		System.out.println("	What do you expect when you test whether a Manager is equal to an Employee with the same name and salary (and vice versa)?");
		System.out.println("Expected: false, Result: " + first.equals(third));
		System.out.println("Expected: false, Result: " + third.equals(first));
		System.out.println("	What do you expect when you test whether the name of an Employee is equal to the name of a clone of the Employee?");
		System.out.println("Expected: true, Result: " + (first.getEmployeeName() == firstClone.getEmployeeName()));
		System.out.println("	If you change the hire date of a clone of a Manager, is the hire date of the original Manager also changed?");
		Manager thirdClone = (Manager) third.clone();
		Calendar testHireDate = Calendar.getInstance();
		testHireDate.set(Calendar.YEAR, 1999);
		testHireDate.set(Calendar.MONTH, 5);
		testHireDate.set(Calendar.DATE, 22);
		thirdClone.setHireDate(testHireDate);
		System.out.println("Expected: false, Result: " + (third.getHireDate() == thirdClone.getHireDate()));
	}
}
