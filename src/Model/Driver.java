package Model;

import Main.DatabaseConnection;

public class Driver extends Employee{
	
	private String licensePlate;
	private Integer employeeId;

	public Driver(String licensePlate, Integer employeeId) {
		super();
		this.licensePlate = licensePlate;
		this.employeeId = employeeId;
	}
	
	DatabaseConnection c = new DatabaseConnection();
	
	public void createDriver() {
		c.createDriver(employeeId, licensePlate);
	}

}
