package Controller;

import Model.User;
import View.User.LoginView;
import View.User.RegistrationView;
import View.User.UserInformationView;

public class UserHandler {
	
	private User user;
	private static UserHandler controller;
	
	public UserHandler() {
		user = new User();
	}
	
	// Singleton
	public static synchronized UserHandler getInstance() {
		if (controller == null) {
			controller = new UserHandler();
		}
		
		return controller;
	}
	
	//add customer account
	public void createAccount(String name, String address, String email, String phoneNumber, String password) {
		User um = new User();
		
		um.setName(name);
		um.setAddress(address);
		um.setEmail(email);
		um.setPhoneNumber(phoneNumber);
		um.setPassword(password);
		um.createAccount();
	}
	
	public User getOne(String email) {
		return user.getOne(email);
	}
	
	//validation for uniqueness email and phoneNumber when user register the account
	public boolean validateUnique(String email, String phoneNumber) {
		return user.checkUnique(email, phoneNumber);
	}
	
	//validation for registration
	public String validateFields(String name, String address, String email, String phoneNumber, String password) {
		if (name.isEmpty() || address.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty()) {
			return "Please fill the data completely.";
		}
		
		if (!email.contains("@")) {
			return "Email is invalid";
		}
		
		if (!phoneNumber.matches("[0-9]+")) {
			return "Phone number is invalid.";
		}
		
		if (validateUnique(email, phoneNumber) == true) {
			return "Email or phone number has been taken.";
		}
		
		return "Data is valid";
	}
	
	//validation for login
	public boolean validateAccount(String email, String password) {
		boolean account = user.getAccount(email, password);
		
		if (account == false) {
			return false;
		}
		
		return true;
	}
	
	public User getUserbyId(Integer userId) {
		return user.getUserbyId(userId);
	}
	
	public UserInformationView viewUserInformation(int userId) {
		return new UserInformationView(userId);
	}
	
	public static RegistrationView viewRegistrationForm() {
		return new RegistrationView();
	}
	
	public static LoginView viewLoginForm() {
		return new LoginView();
	}
	
}
