package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.EmployeeHandler;
import Main.DatabaseConnection;
import Model.Driver;
import Model.Employee;
import Model.Role;

public class EmployeeView extends JFrame implements ActionListener{
	JPanel contentPanel, panel, buttonPanel, dialogBoxPanel; 
	JLabel rolelabel, namelabel, doblabel, emaillabel, passwordlabel, statuslabel, lPlatelabel;
	JTextField namefield, dobfield, emailfield, passwordfield, lPlateField;
	JButton hirebtn, firebtn;
	JComboBox<String> statusfield, rolefield;
	JTable table;
	JScrollPane scrollPane;
	DefaultTableModel dtm;
	Vector<Object> tablecontent;
	Vector<String> statusList, roleList;
	
	DatabaseConnection c = new DatabaseConnection();
	
	public EmployeeView() {
		contentPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout(0,0));
		setContentPane(contentPanel);
		
		panel = new JPanel();
		buttonPanel = new JPanel(new GridLayout(1,2));
		
		contentPanel.add(panel, BorderLayout.CENTER);
		contentPanel.add(buttonPanel, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		rolelabel = new JLabel("Role");
		rolelabel.setBounds(10, 240, 100, 25);
		namelabel = new JLabel("Name");
		namelabel.setBounds(10, 280, 100, 25);
		doblabel = new JLabel("DOB");
		doblabel.setBounds(10, 320, 100, 25);
		emaillabel = new JLabel("Email");
		emaillabel.setBounds(10, 360, 100, 25);
		passwordlabel = new JLabel("Password");
		passwordlabel.setBounds(10, 400, 100, 25);
//		statuslabel = new JLabel("Status");
//		statuslabel.setBounds(10, 440, 100, 25);
		lPlatelabel = new JLabel("Licene Plate");
		lPlatelabel.setBounds(10, 440, 100, 25);
		lPlatelabel.setVisible(false);
		
		hirebtn = new JButton("HIRE");
		hirebtn.addActionListener(this);
		firebtn = new JButton("FIRE");
		firebtn.addActionListener(this);
		
		roleList = new Vector<String>();
		roleList.add("Please select role");
		roleList.add("Driver");
		roleList.add("Chef");
		
		rolefield = new JComboBox<String>(roleList);
		rolefield.setBounds(110, 240, 300, 25);
		rolefield.addActionListener(this);
		namefield = new JTextField();
		namefield.setBounds(110, 280, 300, 25);
		dobfield = new JTextField("YYYY-MM-DD");
		dobfield.setBounds(110, 320, 300, 25);
		emailfield = new JTextField();
		emailfield.setBounds(110, 360, 300, 25);
		passwordfield = new JPasswordField();
		passwordfield.setBounds(110, 400, 300, 25);
		lPlateField = new JTextField();
		lPlateField.setBounds(110,  440, 300, 25);
		lPlateField.setVisible(false);
		
//		statusList = new Vector<>();
//		statusList.add("Please select a status");
//		statusList.add("Active");
//		statusList.add("Fired");
//		statusfield = new JComboBox<String>(statusList);
//		statusfield.setBounds(110, 440, 300, 25);
		
		table = new JTable();
		loadManageEmployeeData();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 200);
		
		panel.add(rolelabel);
		panel.add(namelabel);
		panel.add(doblabel);
		panel.add(emaillabel);
		panel.add(passwordlabel);
		panel.add(lPlatelabel);
//		panel.add(statuslabel);
		
		panel.add(rolefield);
		panel.add(namefield);
		panel.add(dobfield);
		panel.add(emailfield);
		panel.add(passwordfield);
		panel.add(lPlateField);
//		panel.add(statusfield);
		panel.add(scrollPane);
		
		buttonPanel.add(hirebtn);
		buttonPanel.add(firebtn);
		
//		if (rolefield.getSelectedItem().toString().equals("Driver")) {
//			lPlatelabel.setVisible(true);
//			lPlateField.setVisible(true);
//		}
		
		init();
	}
	
	private void init() {
		setTitle("Manage Employee Form");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	private void loadManageEmployeeData() {
		String header[] = {"Role", "Name", "DOB", "Email", "Status"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		c.resultSet = c.query("SELECT role.roleName, employee.name, employee.DOB,"
				+ "employee.email, employee.status FROM employee "
				+ "INNER JOIN role ON employee.roleId=role.roleId");
		
		try {
			while (c.resultSet.next() == true) {
				tablecontent = new Vector<Object>();
				for (int i = 1; i <= c.metaData.getColumnCount(); i++) {
					tablecontent.add(c.resultSet.getObject(i));
				}
				dtm.addRow(tablecontent);
			}
			table.setModel(dtm);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == hirebtn) {
			hire();
			loadManageEmployeeData();
		}
		else if(e.getSource() == firebtn) {
			fire();
			loadManageEmployeeData();
		}
		else if (e.getSource() == rolefield) {
			if (rolefield.getSelectedItem().toString().equals("Driver")) {
				lPlatelabel.setVisible(true);
				lPlateField.setVisible(true);
			}
		}
	}
	
	private void inputdriver() {
		
	}

	public void displayErrorMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	void hire() {
		EmployeeHandler eh = new EmployeeHandler();
		
		Role role = new Role();
		int roleId = role.getRoleId(rolefield.getSelectedItem().toString());
		
		if(eh.createEmployee(roleId, namefield.getText(), dobfield.getText(), emailfield.getText(), passwordfield.getText(), "Active") == true) {
			// roleId = 2 artinya Driver, dilihat dari table role
			if (roleId == 2) {
				Driver d = new Driver(lPlateField.getText().toString(), role.getEmpId(emailfield.getText()));
				d.createDriver(role.getEmpId(emailfield.getText()),lPlateField.getText().toString());
			}
			displayErrorMsg("Employee succesfully created");
		}
	}
	
	void fire() {
		EmployeeHandler eh = new EmployeeHandler();
		
		dialogBoxPanel = new JPanel();
		dialogBoxPanel.setSize(new Dimension(250,100));
		dialogBoxPanel.setLayout(null);
		JLabel dialogText = new JLabel("Are you sure want to fire this employee ?");
		dialogText.setBounds(75,45,300,30);
		dialogBoxPanel.add(dialogText);
		int employeeId = 0;
		
		UIManager.put("OptionPane.minimumSize", new Dimension(400,200));
		int result = JOptionPane.showConfirmDialog(null, dialogBoxPanel, "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		
		if (result == 0) {
			int row = table.getSelectedRow();
			String value = "" + table.getValueAt(row, 3);
			
			c.resultSet = c.query("SELECT * FROM employee WHERE email LIKE '" + value +"'");
			
			try {
				c.resultSet.next();
				employeeId = c.resultSet.getInt("id");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (eh.changeStatus(employeeId)) {
				displayErrorMsg("Employee Fired!!");
			}
			
		}
		else if (result == 1) {
			
		}
	}
}
