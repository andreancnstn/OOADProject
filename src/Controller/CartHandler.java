package Controller;

import java.util.Vector;

import Model.Cart;
import Model.User;
import View.CartView;

public class CartHandler {
	
	private Cart cart;
	private User user;
	private static CartHandler controller;
	
	public CartHandler() {
		cart = new Cart();
		user = new User();
	}
	
	// Singleton
	public static synchronized CartHandler getInstance() {
		if (controller == null) {
			controller = new CartHandler();
		}
		
		return controller;
	}
	
	//get the data of the user who has login
	public void getUser(String email) {
		user = UserHandler.getInstance().getOne(email);
	}
	
	public User getUserObj() {
		return user;
	}
	
	//add item to cart
	public boolean addToCart(Integer foodId, Integer qty) {
		if (qty < 0) {
			return false;
		}
		
		if (isFoodExist(foodId) == -1) { 			//if the food has not added in the cart
			Cart cm = new Cart();
			cm.setUserId(user.getId());
			cm.setFoodId(foodId);
			cm.setQty(qty);
			cm.addToCart();
			
		} else {									//if the food has added in the cart
			Integer _foodId = isFoodExist(foodId);
			Cart cm = new Cart();
			
			cart = cm.getOne(_foodId, user.getId());
			
			updateQty(qty + cart.getQty(), _foodId);
		}
		
		return true;
	}
	
	//remove one item from cart
	public void removeFromCart(Integer foodId) {
		cart.removeFromCart(foodId);
	}
	
	//remove all item in the cart
	public void removeAll() {
		cart.removeAll();
	}
	
	//check if the food is exist in the cart
	public Integer isFoodExist(Integer foodId) {
		return cart.isFoodExist(foodId, user.getId());
	}
	
	//update the 
	public void updateQty(Integer qty, Integer foodId) {
		cart.updateQty(qty, user.getId(), foodId);
	}
	
	public Vector<Cart> viewAll() {
		cart.setUserId(user.getId());
		return cart.viewAll();
	}
	
	public CartView viewMyCart() {
		return new CartView();
	}
	

}

