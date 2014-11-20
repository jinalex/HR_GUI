/**
 * A program used to display java GUI functionalities
 * @purpose employee class contian all the method for employee class
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
 
package com.gf.ex.hr.model;
import java.util.Calendar;


public class Employee {
	/*
	*Initialize the variable
	*/
     
	String firstName;
	String middleName;
	String lastName;
	String performenceComment;
	double sickDays;

	String department;
	
		String sinNum;
	String employeeNum;
	String address;
	String phoneNum;
	String gender;
	double salary;

	
		int yearOfBirth;
	int monthOfBirth;
	int dateOfBirth;
	//methods of enployee class
	public Employee() {}
	//set all information
	public Employee(String firstName, String middleName, String lastName, String department,
			String performenceComment, double sickDays, int yearOfBirth, int monthOfBirth, int dateOfBirth, String sinNum,
			String employeeNum, String address, String phoneNum, String gender,
			double salary) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.department = department;
		this.performenceComment = performenceComment;
		this.sickDays = sickDays;
		this.yearOfBirth = yearOfBirth;
		this.monthOfBirth = monthOfBirth;
		this.dateOfBirth = dateOfBirth;
		this.sinNum = sinNum;
		this.employeeNum = employeeNum;
		this.address = address;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.salary = salary;
	}
	//change information to string
	public String toString() {
		StringBuffer retSb = new StringBuffer();
		
		retSb.append( "Employee Num : ").append( employeeNum )
			.append( " Last Name : " ).append( lastName )
				.append( " First Name : " ).append( firstName )
				.append( " Department Name : " ).append( department )
				.append( " Age : " ).append( getAgeByToday(yearOfBirth, monthOfBirth, dateOfBirth) );
		
		return retSb.toString();
	}
	//get first name
	public String getFirstName() {
		return firstName;
	}
	//set first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	//get middle name
	public String getMiddleName() {
		return middleName;
	}
	//set middle name
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	//get last name
	public String getLastName() {
		return lastName;
	}
	//set last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	//check if birthday is valid
	boolean isValidBirthday( int year, int month, int day ) {
		boolean result = true;
		
		if ( year <= 0 || month <= 0 || day <= 0 ) {
			result = false;
		}  else if ( month > 12 ) {
			result = false;
		}
		
		int daysInMo = 0;
		boolean leapYear;
		
		if (year%4 == 0 && year%100 != 0 || year%400 == 0)
			leapYear = true;
		else
			leapYear = false;
		
		if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)
			daysInMo = 31;
	    
		else if (month == 4 || month == 6 || month == 9 || month == 11)
			daysInMo = 30;

		if (month == 2 && leapYear == true)
			daysInMo = 29;

		else if(month == 2)
			daysInMo = 28;

		//User number of days in month to check to see if day is valid

		if (day > daysInMo)

		    result = false;
		
		return result;
	}
	
	public int calculateAgeByToday( int year, int month, int day ) {
		if ( ! isValidBirthday( year, month, day) ) return 0;
		//set up date of birth  
		Calendar dob = Calendar.getInstance();  
		dob.set( year, month, day );  
		//setup calNow as today.  
		Calendar now = Calendar.getInstance();  
		now.setTime(new java.util.Date());  
		//calculate age in years.  
		int age = (now.get(Calendar.YEAR) - dob.get(Calendar.YEAR)) + 1;  
		// calculate additional age in months, possibly adjust years.  
		int ageMo = (now.get(Calendar.MONTH) - dob.get(Calendar.MONTH));  
		if (ageMo < 0)  
		{  
			//adjust years by subtracting one  
			age--;  
		}  
		return age;  
	}
	
	public String getAgeByToday(int year, int month, int day) {
		final String errMsg = "Error; Invalid birthday. year:"+year+" month:"+month+" date:"+day;

		if ( ! isValidBirthday(year,month,day) ) {
			return errMsg;
		} else {
			int age = calculateAgeByToday( year,month,day );
		return Integer.toString(age);
		}
	}
	
	public String getSinNum() {
		return sinNum;
	}
	public void setSinNum(String sinNum) {
		this.sinNum = sinNum;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public String getGender() {
		
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public double getSalary() {
		return salary;
	}


	public void setSalary(double salary) {
		this.salary = salary;
	}





	public String getPerformenceComment() {
		return performenceComment;
	}


	
	public void setPerformenceComment(String performenceComment) {
		this.performenceComment = performenceComment;
	}
	public double getSickDays() {
		return sickDays;
	}
	public void setSickDays(double sickDays) {
		this.sickDays = sickDays;
	}
	
	public int getYearOfBirth() {
			return yearOfBirth;
		}
		public void setYearOfBirth(int yearOfBirth) {
			this.yearOfBirth = yearOfBirth;
		}
		public int getMonthOfBirth() {
			return monthOfBirth;
		}
		public void setMonthOfBirth(int monthOfBirth) {
			this.monthOfBirth = monthOfBirth;
		}
		public int getDateOfBirth() {
			return dateOfBirth;
		}
		public void setDateOfBirth(int dateOfBirth) {
			this.dateOfBirth = dateOfBirth;
			
		}
	
		public String getDepartment() {
			return department;
		}

		public void setDepartment(String department) {
			this.department = department;
		}//methods
}
