package Controller;

import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import Model.Food;
import Model.Order;
import Model.OrderDetail;
import Model.User;
import View.AvailableOrdersView;

public class OrderHandler {
	Order o = new Order();
	OrderDetail od = new OrderDetail();
	
	public OrderHandler() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean addOrder(User user) {
		//TODO error nih, kayaknya gara2 date nya, gmn ya
//		Date date = new Date();
//		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		long millis=System.currentTimeMillis();  
        java.sql.Date sqlDate=new java.sql.Date(millis);  

		o.addOrder(user, sqlDate);
		
		return false;
		
	}

	public void addDetail(int orderId, int foodId, int qty) {
		od.addDetail(orderId, foodId, qty);
		
	}
	public boolean removeOrder(int orderId) {
		return false;
		
	}
	public boolean removeDetail(int orderId) {
		return false;
		
	}
	
	public Order getOne() {
		//TODO
//		Order o = new Order(0, null, null, 0, 0, null);
//		return o;
		return null;
	}
	
	//views
	public List viewAllHistory(int id) {
		return null;
		
	}
	public List viewById(int orderId) {
		return null;
		
	}
	public List viewOrderList(String status) {
		return null;
		
	}
	public List viewDetailById(int orderId) {
		return null;
		
	}
	public boolean updateStatus(int orderId, String status) {
		return false;
		
	}
	public boolean takeOrder(int orderId, int driverId) {
		return false;
		
	}
	
	
//VIEWS
	//ORDER HANDLER TOTAL ADA 8 VIEW
	//kalau di class diagramnya return typenya view, tp ini pakai void
	//jdi di dlmnya isi new View
	public void viewTakenOrder() {
		
	}
	
	//TODO beberapa views lainnya
	
	public void viewAvailableOrder() {
		AvailableOrdersView v = new AvailableOrdersView();
	}
	
}
