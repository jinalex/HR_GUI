package com.gf.ex.hr.handler;
/**
 * A program used to display java GUI functionalities
 * @purpose A handler program that transfer Department information from department program to main prgram.
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gf.ex.hr.io.DepartmentFileIO;
import com.gf.ex.hr.model.Department;
import com.gf.ex.hr.model.Employee;


public class DepartmentHandler {
	/*
	* Intialize the variable that the method need 
	*/
	String deptFileName;
	static DepartmentFileIO deptFileIO = null;
	static EmployeeHandler empHandler = null;

	Map<String,List<Employee>> departmentEmployeeMap = new HashMap<String, List<Employee>>();

	public DepartmentHandler( String deptFileName ) throws Exception {
		this.deptFileName = deptFileName;
		
		deptFileIO = new DepartmentFileIO();
		
		deptFileIO.loadDepartmentsByFileName(deptFileName);
		
	}
	
	public List<Department> getDepartmentList() {
		return deptFileIO.getDepartmentList();
	}
	
	public List<String> getDepartmentNameList() {
		List<String> depNameList = new ArrayList<String>();
		
		for ( Department dep : getDepartmentList() ) {
			depNameList.add( dep.getName() );
		}
		
		return depNameList;
	}
	

	/**
	 * Retun an Department object if specified Department found;
	 * otherwise returns null
	 * 
	 * @param deptNum
	 * @return
	 */
	public Department getDepartmentByKey( String deptName ) {
		
		Department retDept = null;
		
		for( Department dept : deptFileIO.getDepartmentList() ) {
			if ( dept.getName().equals(deptName) ) { 
				retDept = dept;
				break;
			}
		}
		return retDept;
	}

	/**
	 * 
	 * @param lastName
	 * @return
	 */
	public List<Department> getDepartmentsByHead( String headOfDept ) {
		List<Department> retList = new ArrayList<Department>();
		
		for( Department dept : deptFileIO.getDepartmentList() ) {
			if ( dept.getHeadOfDept().equals(headOfDept) ) { 
				retList.add( dept );
			}
		}
		
		return retList;
	}

	public List<Department> getDepartmentsContainsKey( String name ) {
		List<Department> retList = new ArrayList<Department>();
		
		for( Department dept : deptFileIO.getDepartmentList() ) {
			if ( dept.getName().contains(name) ) { 
				retList.add( dept );
			}
		}
		
		return retList;
	}

	/**
	 * print out all departments
	 */
	public void printOutDepartments() {
		
		for( Department dept : deptFileIO.getDepartmentList() ) {
			
			System.out.println( "Department: " + dept.toString() );
		}
		
	}
	//configurate map
	public Map<String, List<Employee>> getDepartmentEmployeeMap() {
		return departmentEmployeeMap;
	}

	public void setDepartmentEmployeeMap(
			Map<String, List<Employee>> departmentEmployeeMap) {
		this.departmentEmployeeMap = departmentEmployeeMap;
	}
	//get the employee number by departments
	public int getTotalEmployeeNumberByDepName( String depName ) {
		int totalNum = 0;
		
		if ( departmentEmployeeMap.containsKey(depName) ) {
			totalNum = departmentEmployeeMap.get(depName).size();
		}
		return totalNum;
	}
	//get the employee salary by departments
	public double getTotalEmployeeSalaryByDepName( String depName ) {
		
		double totalSalary = 0;
		Map<String, List<Employee>> depEmp = getDepartmentEmployeeMap();
		
		if ( depEmp.containsKey(depName) ) {
			for ( Employee emp :  depEmp.get(depName) ) {
				totalSalary += emp.getSalary();
				
			}
		}
		return totalSalary;
	}
	//get total number of emloyee in the department
	public Map<String,Integer> getDepartmentEmployeeTotalNumMap() {
		Map<String,Integer> depEmpNumMap = new HashMap<String, Integer>();
		
		for( String key : departmentEmployeeMap.keySet() ) {
			depEmpNumMap.put( key, getTotalEmployeeNumberByDepName(key));
		}
		
		return depEmpNumMap;
	}
	
	//get total number of emloyee in the department
	public void printOutDepartmentEmployeeTotalList() {
		StringBuffer sb = new StringBuffer();
		
		Map<String,Integer> depEmpNum = getDepartmentEmployeeTotalNumMap();
		
		for( String key : depEmpNum.keySet() ) {
			sb.append( "Department Name : ").append( key ).append( "\t" )
			.append( "Total employee number : " ).append( depEmpNum.get(key) )
			.append( "\n");
		}
		
		System.out.println( sb.toString() );
		
	}
	//save all departments
	public void saveDepartment(Department dept) {
		List<Department> deptList = getDepartmentList();
		
		if (deptList.contains(dept.getName())) {
			Department existingDept = getDepartmentByKey(dept.getName());
			existingDept.setDescription( dept.getDescription() );
			existingDept.setHeadOfDept( dept.getHeadOfDept() );
		
		} else {
			deptList.add(dept);
		}
		
	}
	//remove certain department
	public void removeDepartment(Department deptToRemove) {
		List<String> deptList = getDepartmentNameList();
		if ( deptList.contains(deptToRemove.getName()) ) {
			getDepartmentList().remove(deptToRemove);
		}
	}
	//call save java file
	public void saveDepartmentToFile() throws Exception {
		deptFileIO.saveDepartmentsToFile(this.deptFileName);
	}
}
