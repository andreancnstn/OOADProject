package Model;

import java.util.List;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import Main.DatabaseConnection;
import View.FoodView;

public class Food {
	
	private int foodId;
	private String name;
	private int price;
	private String description;
	private String status;
	
	DatabaseConnection c = new DatabaseConnection();
	
	public Food() {
		
	}
	
	public Food(int foodId, String name, int price, String description, String status) {
		super();
		this.foodId = foodId;
		this.name = name;
		this.price = price;
		this.description = description;
		this.status = status;
	}

	public boolean addFood(String name, int price, String description) {
		c.addFood(name, price, description);
		
		return true;
	}
	
	public boolean deleteFood(int foodId) {
		c.update("DELETE FROM food WHERE foodId=" + foodId);
		
		return true;
	}
	
	public boolean changeStatus(int foodId) {
		c.update("UPDATE food SET status='Not available' WHERE foodId=" + foodId);
		
		return true;
	}
	
	public boolean validateName(String name) {
		Vector<String> v = new Vector<String>();
		
		if (name.isEmpty()) {
			return false;
		}
		
		c.resultSet = c.query("SELECT * FROM food");
		
		try {
			while(c.resultSet.next() == true) {
				String data = c.resultSet.getString("name");
				v.add(data);
			}
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
		
		for (int i = 0; i < v.size(); i++) {
			if (name == v.get(i)) {
				return false;
			}
		}
		
		return true;
	}
	
	public Food getFood(int foodId) {
		
		Food f = null;
		
		return f;
	}

	public Vector<Food> viewAll() {
		Vector<Food> v = new Vector<Food>();
		
		c.resultSet = c.query("SELECT * FROM food");
		
		try {
			while(c.resultSet.next() == true) {
				for (int i = 1; i <= c.metaData.getColumnCount(); i++) {
					int foodId = c.resultSet.getInt(i);
					String name = c.resultSet.getString(i);
					int price = c.resultSet.getInt(i);
					String desc = c.resultSet.getString(i);
					String status = c.resultSet.getString(i);
					v.add(new Food(foodId, name, price, desc, status));
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		return v;
	}
}
