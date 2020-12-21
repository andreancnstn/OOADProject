package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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

	DatabaseConnection c = new DatabaseConnection();

	public Employee(int id, int roleId, String name, Date dOB, String email, String password, String status) {
		super();
		this.id = id;
		this.roleId = roleId;
		this.name = name;
		DOB = dOB;
		this.email = email;
		this.password = password;
		this.status = status;
	}
	
	public Employee() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDOB() {
		return DOB;
	}

	public void setDOB(Date dOB) {
		DOB = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean createAccount(int roleId, String name, Date DOB, String email, String password, String Status) {
		
		c.createAccount(roleId, name, DOB, email, password, Status);
		
		return true;
	}
	
	public boolean changeStatus(int id) {
		
		c.update("UPDATE employee SET status='Inactive' WHERE id=" + id );
		
		return true;
	}
	
	public Vector<Employee> viewAll(int roleId) {
		
		Vector<Employee> v = new Vector<>();
		Employee e = null;
		
		c.resultSet = c.query("SELECT * FROM employee");
		
		try {
			while(c.resultSet.next()) {
				for (int i = 0; i < c.metaData.getColumnCount(); i++) {
					Integer id = c.resultSet.getInt("id");
					Integer roleid = c.resultSet.getInt("roleId");
					String name = c.resultSet.getString("name");
					Date dob = c.resultSet.getDate("DOB");
					String email = c.resultSet.getString("email");
					String pass = c.resultSet.getString("password");
					String status = c.resultSet.getString("status");
					e = new Employee(id, roleid, name, dob, email, pass, status);
				}
				v.add(e);
			}
			return v;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}
	
	public boolean getEmp(String email, String password) {
		
		try {
			ResultSet rs = c.query("SELECT * FROM employee");
			
			while (rs.next()) {
				
				String email1 = rs.getString("email");
				String password1 = rs.getString("password");
				
				if(email.equals(email1) && password.equals(password1)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public Integer getRoleIdfromEmail(String Email) {
		int roleid = 0;
		try {
			ResultSet rs = c.query("SELECT * FROM employee WHERE email='" + Email + "'");
			
			while (rs.next()) {
				
				roleid = rs.getInt("roleId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return roleid;
	}
	
	public Integer getLogedinEmpId(String email) {
		ResultSet rs = c.query("SELECT * FROM employee WHERE email='" + email + "'");
		
		try {
			while (rs.next()) {
				Integer id = rs.getInt("id");
				return id;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}

}
