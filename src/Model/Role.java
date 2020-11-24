package Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import Main.DatabaseConnection;

public class Role {
	
	private int roleId;
	private String roleName;
	
	DatabaseConnection connection = new DatabaseConnection();
	
	public int getRoleId (String roleName) {
		ResultSet hasilResultSet = connection.query("SELECT roleId FROM role WHERE roleName='" + roleName + "' LIMIT 1");
		
		int roleid = 0;
		try {
			if(hasilResultSet.next()) {
				roleid = hasilResultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return roleid;
	}

}
