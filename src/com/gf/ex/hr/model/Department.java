package com.gf.ex.hr.model;
/**
 * A program used to display java GUI functionalities
 * @purpose Department Class that have all the functions of the department class
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.util.ArrayList;


public class Department {
	/*
	*Intiailize 
	*/
	
	String name;
	String description;
	String headOfDept;

		int totalEmps;
	double totalSalary;
	/*department methods
	*/
	public Department() {
	}
	
	public Department(String name, String description, String headOfDept) {
		super();
		this.name = name;
		this.description = description;
		this.headOfDept = headOfDept;
	}
	//change the information to string
	public String toString() {
		StringBuffer retSb = new StringBuffer();
		
		retSb.append( "Department Name: ").append( name )
			.append( "\t" )
				.append( " Head of Department: " ).append( headOfDept )
				.append( "\t" ); 
																	
		
		return retSb.toString();
	}
	//get the name 
	public String getName() {
		return name;
	}
	//set the name
	public void setName(String name) {
		this.name = name;
	}
	//sents department description information
	public String getDescription() {
		return description;
	}
	//set department description information
	public void setDescription(String description) {
		this.description = description;
	}
	//get head of department
	public String getHeadOfDept() {
		return headOfDept;
	}
	//set head of department
	public void setHeadOfDept(String headOfDept) {
		this.headOfDept = headOfDept;
	}
	//get total number of employee
	public int getTotalEmps(ArrayList<Employee> employees) {
			return employees.size();
	}
	//ger salary
	public double getTotalSalary(ArrayList<Employee> employees) {
		double totalSalary = 0;
		
		for (Employee each: employees){
			totalSalary += each.salary;
		}
		
		return totalSalary;
	}
	
	//override method
	@Override
	public boolean equals( Object obj ) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		final Department other = (Department) obj;
		if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
			return false;
		}
		if ( ! this.headOfDept.equals(other.headOfDept) ) {
			return false;
		}
		if ( ! this.description.equals(other.description) ) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + (this.name != null ? this.name.hashCode() : 0);
		return hash;
	}
  

}
