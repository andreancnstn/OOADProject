package Controller;

import java.util.Vector;

import Model.Food;
import View.FoodView;
import View.MenuView;

public class FoodHandler {
	FoodView f;
	Food fModel = new Food();
	MenuView menu;
	
	public void viewManageFoodForm() {
		f = new FoodView();
		
	}
	
	public void viewMenu() {
		menu = new MenuView();
	}
	
	public Vector<Food> viewAll() {
		Vector<Food> v = fModel.viewAll();
		
		return v;
	}
	
	public Vector<Food> viewMenuList() {
		Vector<Food> v = fModel.viewMenu();
		
		return v;
	}

	public boolean addFood(String name, int price, String desc) {
		
		if (validateInput(name, price, desc)) {
			fModel.addFood(name, price, desc);
			return true;
		}
		
		return false;
	}
	
	public boolean validateInput(String name, int price, String desc) {
		
		if (!fModel.validateName(name)) {
			f.displayMsg("Name cannot be empty and must be unique !");
			return false;
		}
		
		if (price <= 0 || price != (int)price) {
			f.displayMsg("Price cannot be below and cannot be empty and must be Integer!");
			return false;
		}
		
		if (desc.isEmpty()) {
			f.displayMsg("Description cannot be empty !");
			return false;
		}
		
		return true;
	}
	
	public boolean deleteFood(int foodId) {
		fModel.deleteFood(foodId);
		
		return true;
	}
	
	public boolean changeStatus(int foodId) {
		fModel.changeStatus(foodId);
		
		return true;
	}
	
	public boolean checkStatus(Food f) {
		
		return true;
	}
	
	public Food getFood(int foodId) {
		
		return fModel.getFood(foodId);
	}
	
	public int getFoodID(String foodName) {
		int id = fModel.getFoodID(foodName);
		
		return id;
	}
}
