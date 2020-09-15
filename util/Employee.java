package util;

import java.time.LocalDate;

public class Employee implements Comparable<Employee> {

	private static int idSrc;
	
	private int id;
	private String firstName;
	private String lastName;
	private String department;
	private double salary;
	private LocalDate birthDate;
	private String eMail;
	private String companyName;
	
	public Employee() {
		this.id = ++idSrc;
	}
	
	public Employee(String firstName, String lastName, String department, double salary, LocalDate birthDate, String eMail, String companyName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.salary = salary;
		this.birthDate = birthDate;
		this.eMail = eMail;
		this.companyName = companyName;
		
		this.id = ++idSrc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Employee))
			return false;
		Employee other = (Employee) obj;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", birthDate=" + birthDate
				+ ", eMail=" + eMail + "]";
	}

//	@Override
//	public String toString() {
//		return "Employee [firstName=" + firstName + ", lastName=" + lastName + "]";
//	}
	
	
	@Override
	public int compareTo(Employee o) {
		
		if ( this.lastName.equals(o.getLastName()) ) {
			if ( this.firstName.contentEquals(o.getFirstName()) ) {
				return this.birthDate.compareTo(o.getBirthDate());
			}else {
				return this.firstName.compareTo(o.getFirstName());
			}
		}else {
			return this.lastName.compareTo(o.getLastName());
		}
		
	}


	
	
}
