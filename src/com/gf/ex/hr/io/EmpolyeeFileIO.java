package com.gf.ex.hr.io;
/**
 * A program used to display java GUI functionalities
 * @purpose Save or Import information of employee from text file
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.gf.ex.hr.model.Employee;


public class EmpolyeeFileIO {
	
	ArrayList<Employee> employeeList;
	int maxEmpNum = 0;

	
	/**
	 *  Constructor to initialize the list
	 */
	public EmpolyeeFileIO() {
		employeeList = new ArrayList<Employee>();
	}
	
	/**
	 * This is to save current employees from memory into a file.
	 * 
	 * @param empFileName
	 * @throws Exception
	 */
	public void saveEmployeesToFile( String empFileName ) throws Exception {
		
	PrintWriter writer = null;
	
	try{
		
	    writer = new PrintWriter (new File (empFileName));
	    
	    for (Employee each:getEmployeeList() ) {
		
		    writer.print (each.getFirstName()+"|");
		    writer.print (each.getMiddleName()+"|");
		    writer.print (each.getLastName()+"|");
		    writer.print (each.getDepartment()+"|");
		    writer.print (each.getPerformenceComment()+"|");
		    writer.print (each.getSickDays()+"|");
		    writer.print (each.getYearOfBirth()+"|");
		    writer.print (each.getMonthOfBirth()+"|");
		    writer.print (each.getDateOfBirth()+"|");
		    writer.print (each.getSinNum()+"|");
		    writer.print (each.getEmployeeNum()+"|");
		    writer.print (each.getAddress()+"|");
		    writer.print (each.getPhoneNum()+"|");
		    writer.print (each.getGender()+"|");
		    writer.println (each.getSalary());
	    }
	    
	} catch (FileNotFoundException err) {
	
		System.out.println("Error finding file");
		throw new Exception( "Failed to write to " + empFileName );
	
	} finally{
		
		if ( writer != null ) {
			writer.close();   
		}
	}
	
	System.out.print("Information saved to employeeList.txt");
	}
	
	/**
	 * This is to upload employees from a specified file name.
	 * 
	 * "employeeList.txt"
	 * 
	 * @param empFileName
	 * @throws Exception 
	 */
	public void loadEmployeesByFileName( String empFileName ) throws Exception {
		
	Scanner reader = null;
	//Import file 
	ArrayList<Employee> listOFemployee = new ArrayList<Employee>();
	
	try {
		
	    reader = new Scanner(new BufferedReader(new FileReader(empFileName)));
	    
	    StringTokenizer line;
	    
	    maxEmpNum = 0;
	    while (reader.hasNext()) {
		
		line = new StringTokenizer(reader.nextLine(),"|",false);
		String firstName = line.nextToken();
		String middleName =line.nextToken();
		String lastName =line.nextToken();
		String department = line.nextToken();
		String performenceComment = line.nextToken();
		double sickDays = Double.parseDouble(line.nextToken());
		int year = Integer.parseInt(line.nextToken());
		int month = Integer.parseInt(line.nextToken());
		int date = Integer.parseInt(line.nextToken());
		String sinNum =line.nextToken();
		String employeeNum = line.nextToken();
		String address = line.nextToken();
		String phoneNum = line.nextToken();
		String gender = line.nextToken();
		double salary = Double.parseDouble(line.nextToken());
		
		maxEmpNum = Math.max(maxEmpNum, Integer.parseInt(employeeNum));
		Employee temp = new Employee(firstName,middleName, lastName, department,
			performenceComment, sickDays, year, month, date, sinNum,
			employeeNum, address, phoneNum, gender,
			salary);
		listOFemployee.add(temp);
		
	    }
	    
	    // assign temp list into employee list in this class
	    this.employeeList = listOFemployee;
	    
	} catch (FileNotFoundException err) {
		
		System.out.println("Error finding file");
		throw new Exception( "Failed to write to " + empFileName );
	
	} finally {
	     if (reader != null) {
		 reader.close();
	     }
	}
	}

	/**
	 * to refresh employee age.
	 */
	public void recalculateEmployeeAge() {
		for ( Employee emp : getEmployeeList() ) {
			emp.calculateAgeByToday( emp.getYearOfBirth(), emp.getMonthOfBirth(), emp.getDateOfBirth() );
		}
	}
	
	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

	public void setEmployeeList(ArrayList<Employee> employeeList) {
		this.employeeList = employeeList;
	}

	public String getNextEmpNum() {
		
		String empNum = new Integer( maxEmpNum + 1 ).toString();
		return empNum;
	}
	
	
	
}
