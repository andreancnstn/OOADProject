package Model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Main.DatabaseConnection;


public class Cart {
	
	private DatabaseConnection con = new DatabaseConnection();
	private Statement state;
	
	private Food food;
	private User user;
	private Integer userId;
	private Integer foodId;
	private Integer qty;
	
	public Cart() {
		user = new User();
	}
	
	
	// setter getter
	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getFoodId() {
		return foodId;
	}

	public void setFoodId(Integer foodId) {
		this.foodId = foodId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getQty() {
		return qty;
	}

	public void setQty(Integer qty) {
		this.qty = qty;
	}

	
	//add item to cart
	public void addToCart() {
		String query = String.format("INSERT INTO cart VALUES(?, ?, ?)");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, foodId);
			ps.setInt(3, qty);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//remove one item from cart
	public void removeFromCart(Integer foodId) {
		String query = String.format("DELETE FROM carts WHERE userId=? && foodId=?");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.setInt(2, foodId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//remove all items
	public void removeAll() {
		String query = String.format("DELETE FROM carts WHERE userId=?");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, userId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update qty in cart
	public void updateQty(Integer qty, Integer userId, Integer foodId) {
		String query = String.format("UPDATE cart SET qty=? WHERE userId=? && foodId=?");
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, qty);
			ps.setInt(2, userId);
			ps.setInt(3, foodId);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//view all items in cart
	public Vector<Cart> viewAll() {
		Vector<Cart> cartItems = new Vector<>();
		
		ResultSet rs = con.query("SELECT * FROM cart");
		
		try {
			while(rs.next()) {
				Integer user_id = rs.getInt("userId");
				Integer food_id = rs.getInt("foodId");
				Integer qty = rs.getInt("qty");
				
				if (userId.equals(user_id)) {
					Cart cm = new Cart();
					Food fm = new Food();

					cm.setFoodId(food_id);
					cm.setFood(fm.getFood(food_id));
					cm.setQty(qty);
					
					cartItems.add(cm);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cartItems;
	}
	
	//check if the items is existed in the cart
	public Integer isFoodExist(Integer FoodId, Integer UserId) {
		
		try {
			ResultSet rs = con.query("SELECT * FROM cart");
			
			while (rs.next()) {
				Integer _userId = rs.getInt("userId");
				Integer _foodId = rs.getInt("foodId");
				
				if (FoodId.equals(_foodId) && UserId.equals(_userId)) {
					return _foodId;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	// get one item for update quantity when user add the existed food in the cart
	public Cart getOne(Integer FoodId, Integer UserId) {
		
		try {
			ResultSet rs = con.query("SELECT * FROM cart");

			while (rs.next()) {
				Integer _userId = rs.getInt("userId");
				Integer _foodId = rs.getInt("foodId");
				Integer _qty = rs.getInt("qty");
				
				if (FoodId.equals(_foodId) && UserId.equals(_userId)) {
					Cart cm = new Cart();
					
					cm.setUserId(_userId);
					cm.setFoodId(_foodId);
					cm.setQty(_qty);
					
					return cm;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}