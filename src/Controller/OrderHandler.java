package Controller;

import java.awt.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

import Model.Employee;
import Model.Food;
import Model.Order;
import Model.OrderDetail;
import Model.User;
import View.AvailableOrdersView;

public class OrderHandler {
	Order o = new Order();
	OrderDetail od = new OrderDetail();
	private static OrderHandler oh;
	private Employee emp;
	
	public OrderHandler() {
		// TODO Auto-generated constructor stub
		emp = new Employee();
	}
	
	public static synchronized OrderHandler getInstance() {
		if (oh == null) oh = new OrderHandler();
		
		return oh;
	}
	
	public boolean addOrder(User user) {
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
	
	public Order getOne(int orderId) {
		Order o = new Order();
		return o.getOne(orderId);
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
	public Vector<Order> viewDetailById(int orderId) {
		Vector<Order> order = o.viewDetailById(orderId);
		return order;
		
	}
	public boolean updateStatus(int orderId, String status) {
		return false;
		
	}
	public boolean takeOrder(int orderId, int driverId) {
		o.takeOrder(orderId, driverId);
		return true;
		
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
