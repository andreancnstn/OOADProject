package Model;

import java.sql.Date;

import Main.DatabaseConnection;

public class User {
	private int userId;
	private String name;
	private String address;
	private String email;
	private String phoneNumber;
	private String password;
	
	DatabaseConnection c = new DatabaseConnection();
	
	public User(int userId, String name, String address, String email, String phoneNumber, String password) {
		super();
		this.userId = userId;
		this.name = name;
		this.address = address;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.password = password;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	//di classdiagramnya dia minta param userId. hmm 
	public boolean createUserAccount(/*int userId, */String name, String address, String email, String phoneNumber, String password) {
		c.createUserAccount(/*userId, */name, address, email, phoneNumber, password);
		return true;
	}
	
	public User getOne(int userId) {
		//TODO get user from user table where userId = userId
		User u = null;
		return u;
	}
	
	//SETTER GETTER
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

}
