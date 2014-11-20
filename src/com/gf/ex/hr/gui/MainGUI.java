package com.gf.ex.hr.gui;

/**
 * A program used to display java GUI functionalities
 * @purpose allow user to add, edit ,remove and employee information data stored in text file
 * @author      Alex Jin 
 * @version 2.0
 * 
 */
 
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import com.gf.ex.hr.handler.DepartmentHandler;
import com.gf.ex.hr.handler.EmployeeHandler;
import com.gf.ex.hr.io.PasswordFileIO;
import com.gf.ex.hr.model.Department;
import com.gf.ex.hr.model.Employee;

import javax.swing.ImageIcon;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JMenu;


public class MainGUI extends JFrame {
	 /*
	 * Initializes the variabls used
	 */
	 //frame
	static private MainGUI frame = null;
	//Panel, list of department and employee text field
	private JPanel contentPane;
	private JTextField txtEmpSearch;
	private JList listDepartment;
	
	//Tab Pane
	private JTabbedPane tabbedPane;
	
	//Handler
	private static EmployeeHandler empHandler = null;
	private static DepartmentHandler departmentHandler = null;
	private static PasswordFileIO passwordIO = null;
	
	//String
	private static String password;

	//Text Field
	private JTextField txtDDDeptName;
	private JTextField txtDDHeadOfDept;
	private JTextArea  txtDDDeptDesc;
	private JTextField txtEDfirstName;
	private JTextField txtEDmiddleName;
	private JTextField txtEDlastName;
	private JTextField txtEDdepartment;
	private JTextField txtED_SIN;
	private JTextField txtEDaddress;
	private JTextField txtEDphoneNum;
	private JTextField txtEDgender;
	private JTextArea txtAreaEDcomments;
	private JFormattedTextField ftxtEDsickDays;
	private JFormattedTextField ftxtEDsalary;
	private JLabel ageLabel;
	private JFormattedTextField ftxtEDyear;
	private JComboBox comboBoxEDmonth;
	private JComboBox comboBoxEDday;
	private JList listDLdeptList;
	
	//Panel
	private JPanel deptListTab;
	private JPanel empListTab;
	//Label
	private JLabel empIDlbl;
	
	//Combo Box
	private JComboBox deptComboBox;
	
	private boolean readOnlyFlag = true;
	//Set Department table
	public static class DepartmentTableModel extends AbstractTableModel
	{
	    //Intiatialize Array
	    private List<String> columnNames = new ArrayList<String>();
	    private List<List<Object>> data = new ArrayList<List<Object>>();

	    {
		columnNames.add("Name");
		columnNames.add("Head Of Department");
		columnNames.add("Employee population");
		columnNames.add("Employee expense ($)");
	    }
	    //get size and information
	    public void addRow(List<Object> rowData)
	    {
		data.add(rowData);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	    }
	
	    public int getColumnCount()
	    {
		return columnNames.size();
	    }

	    public int getRowCount()
	    {
		return data.size();
	    }

	    public String getColumnName(int col)
	    {
		try
		{
		    return columnNames.get(col);
		}
		catch(Exception e)
		{
		    return null;
		}
	    }

	    public Object getValueAt(int row, int col)
	    {
		return data.get(row).get(col);
	    }

	    public boolean isCellEditable(int row, int col)
	    {
		return false;
	    }

	    public Class getColumnClass(int c)
	    {
		return getValueAt(0, c).getClass();
	    }
	    
	    
	};
	//Set Employee table
	public static class EmployeeTableModel extends AbstractTableModel
	{
	    private List<String> columnNames = new ArrayList<String>();
	    private List<List<Object>> data = new ArrayList<List<Object>>();

	    {
		columnNames.add("Employee ID");
		columnNames.add("First Name");
		columnNames.add("Last Name");
		columnNames.add("Department");
		columnNames.add("Gender");
		columnNames.add("Age");
		columnNames.add("Salary ($)");

	    }
	    //get size and information
	    public void addRow(List<Object> rowData)
	    {
		data.add(rowData);
		fireTableRowsInserted(data.size() - 1, data.size() - 1);
	    }

	    public int getColumnCount()
	    {
		return columnNames.size();
	    }

	    public int getRowCount()
	    {
		return data.size();
	    }

	    public String getColumnName(int col)
	    {
		try
		{
		    return columnNames.get(col);
		}
		catch(Exception e)
		{
		    return null;
		}
	    }

