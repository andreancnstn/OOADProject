package Controller;

import javax.swing.text.View;

import Model.User;

public class UserHandler {
	
	public boolean createAccount(String name, String address, String email, String phoneNumber, String password) {
		User u = new User(password, password, password, password, password);
		u.createUserAccount(/*userId, */name, address, email, phoneNumber, password);
		return true;
	}
	//METHODS
	
	//getOne
	//validateUnique
	//validateFields
	//viewUserInformation
	//viewRegistrationForm
}

