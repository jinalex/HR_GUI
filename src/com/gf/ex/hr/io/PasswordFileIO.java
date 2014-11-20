package com.gf.ex.hr.io;
/**
 * A program used to display java GUI functionalities
 * @purpose to protect password and able to change passowrd by the user
 * @author      Alex Jin Edward Siddharth
 * @version 1.0
 * 
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class PasswordFileIO {
	 /**
	 *  Constructor to initialize the list
	 */
	private static String password = readPasswordFromSecureFile();
	
	/**
	 * This is to upload password from a specified file name.
	 * 
	 * "password.txt"
	 * 
	 * @param empFileName
	 * @throws Exception 
	 */
	private static String readPasswordFromSecureFile() {
		
		Scanner reader = null;
		String pass = "";
		
		//Catch No File Found Error
		try {
			reader = new Scanner(new BufferedReader(new FileReader("password.txt")));
			
			while (reader.hasNext()) {
				pass = reader.nextLine();
			}
			
		} catch (FileNotFoundException err) {
			
			System.out.println("Error finding file");
			
		
		} finally {
		
		     if (reader != null) {
			 reader.close();
		     }//Reads Passowrd
		}
		
		return pass;
	}
	//Import new Password
		/**
	 * This is to save string password from memory into a file.
	 * 
	 * @param empFileName
	 * @throws Exception
	 */
	public static void writePasswordToSecureFile(String password) {
		PrintWriter writer = null;
	
	try{
		
	    writer = new PrintWriter (new File ("password.txt"));
	
		    writer.print (password);
	    
	} catch (FileNotFoundException err) {
		System.out.println("Error with file");
	
	} finally{
		
		if ( writer != null ) {
			writer.close();   
		}
	}
	
	
	}
	//Set passward to "glenforest"
	public String getPassword() {
		
		if (password.equals("")) {
			password = "glenforest";
		}
		
			return password;
	}
	
	

}

