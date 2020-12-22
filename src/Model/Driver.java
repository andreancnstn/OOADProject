package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import Main.DatabaseConnection;

public class Driver extends Employee{
	
	private String licensePlate;
	private Integer employeeId;

	public Driver(String licensePlate, Integer employeeId) {
		super();
		this.licensePlate = licensePlate;
		this.employeeId = employeeId;
	}
	
	public Driver() {
		
	}
	
	DatabaseConnection c = new DatabaseConnection();
	
	//createDriver hrsnya terima 2 param : employeeId plateNumber
	public void createDriver(int employeeId, String licensePlate) {
		c.createDriver(employeeId, licensePlate);
	}
	
	public Integer getDriverId(Integer empId) {
		ResultSet rs = c.query("SELECT id FROM driver WHERE employeeId=" + empId);
	
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