	    public Object getValueAt(int row, int col)
	    {
		return data.get(row).get(col);
	    }

	    public boolean isCellEditable(int row, int col)
	    {
		return false;
	    }

	    public Class getColumnClass(int c)
	    {
		return getValueAt(0, c).getClass();
	    }
	};
	
	
	public class SortAction implements ActionListener {

	private JTable table;
	private int column;

	public SortAction(JTable table, int column) {
	    this.table = table;
	    this.column = column;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	/*Action Listener
	*/
	    RowSorter<? extends TableModel> rowSorter = table.getRowSorter();
	    List<RowSorter.SortKey> sortKeys = new ArrayList<RowSorter.SortKey>(rowSorter.getSortKeys());
	    boolean found = false;
	    SortOrder order = SortOrder.ASCENDING;
	    for (int index = 0; index < sortKeys.size(); index++) {
		RowSorter.SortKey key = sortKeys.get(index);
		if (key.getColumn() == column) {
		    found = true;
		    System.out.println("Found existing sort key for column " + column);
		    switch (key.getSortOrder()) {
			case ASCENDING:
			    order = SortOrder.DESCENDING;
			    sortKeys.set(index, new RowSorter.SortKey(column, order));
			    break;
			case DESCENDING:
			    order = SortOrder.UNSORTED;
			    sortKeys.remove(index);
			    break;
		    }
		    break;
		}
	    }
	    if (!found) {
		System.out.println("Add new sort key for column " + column);
		sortKeys.add(new RowSorter.SortKey(column, order));
	    }
	    System.out.println("List contains " + sortKeys.size());
	    RowSorter newSorter = new TableRowSorter(table.getModel());
	    newSorter.setSortKeys(sortKeys);
	    table.setRowSorter(newSorter);
	    switch (order) {
		case ASCENDING:
		    ((JButton) e.getSource()).setText("+");
		    break;
		case DESCENDING:
		    ((JButton) e.getSource()).setText("-");
		    break;
		default:
		    ((JButton) e.getSource()).setText(" ");
		    break;
	    }
	}
    }   
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		try {
			
			/*
			 * Build employee & Department handler
			 */
			passwordIO = new PasswordFileIO();
			
			password = passwordIO.getPassword();
			
			empHandler = new EmployeeHandler( "employeeList.txt" );
			departmentHandler = new DepartmentHandler( "departmentList.txt" );
			
			departmentHandler.setDepartmentEmployeeMap( empHandler.getDepartmentEmployees() );
			
			departmentHandler.printOutDepartmentEmployeeTotalList();
			
		} catch (Exception e1) {
			
			e1.printStackTrace();
			errorMessageBox( "Failed to build Employee list.",  e1.getMessage() );
			System.out.println( "Failed to build Employee list. Msg: " + e1.getMessage() );
		}
		
		empHandler.printOutEmployees();
		
		
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainGUI();
					frame.setVisible(true);
					
