package Controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.swing.text.View;

import Main.DatabaseConnection;
import Model.Employee;
import View.EmployeeView;
import View.User.EmployeeLoginView;

public class EmployeeHandler {
	
	private EmployeeView view;
	private Employee model;
	private static EmployeeHandler eh;
	DatabaseConnection c = new DatabaseConnection();
	Vector<String> v = new Vector<>();
	
	public EmployeeHandler() {
		model = new Employee();
	}
	
	public static synchronized EmployeeHandler getInstance() {
		if (eh == null) eh = new EmployeeHandler();
		
		return eh;
	}

	public EmployeeView viewManageEmployeeForm() {
		return new EmployeeView();
	}
	
	public boolean createEmployee(int roleId, String name, String DOB, String email, String password, String status) {
		
		if (validateFields(name, DOB, email) == true) {
			model.createAccount(roleId, name, Date.valueOf(DOB), email, password, status);
			return true;
		}
		
		return false;
	}

	private boolean validateFields(String name, String dOB, String email) {
		
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if (!email.matches(regex)) {
			view.displayErrorMsg("Wrong email format");
			return false;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		
		try {
			java.util.Date sd = format.parse(dOB);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			view.displayErrorMsg("Wrong date format");
			return false;
		}
		
		c.resultSet = c.query("SELECT * FROM employee");
		
		try {
			while(c.resultSet.next() == true) {
				String data = c.resultSet.getString("email");
				v.add(data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			view.displayErrorMsg("Email already Exist");
			return false;
		}
		
		for (int i = 0; i < v.size(); i++) {
			if (email == v.get(i)) {
				view.displayErrorMsg("Email already Exist");
				return false;
			}
		}
		
		return true;
	}
	
	public boolean changeStatus(int id) {
		model.changeStatus(id);
		
		return true;
	}
	
	public Vector<Employee> viewAll(int roleId) {
		
		Vector<Employee> em = new Vector<Employee>();
		
		return em;
	}
	
	public static EmployeeLoginView viewEmployeeLoginForm() {
		return new EmployeeLoginView();
	}
	
	public boolean validateEmp(String email, String password) {
		return model.getEmp(email, password);
	}
	
	public Integer getRoleIdfromEmail(String Email) {
		return model.getRoleIdfromEmail(Email);
	}
	
	public Integer getLogedinEmpId(String email) {
		return model.getLogedinEmpId(email);
	}

	
}
