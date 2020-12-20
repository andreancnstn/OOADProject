package Main;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import Model.User;

public class DatabaseConnection {
	
	public Statement statement;
	public ResultSet resultSet;
	public ResultSetMetaData metaData;
	public PreparedStatement preparedStatement;
	public Connection connection;

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectooad", "root", "");
			statement = connection.createStatement();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("koneksi gagal");
		}
	}
	
	public ResultSet query(String q) {
		try {
			resultSet = statement.executeQuery(q);
			metaData = resultSet.getMetaData();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return resultSet;
	}
	
	public void createAccount(int roleId, String name, Date DOB, String email, String password, String Status) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO employee (roleId, name, DOB, email, password, status) VALUES (?,?,?,?,?,?)");
			preparedStatement.setInt(1, roleId);
			preparedStatement.setString(2, name);
			preparedStatement.setDate(3, DOB);
			preparedStatement.setString(4, email);
			preparedStatement.setString(5, password);
			preparedStatement.setString(6, Status);
			
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void createUserAccount(/*int userId, */String name, String address, String email, String phoneNumber, String password) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO tbluser (name, address, email, phoneNumber, password) VALUES (?,?,?,?,?)");
//			preparedStatement.setInt(1, userId); //autoincrement
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, address);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, phoneNumber);
			preparedStatement.setString(5, password);
			
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(String query) {
		try {
			statement.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 
	public void addFood(String name, int price, String description) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO food (name, price, description, status) VALUES (?,?,?,?)");
			preparedStatement.setString(1, name);
			preparedStatement.setInt(2, price);
			preparedStatement.setString(3, description);
			preparedStatement.setString(4, "available");
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOrder(User user, java.sql.Date date) {
		String addresss = user.getAddress();
		int userIdd = user.getUserId();
		String statuss = "Not accepted"; //belom diambil driver
						//Di tblorder, ganti lengthnya STATUS jadi VARCHAR(13)
		
		try {
			//TODO
			preparedStatement = connection.prepareStatement("INSERT INTO tblorder (date, address, userId, driverId, status) VALUES (?,?,?,?,?)");
//			preparedStatement.s	etInt(1, orderIdd); //autoincrement
			preparedStatement.setDate(1, date);
			preparedStatement.setString(2, addresss);
			preparedStatement.setInt(3, userIdd);
			preparedStatement.setInt(4, 0); //not taken by driver yet
			preparedStatement.setString(5, statuss); 
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void addDetail(int orderId, int foodId, int qty) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO orderdetail (orderId,foodId,qty) VALUES (?,?,?)");
			preparedStatement.setInt(1, orderId);
			preparedStatement.setInt(2, foodId);
			preparedStatement.setInt(3, qty);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void createDriver(int employeeId, String lPlate) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO driver (employeeId,licensePlate) VALUES (?,?)");
			preparedStatement.setInt(1, employeeId);
			preparedStatement.setString(2, lPlate);
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
