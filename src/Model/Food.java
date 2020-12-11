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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFoodId() {
		return foodId;
	}

	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
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
		Food f = null;
		
		c.resultSet = c.query("SELECT * FROM food");
		
		try {
			while(c.resultSet.next() == true) {
				for (int i = 0; i <= c.metaData.getColumnCount(); i++) {
					int foodId = c.resultSet.getInt(1);
					String name = c.resultSet.getString(2).toString();
					int price = c.resultSet.getInt(3);
					String desc = c.resultSet.getString(4);
					String status = c.resultSet.getString(5);
					f = new Food(foodId, name, price, desc, status);
				}
				v.add(f);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		return v;
	}
	
	public int getFoodID (String foodName) {
		c.resultSet = c.query("SELECT TOP 1 foodId WHERE name='" + foodName + "'");

		int id = 0;
		try {
			if (c.resultSet.next()) {
				id = c.resultSet.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return id;
	}
}
