package com.gf.ex.hr.io;
/**
 * A program used to display java GUI functionalities
 * @purpose import and save different departments enter by the user from text file
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import com.gf.ex.hr.model.Department;


public class DepartmentFileIO {
	
	ArrayList<Department> departmentList;
	
	/**
	 *  Constructor to initialize the list
	 */
	public DepartmentFileIO() {
		departmentList = new ArrayList<Department>();
	}
	
	/**
	 * This is to save current employees from memory into a file.
	 * 
	 * @param empFileName
	 * @throws Exception
	 */
	public void saveDepartmentsToFile( String deptFileName ) throws Exception {
		
	PrintWriter writer = null;
	
	try{
		
	    writer = new PrintWriter (new File (deptFileName));
	    
	    for (Department each: getDepartmentList() ) {
		
		    writer.print (each.getName()+"|");
		    writer.print (each.getDescription()+"|");
		    writer.println (each.getHeadOfDept());
	    }
	    
	} catch (FileNotFoundException err) {
		System.out.println("Error finding file");
		throw new Exception( "Failed to write to " + deptFileName );
	
	} finally{
		
		if ( writer != null ) {
			writer.close();   
		}
	}
	
	System.out.print("Information saved to departmentList.txt");
	}
	
	/**
	 * This is to upload departments from a specified file name.
	 * 
	 * "departmentList.txt"
	 * 
	 * @param empFileName
	 * @throws Exception 
	 */
	public void loadDepartmentsByFileName( String deptFileName ) throws Exception {
		
	Scanner reader = null;
	//Import file 
	ArrayList<Department> listOfDepartments = new ArrayList<Department>();
	
	try {
		
	    reader = new Scanner(new BufferedReader(new FileReader(deptFileName)));
	    
	    StringTokenizer line;
	    
	    while (reader.hasNext()) {
		
		line = new StringTokenizer(reader.nextLine(),"|",false);
		String name = line.nextToken();
		String description = line.nextToken();
		String headOfDept = line.nextToken();
		Department temp = new Department(name, description, headOfDept);
		listOfDepartments.add(temp);
		
	    }
	    
	    // assign temp list into employee list in this class
	    this.departmentList = listOfDepartments;
	    
	} catch (FileNotFoundException err) {
		
		System.out.println("Error finding file");
		throw new Exception( "Failed to load from " + deptFileName );
	
	} finally {
	     if (reader != null) {
		 reader.close();
	     }
	}
	}

	/**
	 * to refresh employee age.
	 */
	
	public ArrayList<Department> getDepartmentList() {
		return departmentList;
	}

	
}
