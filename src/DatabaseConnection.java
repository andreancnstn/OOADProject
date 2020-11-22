import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
	
	Statement statement;
	ResultSet resultSet;
	ResultSetMetaData metaData;
	PreparedStatement preparedStatement;
	Connection connection;

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
}