					frame.setupFrame();
					
				} catch (Exception e) {
					e.printStackTrace();
					errorMessageBox( "Failed to build Human Resource GUI", e.getMessage() );
				}
			}
		});
	}
	
	private void refreshDepList (){
		/*
		 * Setup department name list.
		 */
		List<String> depList = departmentHandler.getDepartmentNameList();
		String [] depNameArray = new String[depList.size()];
		setDepartmentList( depList.toArray(depNameArray) );
	}

	private void setupFrame() {
		
		//reload department list on left
		refreshDepList ();
		/*
		 * Setup department tab list
		 */
		ArrayList<String> depString = new ArrayList<String>();
		for( Department dept : departmentHandler.getDepartmentList() ) {
			depString.add( dept.toString()
					+"\t"
					+"Employee Population: "+departmentHandler.getTotalEmployeeNumberByDepName(dept.getName())
					+"\t"
					+"Total Employee Expense: $"+departmentHandler.getTotalEmployeeSalaryByDepName(dept.getName()));
		}
		
		List<String> depList = departmentHandler.getDepartmentNameList();
		String [] depNameArray = new String[depList.size()];
		String [] deptList = new String[depList.size()];
		fillDepartmentList ( depString.toArray(deptList) );
		
		/*
		 * Setup department table.
		 */
		
		populateDepartmentTab();
		
		/*
		 * Setup employee table.
		 */
		populateEmployeeTab();
	}
	
	
	
	private Employee getEmployeeInfoFromGUI(){
		/*
		 * Reads in user information from the GUI
		 */
		Employee emp = new Employee();
		
		emp.setEmployeeNum( empIDlbl.getText() );
		emp.setFirstName(  txtEDfirstName.getText() );
		emp.setMiddleName( txtEDmiddleName.getText() );
		emp.setLastName( txtEDlastName.getText() );
		//emp.setDepartment( txtEDdepartment.getText() );
		emp.setDepartment( deptComboBox.getSelectedItem().toString() );
		emp.setSinNum ( txtED_SIN.getText() );
		emp.setAddress( txtEDaddress.getText() );
		emp.setPhoneNum( txtEDphoneNum.getText() );
		emp.setGender( txtEDgender.getText() );
		emp.setPerformenceComment( txtAreaEDcomments.getText() );
		try {
			emp.setSickDays( Double.parseDouble(ftxtEDsickDays.getText()) );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in sick day input. Use numbers please.", "Error", DISPOSE_ON_CLOSE);
		}
		try {
			emp.setSalary( Double.parseDouble(ftxtEDsalary.getText()) );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in salary input. Use appropriate number format.", "Error", DISPOSE_ON_CLOSE);
		}
		try {
			emp.setYearOfBirth( Integer.parseInt(ftxtEDyear.getText()) );
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in year input. Enter valid years please.", "Error", DISPOSE_ON_CLOSE);
		}
		emp.setMonthOfBirth( comboBoxEDmonth.getSelectedIndex()+1 );
		emp.setDateOfBirth( comboBoxEDday.getSelectedIndex()+1 );
		
		return emp;
	}
	
	private Department getDepartmentInfoFromGUI(){
		
		Department dept = new Department();
		
		dept.setName( txtDDDeptName.getText() );
		dept.setDescription( txtDDDeptDesc.getText() );
		dept.setHeadOfDept( txtDDHeadOfDept.getText() );
		return dept;
	}
	
	private void populateDepartmentTab() {
		/*
		 * loads information into the department list tab
		 */
		
		deptListTab.removeAll();
		DepartmentTableModel model = new DepartmentTableModel();

	    for ( Department dept :  departmentHandler.getDepartmentList() ) {
		Object[] array = new Object[4];
		array[0] = dept.getName();
		array[1] = dept.getHeadOfDept();
		array[2] = new Integer( departmentHandler.getTotalEmployeeNumberByDepName(dept.getName()) );
		array[3] = new Double( departmentHandler.getTotalEmployeeSalaryByDepName(dept.getName()) );

		model.addRow(Arrays.asList(array));
	    }

	    final JTable table = new JTable(model);
	    table.setAutoCreateRowSorter(true);
	    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    table.setFillsViewportHeight(true);
	    table.getTableHeader().setReorderingAllowed(false);
	   
	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
		

			@Override
			public void valueChanged(ListSelectionEvent arg0) {

				String deptName = table.getValueAt(table.getSelectedRow(), 0 ).toString();
				Department selectedDept = departmentHandler.getDepartmentByKey(deptName);
				populateDepartmentDetails(selectedDept, readOnlyFlag);
				
			}
	    });

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(5, 5, deptListTab.getSize().width-10, deptListTab.getSize().height-10);
	    //now adding this to the frame where I want to show 
	    deptListTab.add( scrollPane );
	}
	
	private void populateEmployeeTab() {
		
		/*
		 * loads information into employee list tab
		 */
		
		empListTab.removeAll();
		EmployeeTableModel model = new EmployeeTableModel();

	    for ( Employee emp :  empHandler.getEmployeeList() ) {
		Object[] array = new Object[7];
		array[0] = emp.getEmployeeNum();
		array[1] = emp.getFirstName();
		array[2] = emp.getLastName();
		array[3] = emp.getDepartment();
		array[4] = emp.getGender();
		array[5] = new Integer( emp.calculateAgeByToday(emp.getYearOfBirth(), emp.getMonthOfBirth(), emp.getDateOfBirth()) );
		array[6] = new Double( emp.getSalary() );

		model.addRow(Arrays.asList(array));
	    }

	    final JTable table = new JTable(model);
	    table.setAutoCreateRowSorter(true);
	    table.setPreferredScrollableViewportSize(new Dimension(500, 70));
	    table.setFillsViewportHeight(true);
	    table.getTableHeader().setReorderingAllowed(false);
	    
	    table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				
				String empNum = table.getValueAt(table.getSelectedRow(), 0).toString();
				Employee selectedEmp = empHandler.getEmployeeByKey(empNum);
				populateEmployeeDetails(selectedEmp, readOnlyFlag);
				
			}
	    });

	    JScrollPane scrollPane = new JScrollPane(table);
	    scrollPane.setBounds(5, 5, empListTab.getSize().width-10, empListTab.getSize().height-10);
	    //now adding this to the frame where I want to show 
	    empListTab.add( scrollPane );
	}
	
	/**
	 * 
	 * @param depNameList
	 */
	private void setDepartmentList( final String[] depNameList ) {
	
		/*
		 * sets up department list on the left and handles department selection
		 */
		
		listDepartment.setListData(depNameList);
		listDepartment.addListSelectionListener(new ListSelectionListener() {
		
			public void valueChanged(ListSelectionEvent arg0) {
				
				System.out.println( "Item selected"+arg0.getFirstIndex() +"  "+arg0.getLastIndex() + "   " +listDepartment.getSelectedValue().toString());
				
				Department selectedDept = departmentHandler.getDepartmentByKey(listDepartment.getSelectedValue().toString());
				System.out.println( "Selected Department : \n" + selectedDept);
				
				populateDepartmentDetails(selectedDept, readOnlyFlag);
			}
		});

	}
	
	protected void fillDepartmentList ( final String[] depList ) {
		
		/*
		 *  loads information into department list and handles selection
		 */
		
		listDLdeptList.setListData(depList);
		listDLdeptList.addListSelectionListener(new ListSelectionListener() {
		
			public void valueChanged(ListSelectionEvent arg0) {
				
				System.out.println( "Item selected"+arg0.getFirstIndex() +"  "+arg0.getLastIndex() + "   " +listDLdeptList.getSelectedValue().toString());
				
				
				
				Department selectedDept = departmentHandler.getDepartmentByKey(departmentHandler.getDepartmentList().get(arg0.getFirstIndex()).getName());
				System.out.println( "Selected Department : \n" + selectedDept);
				
				populateDepartmentDetails(selectedDept, readOnlyFlag);
			}
		});
		
	}
	
	protected void populateDepartmentDetails(Department department,
			boolean readOnlyFlag) {
		
		/*
		 * shows details of a department in the department details tab
		 */

		txtDDDeptName.setText(department.getName());
		txtDDDeptDesc.setText(department.getDescription());
		txtDDHeadOfDept.setText(department.getHeadOfDept());
		
		txtDDDeptName.setEditable( ! readOnlyFlag );
		txtDDDeptDesc.setEditable( ! readOnlyFlag );
		txtDDHeadOfDept.setEditable( ! readOnlyFlag );

		tabbedPane.setSelectedIndex(2);

	}
	
	protected void populateEmployeeDetails(Employee employee,
			boolean readOnlyFlag) {
		
		/*
		 *  shows details of a employee in the employee details tab
		 */
		
		empIDlbl.setText(employee.getEmployeeNum());
		txtEDfirstName.setText(employee.getFirstName());
		txtEDmiddleName.setText(employee.getMiddleName());
		txtEDlastName.setText(employee.getLastName());
		txtEDdepartment.setText(employee.getDepartment());
		txtED_SIN.setText(employee.getSinNum());
		txtEDaddress.setText(employee.getAddress());
		txtEDphoneNum.setText(employee.getPhoneNum());
		txtEDgender.setText(employee.getGender());
		txtAreaEDcomments.setText(employee.getPerformenceComment());
		ftxtEDsickDays.setText(new Double(employee.getSickDays()).toString());
		ftxtEDsalary.setText(new Double(employee.getSalary()).toString());
		ageLabel.setText("Age: "+employee.calculateAgeByToday(employee.getYearOfBirth(), employee.getMonthOfBirth(), employee.getDateOfBirth()));
		ftxtEDyear.setText(new Integer(employee.getYearOfBirth()).toString());
		comboBoxEDmonth.setSelectedIndex(employee.getMonthOfBirth()-1);
		comboBoxEDday.setSelectedIndex(employee.getDateOfBirth()-1);
		if (employee.getDepartment() == null || employee.getDepartment().isEmpty() ){
			deptComboBox.setSelectedIndex(0);
		} else {
			deptComboBox.setSelectedItem(employee.getDepartment());
		}
		txtEDdepartment.setVisible(false);
		
		
		txtEDfirstName.setEditable( ! readOnlyFlag );
		txtEDmiddleName.setEditable( ! readOnlyFlag );
		txtEDlastName.setEditable( ! readOnlyFlag );
		txtEDdepartment.setEditable( ! readOnlyFlag );
		txtED_SIN.setEditable( ! readOnlyFlag );
		txtEDaddress.setEditable( ! readOnlyFlag );
		txtEDphoneNum.setEditable( ! readOnlyFlag );
		txtEDgender.setEditable( ! readOnlyFlag );
		txtAreaEDcomments.setEditable( ! readOnlyFlag );
		ftxtEDsickDays.setEditable( ! readOnlyFlag );
		ftxtEDsalary.setEditable( ! readOnlyFlag );
		ftxtEDyear.setEditable(! readOnlyFlag);
		comboBoxEDmonth.setEditable(! readOnlyFlag);
		comboBoxEDday.setEditable(! readOnlyFlag);
		deptComboBox.setEditable(! readOnlyFlag);
		
		tabbedPane.setSelectedIndex(3);
	}

	/**
	 * Create the frame.
	 */
	public MainGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				//String [] exitOpts = {"Save","Exit","Cancel"};
				int closeOption = JOptionPane.showConfirmDialog(null, "Would you like to save your work before exiting?", "Exit",  JOptionPane.YES_NO_CANCEL_OPTION);
				if (closeOption == 0) {
					try {
						departmentHandler.saveDepartmentToFile();
					} catch (Exception e) {
						e.printStackTrace();
						errorMessageBox( "Failed to save data into department file.", e.getMessage() );
					}
					try {
						empHandler.saveEmployeeToFile();
					} catch (Exception e) {
						e.printStackTrace();
						errorMessageBox( "Failed to save data into employee file.", e.getMessage() );
					}
					System.exit(0);
				} else if (closeOption == 1) {
					System.exit(0);
				} else {
					//Do Nothing
				}
				
			}
		});
		try {
	    // Set cross-platform Java L&F (also called "Metal")
		 UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName());
	    } 
	    catch (UnsupportedLookAndFeelException e) {
	       // handle exception
			errorMessageBox( "UnsupportedLookAndFeelException", e.getMessage() );
	    }
	    catch (ClassNotFoundException e) {
	       // handle exception
			errorMessageBox( "ClassNotFoundException for LookAndFeel", e.getMessage() );
	    }
	    catch (InstantiationException e) {
	       // handle exception
			errorMessageBox( "InstantiationException on LookAndFeel", e.getMessage() );
	    }
	    catch (IllegalAccessException e) {
	       // handle exception
			errorMessageBox( "IllegalAccessException on LookAndFeel", e.getMessage() );
	    }
		 //Set Frame Size
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 753, 425);
		
		//Initialize Menu Bar
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		
		//Add items
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Save");
		mntmNewMenuItem_2.setText( "Save" );
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmNewMenuItem_2.setHorizontalAlignment(SwingConstants.CENTER);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					empHandler.saveEmployeeToFile();
					departmentHandler.saveDepartmentToFile();
				} catch (Exception e1) {
					e1.printStackTrace();
					errorMessageBox( "Failed to save to file", e1.getMessage() );
				}
			}
		});//save Option
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmReadAndEdit = new JMenuItem("Read and Edit");
		mntmReadAndEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String [] options = {"Read-only", "Editable"};
				int readOption = JOptionPane.showOptionDialog(null , "Read/Editable", "Settings", 2, 2, new ImageIcon(MainGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")), options, 0);
				System.out.println("VERY IMPORTANT "+readOption);
				if (readOption == 0) {
					readOnlyFlag = true;
				} else if (readOption == 1) {
					readOnlyFlag = false;
				}
			}
		});//Able to edit inofrmation
		mnFile.add(mntmReadAndEdit);
		
		JMenuItem mntmChangePassword = new JMenuItem("Change Password");
		mntmChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password = JOptionPane.showInputDialog(frame , "Enter the new password", "Password Change", DISPOSE_ON_CLOSE);
				System.out.println(password);
				passwordIO.writePasswordToSecureFile(password);
			}
		});
		mnFile.add(mntmChangePassword);
		menuBar.add(mntmNewMenuItem_2);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mntmAbout.setHorizontalAlignment(SwingConstants.RIGHT);
		mntmAbout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Component frame = null;
				JOptionPane.showMessageDialog(frame, "Human Resource System Version 2.0\nDeveloped by Software Solutions\nCopyright 2014\n",null , JOptionPane.PLAIN_MESSAGE, null);
			}
		});//About the prgram
		
		menuBar.add(mntmAbout);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		//add Panel
		
		//set new Panel
		JPanel sidePanel = new JPanel();
		sidePanel.setForeground(Color.BLACK);
		contentPane.add(sidePanel, BorderLayout.WEST);
		GridBagLayout gbl_sidePanel = new GridBagLayout();
		gbl_sidePanel.columnWidths = new int[] {52, 0};
		gbl_sidePanel.rowHeights = new int[] {44, 0, 30, 38, 28, 0};
		gbl_sidePanel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gbl_sidePanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0};
		sidePanel.setLayout(gbl_sidePanel);
		
		//Label
		JLabel lblNewLabel = new JLabel("Employee ID");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		sidePanel.add(lblNewLabel, gbc_lblNewLabel);
		
		//Text field
		txtEmpSearch = new JTextField();
		GridBagConstraints gbc_txtEmpSearch = new GridBagConstraints();
		gbc_txtEmpSearch.fill = GridBagConstraints.VERTICAL;
		gbc_txtEmpSearch.insets = new Insets(0, 0, 5, 0);
		gbc_txtEmpSearch.gridx = 0;
		gbc_txtEmpSearch.gridy = 1;
		sidePanel.add(txtEmpSearch, gbc_txtEmpSearch);
		txtEmpSearch.setColumns(10);
		
		//Serach Buton
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String empSearch = txtEmpSearch.getText();
				Employee foundEmp = empHandler.getEmployeeByKey(empSearch);
				if (foundEmp != null){
					populateEmployeeDetails(foundEmp, true);
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 2;
		sidePanel.add(btnNewButton, gbc_btnNewButton);
		
		//Department
		JLabel lblNewLabel_1 = new JLabel("Department");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 4;
		sidePanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		//Scroll Panel
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 5;
		sidePanel.add(scrollPane, gbc_scrollPane);
		
		listDepartment = new JList();

		listDepartment.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listDepartment);
		listDepartment.setModel(new AbstractListModel() {
			String[] values = new String[] {};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
		JScrollBar scrollBar = new JScrollBar();
		scrollPane.setVerticalScrollBar(scrollBar);
		
		JPanel topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		
		JPanel centrePanel = new JPanel();
		contentPane.add(centrePanel, BorderLayout.SOUTH);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		deptListTab = new JPanel();
		tabbedPane.addTab("Department List", null, deptListTab, null);
		deptListTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		deptListTab.add(scrollPane_1, BorderLayout.CENTER);
		
		listDLdeptList = new JList();
		scrollPane_1.setViewportView(listDLdeptList);
		
		JScrollBar scrollBar_1 = new JScrollBar();
		scrollPane_1.setVerticalScrollBar(scrollBar_1);
		
		empListTab = new JPanel();
		tabbedPane.addTab("Employee List", null, empListTab, null);
		empListTab.setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		empListTab.add(scrollPane_2, BorderLayout.CENTER);
		
		java.awt.List list_1 = new java.awt.List();
		scrollPane_2.setViewportView(list_1);
		
		JScrollBar scrollBar2 = new JScrollBar();
		scrollPane_2.setVerticalScrollBar(scrollBar2);
		
		JPanel deptDetail = new JPanel();
		tabbedPane.addTab("Department Detail", null, deptDetail, null);
		deptDetail.setLayout(null);
		
		txtDDDeptDesc = new JTextArea();
		txtDDDeptDesc.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtDDDeptDesc.setBounds(23, 98, 229, 87);
		deptDetail.add(txtDDDeptDesc);
		
		JLabel lblDepartmentName = new JLabel("Department Name");
		lblDepartmentName.setBounds(23, 22, 117, 14);
		deptDetail.add(lblDepartmentName);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setBounds(23, 73, 117, 14);
		deptDetail.add(lblDescription);
		
		txtDDDeptName = new JTextField();
		txtDDDeptName.setBounds(23, 36, 105, 20);
		deptDetail.add(txtDDDeptName);
		txtDDDeptName.setColumns(10);
		
		JLabel lblHeadOfDepartment = new JLabel("Head of Department");
		lblHeadOfDepartment.setBounds(23, 220, 164, 14);
		deptDetail.add(lblHeadOfDepartment);
		
		txtDDHeadOfDept = new JTextField();
		txtDDHeadOfDept.setColumns(10);
		txtDDHeadOfDept.setBounds(23, 232, 105, 20);
		deptDetail.add(txtDDHeadOfDept);
		
		JButton createDept = new JButton("Create/Edit");
		createDept.setIcon(new ImageIcon(MainGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		createDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department dept = getDepartmentInfoFromGUI();
				departmentHandler.saveDepartment( dept );
				populateDepartmentTab();
				populateDepartmentDetails( dept , false );
				refreshDepList ();
			}
		});
		createDept.setBounds(387, 231, 128, 23);
		deptDetail.add(createDept);
		
		JButton removeDept = new JButton("Remove");
		removeDept.setIcon(new ImageIcon(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		removeDept.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Department deptToRemove = getDepartmentInfoFromGUI();
				if (departmentHandler.getDepartmentByKey( deptToRemove.getName() )!= null) {
					departmentHandler.removeDepartment( deptToRemove );
					populateDepartmentTab();
					cleanDepartmentDetails();
					refreshDepList ();
				} else {
					Component frame = null;
					JOptionPane.showMessageDialog(frame, "Cannot find specified department name to be removed.");
				}
			}
		});
		removeDept.setBounds(387, 252, 128, 23);
		deptDetail.add(removeDept);
		
		JButton clearDeptDetails = new JButton("Clear Details");
		clearDeptDetails.setIcon(new ImageIcon(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		clearDeptDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanDepartmentDetails();
			}
		});
		clearDeptDetails.setBounds(387, 271, 128, 23);
		deptDetail.add(clearDeptDetails);
		
		JPanel empDetail = new JPanel();
		tabbedPane.addTab("Employee Detail", null, empDetail, null);
		empDetail.setLayout(null);
		
		JButton btnSaveEdits = new JButton("Create/Edit");
		btnSaveEdits.setIcon(new ImageIcon(MainGUI.class.getResource("/com/sun/java/swing/plaf/windows/icons/DetailsView.gif")));
		btnSaveEdits.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee emp = getEmployeeInfoFromGUI();
				
				String empNum = empHandler.saveEmployee( emp );
				
				if ( empNum != null && ! empNum.isEmpty() ) {

					emp.setEmployeeNum( empNum );
					populateEmployeeTab();
					populateEmployeeDetails(emp, false);
				} else {
					System.out.println( "Error;");
				}
			}
		});
		
		deptComboBox = new JComboBox();
		deptComboBox.setModel(new DefaultComboBoxModel( this.departmentHandler.getDepartmentNameList().toArray()));
		deptComboBox.setBounds(10, 88, 105, 20);
		deptComboBox.setSelectedIndex(0);
		empDetail.add(deptComboBox);
		btnSaveEdits.setBounds(407, 227, 128, 23);
		empDetail.add(btnSaveEdits);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 29, 117, 14);
		empDetail.add(lblFirstName);
		
		txtEDfirstName = new JTextField();
		txtEDfirstName.setColumns(10);
		txtEDfirstName.setBounds(10, 43, 105, 20);
		empDetail.add(txtEDfirstName);
		
		txtAreaEDcomments = new JTextArea();
		txtAreaEDcomments.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAreaEDcomments.setBounds(10, 140, 229, 87);
		empDetail.add(txtAreaEDcomments);
		
		JLabel lblComments = new JLabel("Comments");
		lblComments.setBounds(10, 126, 117, 14);
		empDetail.add(lblComments);
		
		txtEDmiddleName = new JTextField();
		txtEDmiddleName.setColumns(10);
		txtEDmiddleName.setBounds(149, 43, 86, 20);
		empDetail.add(txtEDmiddleName);
		
		JLabel lblMiddleName = new JLabel("Middle Name");
		lblMiddleName.setBounds(149, 29, 164, 14);
		empDetail.add(lblMiddleName);
		
		txtEDlastName = new JTextField();
		txtEDlastName.setColumns(10);
		txtEDlastName.setBounds(273, 43, 105, 20);
		empDetail.add(txtEDlastName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(273, 29, 105, 14);
		empDetail.add(lblLastName);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(10, 74, 164, 14);
		empDetail.add(lblDepartment);
		
		txtEDdepartment = new JTextField();
		txtEDdepartment.setColumns(10);
		txtEDdepartment.setBounds(10, 88, 105, 20);
		empDetail.add(txtEDdepartment);
		
		JLabel lblDaysoff = new JLabel("Days-Off");
		lblDaysoff.setBounds(149, 74, 164, 14);
		empDetail.add(lblDaysoff);
		
		ftxtEDsickDays = new JFormattedTextField();
		ftxtEDsickDays.setBounds(149, 88, 74, 20);
		empDetail.add(ftxtEDsickDays);
		
		JLabel lblAnnualSalary = new JLabel("Annual Salary");
		lblAnnualSalary.setBounds(273, 215, 164, 14);
		empDetail.add(lblAnnualSalary);
		
		ftxtEDsalary = new JFormattedTextField();
		ftxtEDsalary.setBounds(273, 227, 105, 20);
		empDetail.add(ftxtEDsalary);
		
		JLabel lblEmployeeId = new JLabel("Employee ID: ");
		lblEmployeeId.setBounds(10, 4, 86, 14);
		empDetail.add(lblEmployeeId);
		
		JLabel lblSin = new JLabel("SIN");
		lblSin.setBounds(273, 74, 164, 14);
		empDetail.add(lblSin);
		
		txtED_SIN = new JTextField();
		txtED_SIN.setColumns(10);
		txtED_SIN.setBounds(273, 88, 140, 20);
		empDetail.add(txtED_SIN);
		
		txtEDaddress = new JTextField();
		txtEDaddress.setColumns(10);
		txtEDaddress.setBounds(273, 141, 217, 20);
		empDetail.add(txtEDaddress);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(273, 126, 164, 14);
		empDetail.add(lblAddress);
		
		txtEDphoneNum = new JTextField();
		txtEDphoneNum.setColumns(10);
		txtEDphoneNum.setBounds(273, 184, 140, 20);
		empDetail.add(txtEDphoneNum);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(273, 171, 164, 14);
		empDetail.add(lblPhoneNumber);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(407, 29, 52, 14);
		empDetail.add(lblGender);
		
		txtEDgender = new JTextField();
		txtEDgender.setColumns(10);
		txtEDgender.setBounds(407, 43, 52, 20);
		empDetail.add(txtEDgender);
		
		ftxtEDyear = new JFormattedTextField();
		ftxtEDyear.setBounds(10, 259, 52, 20);
		empDetail.add(ftxtEDyear);
		
		JLabel lblYear = new JLabel("Year");
		lblYear.setBounds(10, 246, 74, 14);
		empDetail.add(lblYear);
		
		JLabel lblMonth = new JLabel("Month");
		lblMonth.setBounds(72, 246, 74, 14);
		empDetail.add(lblMonth);
		
		JLabel lblDay = new JLabel("Day");
		lblDay.setBounds(125, 246, 74, 14);
		empDetail.add(lblDay);
		
		ageLabel = new JLabel("Age: ");
		ageLabel.setBounds(196, 265, 117, 14);
		empDetail.add(ageLabel);
		
		comboBoxEDmonth = new JComboBox();
		comboBoxEDmonth.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}));
		comboBoxEDmonth.setBounds(72, 259, 43, 20);
		empDetail.add(comboBoxEDmonth);
		
		comboBoxEDday = new JComboBox();
		comboBoxEDday.setModel(new DefaultComboBoxModel(new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
		comboBoxEDday.setBounds(125, 259, 39, 20);
		empDetail.add(comboBoxEDday);
		
		empIDlbl = new JLabel("");
		empIDlbl.setBounds(90, 4, 46, 14);
		empDetail.add(empIDlbl);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setIcon(new ImageIcon(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employee empToRemove = empHandler.getEmployeeByKey(empIDlbl.getText());
				empHandler.removeEmployee(empToRemove);
				populateEmployeeTab();
				cleanEmployeeDetails();
			}
		});
		btnRemove.setBounds(407, 251, 128, 23);
		empDetail.add(btnRemove);
		
		JButton btnClearDetails = new JButton("Clear Details");
		btnClearDetails.setIcon(new ImageIcon(MainGUI.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		btnClearDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cleanEmployeeDetails();
			}
		});
		btnClearDetails.setBounds(407, 271, 128, 23);
		empDetail.add(btnClearDetails);
	}
	
	protected void cleanDepartmentDetails() {
		populateDepartmentDetails(new Department(), false);
		
	}
	
	protected void cleanEmployeeDetails() {
		populateEmployeeDetails(new Employee(), false);
		
	}
	
	private static void errorMessageBox( final String errMsg, final String msg ) {
		JOptionPane.showMessageDialog(null, errMsg, null, JOptionPane.ERROR_MESSAGE, null);
		System.out.println( "ERROR: " + errMsg + " Msg: " + msg );
	}

}
