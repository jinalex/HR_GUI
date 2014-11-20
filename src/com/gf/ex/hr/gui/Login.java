package com.gf.ex.hr.gui;
/**
 * A program used to display java GUI functionalities
 * @purpose Asks user for name, password name, and student number.
 * 			If entered correctly, sends user to main JinReportCardEntry program
 * @author 	Alex Jin 459308
 * @version 1.0
 * 
 */

import java.awt.*; 
import java.awt.event.*; 
import java.text.NumberFormat;

import javax.swing.*; 
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.gf.ex.hr.io.PasswordFileIO;
  
public class Login extends JPanel implements ActionListener {
	/*
	 * Initializes the variables used
	 */
	
	private static PasswordFileIO passwordIO = new PasswordFileIO();
	
    //public password texts
    public static String adminPass = passwordIO.getPassword();
    
    //public variables for student information
    public static String text;
    public static String passwordText;
    
    //JButton
    private JButton b;
    
    //text
	private JTextField textField;
	private JPasswordField passwordField;
	
	//button
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
    public Login() {
        setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel lblNewLabel = new JLabel("Welcome to the Human Resource Program 2.0\r\n");
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
        lblNewLabel.setBounds(49, 25, 320, 17);
        add(lblNewLabel);
        
        textField = new JTextField();
        textField.setBounds(180, 84, 130, 20);
        add(textField);
        textField.setColumns(10);
        
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setFont(new Font("Arial", Font.PLAIN, 12));
        lblPassword.setBounds(95, 139, 56, 15);
        add(lblPassword);
        
        JButton btnLogin = new JButton("Login");
        buttonGroup.add(btnLogin);
        btnLogin.setForeground(Color.DARK_GRAY);
        btnLogin.setBackground(Color.WHITE);
        btnLogin.setBounds(209, 187, 65, 26);
        btnLogin.setActionCommand("login");
        btnLogin.setMnemonic(KeyEvent.VK_L);
        btnLogin.setEnabled(true);
        btnLogin.addActionListener(this);
        
        JLabel lblUserid = new JLabel("UserID");
        lblUserid.setFont(new Font("Arial", Font.PLAIN, 12));
        lblUserid.setBounds(95, 87, 39, 15);
        add(lblUserid);
        add(btnLogin);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(180, 136, 130, 20);
        add(passwordField);
        passwordField.setText("");
                
        //Add Components to this panel. 

        
        //Initial text
        textField.setText("");
                 
    }
    
    public static boolean hasInfo(String user, String password){
    	/*
    	 * @param String user name
    	 * @param String password name
    	 * this method checks if the user entered any info
    	 */
    	if (user.equals("") && password.equals("")){
    		return false;
    	}
    	else{
    		return true;
    	}   	
    }
  
    @SuppressWarnings("deprecation")
	public void actionPerformed(ActionEvent evt) {
    	/*
    	 * @param ActionEvent e
    	 * this method checks if an action was performed
    	 */
    	
    	//sets text fields
        text = textField.getText();
        textField.selectAll();
        
        passwordText = passwordField.getText();
        passwordField.selectAll();
        
        //if the button is pressed and fields have info, proceed to main program
        if ("login".equals(evt.getActionCommand()) && hasInfo(text, passwordText) && validPassword(text, passwordText)) {
                String[] args = null;
                MainGUI.main(args);
                textField.setBackground(Color.white);
                passwordField.setBackground(Color.white);
        }
       
        //Demands a user input sends a error dialog
        
        else if ("login".equals(evt.getActionCommand())){
        	textField.setBackground(Color.PINK);
            passwordField.setBackground(Color.pink);
            Component frame = null;
			JOptionPane.showMessageDialog(frame, "Please enter your login info.");
        }

    }
    
    /** 
     * Create the GUI and show it.  For thread safety, 
     * 
     * this method should be invoked from the 
     * event dispatch thread. 
     */
    public static void createAndShowGUI() { 
        //Create and set up the window. 
        JFrame frame = new JFrame("Student Login"); 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); 
  
        //Add contents to the window. 
        frame.getContentPane().add(new Login()); 
  
        //Display the window.
        frame.getContentPane().setPreferredSize(new Dimension(420, 280));
        frame.pack(); 
        frame.setVisible(true);
        frame.setResizable(false);
    }
    
    
  
    public static void main(String[] args) { 
        //Schedule a job for the event dispatch thread: 
        //creating and showing this application's GUI.
    	try {
            // Set cross-platform Java L&F (also called "Metal")
    		 UIManager.setLookAndFeel(
    		            UIManager.getCrossPlatformLookAndFeelClassName());
    		 
    		    } 
    		    catch (UnsupportedLookAndFeelException e) {
    		       // handle exception
    		    }
    		    catch (ClassNotFoundException e) {
    		       // handle exception
    		    }
    		    catch (InstantiationException e) {
    		       // handle exception
    		    }
    		    catch (IllegalAccessException e) {
    		       // handle exception
    		    }
    	
        javax.swing.SwingUtilities.invokeLater(new Runnable() { 
            public void run() { 
                createAndShowGUI(); 
            } 
        }); 
    }
	
    private static boolean validPassword(String user, String pass) {
		if (text.equals("admin") && passwordText.equals(adminPass)){
			return true;
		}
		
    	return false;
    }

}
