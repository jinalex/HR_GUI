package com.gf.ex.hr.handler;
/**
 * A program used to display java GUI functionalities
 * @purpose A handler program that transfer employee information from department program to main prgram.
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gf.ex.hr.io.EmpolyeeFileIO;
import com.gf.ex.hr.model.Department;
import com.gf.ex.hr.model.Employee;


public class EmployeeHandler {
	
	String empFileName;
	static EmpolyeeFileIO empFileIo = null;

	public EmployeeHandler( String empFileName ) throws Exception {
		this.empFileName = empFileName;
		
		empFileIo = new EmpolyeeFileIO();
		
		empFileIo.loadEmployeesByFileName(empFileName);
	}
	
	/**
	 * Retun an employee object if specified employee found;
	 * otherwise returns null
	 * 
	 * @param empNum
	 * @return
	 */
	public Employee getEmployeeByKey( String empNum ) {
		
		Employee retEmp = null;
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if ( emp.getEmployeeNum().equals(empNum) ) { 
				retEmp = emp;
				break;
			}
		}
		return retEmp;
	}
	
	public List<Employee> getEmployeeList() {
		return empFileIo.getEmployeeList();
	}

	/**
	 * Retun an employee object if specified employee found;
	 * otherwise returns null
	 * @param lastName
	 * @return
	 */
	public List<Employee> getEmployeesByLastName( String lastName ) {
		List<Employee> retList = new ArrayList<Employee>();
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if ( emp.getEmployeeNum().equals(lastName) ) { 
				retList.add( emp );
			}
		}
		
		return retList;
	}

	/**
	 * Retun an employee object if specified employee found;
	 * otherwise returns null
	 * @param lastName
	 * @return
	 */
	public List<Employee> getEmployeesContainLastName( String lastName ) {
		List<Employee> retList = new ArrayList<Employee>();
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if ( emp.getEmployeeNum().contains(lastName) ) { 
				retList.add( emp );
			}
		}
		
		return retList;
	}

	 /**
	 * Retun an employee object if specified employee found;
	 * otherwise returns null
	 * @param firstName
	 * @return
	 */
	public List<Employee> getEmployeesByFirstName( String firstName ) {
		List<Employee> retList = new ArrayList<Employee>();
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if ( emp.getEmployeeNum().equals(firstName) ) { 
				retList.add( emp );
			}
		}
		
		return retList;
	}

	public List<Employee> getEmployeesContainFirstName( String firstName ) {
		List<Employee> retList = new ArrayList<Employee>();
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if ( emp.getEmployeeNum().contains(firstName) ) { 
				retList.add( emp );
			}
		}
		
		return retList;
	}

	/**
	 * print out all employees
	 */
	public void printOutEmployees() {
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			
			System.out.println( "Employee : " + emp.toString() );
		}
		
	}
	
	public Map<String,List<Employee>> getDepartmentEmployees() {
		Map<String, List<Employee>> depEmployeeMap = new HashMap<String, List<Employee>>();
		
		for( Employee emp : empFileIo.getEmployeeList() ) {
			if( ! depEmployeeMap.containsKey(emp.getDepartment()) ) {
				List<Employee> empList = new ArrayList<Employee>();
				empList.add( emp );
				depEmployeeMap.put( emp.getDepartment(), empList );
			} else {
				depEmployeeMap.get( emp.getDepartment() ).add( emp );
			}
		}
		
		return depEmployeeMap;
	}

	public void saveEmployeeToFile() throws Exception {
		empFileIo.saveEmployeesToFile(this.empFileName);
	}
	/*
	*save all employee information that the user entered
	*/
	public String saveEmployee(Employee emp) {
		List<Employee> empList = empFileIo.getEmployeeList();
		String empNum = "";

		if ( emp.getEmployeeNum() == null || emp.getEmployeeNum().isEmpty() ) {
			//New employee
			empNum = empFileIo.getNextEmpNum();
			emp.setEmployeeNum( empNum );
			empList.add( emp );
		} else {
			empNum = emp.getEmployeeNum();
			boolean updatedFlag = false;
			for ( Employee e : empList ) {
				if ( e.getEmployeeNum().equals( emp.getEmployeeNum()) ) {
					e.setFirstName(  emp.getFirstName() );
					e.setMiddleName(emp.getMiddleName() );
					e.setLastName( emp.getLastName() );
					e.setDepartment( emp.getDepartment() );
					e.setSinNum ( emp.getSinNum() );
					e.setAddress( emp.getAddress() );
					e.setPhoneNum( emp.getPhoneNum() );
					e.setGender( emp.getGender() );
					e.setPerformenceComment( emp.getPerformenceComment() );
					e.setSickDays( emp.getSickDays() );
					e.setSalary( emp.getSalary() );
					e.setYearOfBirth( emp.getYearOfBirth() );
					e.setMonthOfBirth( emp.getMonthOfBirth() );
					e.setDateOfBirth( emp.getDateOfBirth() );

				}
			}
		}
		return empNum;
		
	}
	//delete employee
	public void removeEmployee(Employee employee) {
		if (empFileIo.getEmployeeList().contains(employee)) {
			empFileIo.getEmployeeList().remove(employee);
		}
	}
}
