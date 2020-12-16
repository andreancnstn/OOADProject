package Main;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
	
	public void addOrder(int orderId, Date date, String address, int userId, int driverId, String status) {
		try {
			preparedStatement = connection.prepareStatement("INSERT INTO order (orderId, date, address, userId, driverId, status) VALUES (?,?,?,?,?,?)");
			preparedStatement.setInt(1, orderId);
			preparedStatement.setDate(2, date);
			preparedStatement.setString(3, address);
			preparedStatement.setInt(4, userId);
			preparedStatement.setInt(5, driverId);
			preparedStatement.setString(6, status);
			
			preparedStatement.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
