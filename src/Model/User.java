package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Main.DatabaseConnection;

public class User {

	
	private DatabaseConnection con = new DatabaseConnection();
	
	private Integer id;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private String password;
	
	
	//setter getter
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	//get user data
	//it is used for get the data of the user who has login
	public User getOne(String _email) {
		try {
			ResultSet rs = con.query("SELECT * FROM users");
			
			while (rs.next()) {
				
				Integer Id = rs.getInt("userId");
				String Name = rs.getString("name");
				String Address = rs.getString("address");
				String Email = rs.getString("email");
				String PhoneNumber = rs.getString("phoneNumber");
				String Password = rs.getString("password");
				
				if(_email.equals(Email)) {
					User um = new User();
					
					um.setId(Id);
					um.setName(Name);
					um.setAddress(Address);
					um.setEmail(Email);
					um.setPhoneNumber(PhoneNumber);
					um.setPassword(Password);
					
					return um;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	//add account
	public void createAccount() {
		String query = String.format("INSERT INTO users VALUES(null, ?, ?, ?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, name);
			ps.setString(2, address);
			ps.setString(3, email);
			ps.setString(4, phoneNumber);
			ps.setString(5, password);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// validate account for login
	public boolean getAccount(String Email, String Password) {
		try {
			ResultSet rs = con.query("SELECT * FROM users");
			
			while (rs.next()) {
				
				String email = rs.getString("email");
				String password = rs.getString("password");
				
				if(Email.equals(email) && Password.equals(password)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	
	// check unique email and phoneNumber for registration
	public boolean checkUnique(String Email, String PhoneNumber) {
		try {
			ResultSet rs = con.query("SELECT * FROM users");
			
			while (rs.next()) { 
				String email = rs.getString("email");
				String phoneNumber = rs.getString("phoneNumber");
				
				if(Email.equals(email) || PhoneNumber.equals(phoneNumber)) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
	public User getUserbyId(Integer userId) {
		try {
			ResultSet rs = con.query("SELECT * FROM users");
			
			while (rs.next()) {
				
				Integer Id = rs.getInt("userId");
				String Name = rs.getString("name");
				String Address = rs.getString("address");
				String Email = rs.getString("email");
				String PhoneNumber = rs.getString("phoneNumber");
				String Password = rs.getString("password");
				
				if(userId.equals(Id)) {
					User um = new User();
					
					um.setId(Id);
					um.setName(Name);
					um.setAddress(Address);
					um.setEmail(Email);
					um.setPhoneNumber(PhoneNumber);
					um.setPassword(Password);
					
					return um;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
