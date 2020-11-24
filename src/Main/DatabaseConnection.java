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
			System.out.println("koneksi oke");
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
}
