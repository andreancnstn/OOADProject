package Model;

import java.sql.Date;
import java.util.Vector;

import Main.DatabaseConnection;

public class Employee {
	
	private int id;
	private int roleId;
	private String name;
	private Date DOB;
	private String email;
	private String password;
	private String status;
	
//	public Employee(int roleId, String name, Date dOB, String email, String password, String status) {
//		super();
//		this.roleId = roleId;
//		this.name = name;
//		DOB = dOB;
//		this.email = email;
//		this.password = password;
//		this.status = status;
//	}

	DatabaseConnection c = new DatabaseConnection();
	
	public boolean createAccount(int roleId, String name, Date DOB, String email, String password, String Status) {
		
		c.createAccount(roleId, name, DOB, email, password, Status);
		
		return true;
	}
	
	public boolean changeStatus(int id) {
		
		c.update("UPDATE employee SET status='Inactive' WHERE id=" + id );
		
		return true;
	}
	
	public Vector<Employee> viewAll() {
		
		Vector<Employee> v = null;
		
		return v;
	}

}
