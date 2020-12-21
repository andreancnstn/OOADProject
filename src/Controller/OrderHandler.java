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
import View.HistoryView;
import View.OrderQueueView;
import View.ProfitView;

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

		if (o.addOrder(user, sqlDate)) return true;
		
		return false;
		
	}

	public void addDetail(int orderId, int foodId, int qty) {
		od.addDetail(orderId, foodId, qty);
		
	}
	
	public boolean removeOrder(int orderId) {
		return o.removeOrder(orderId);
	}
	
//	public boolean removeDetail(int orderId) {
//		return false;
//		
//	}
	
	public Order getOne(int orderId) {
		return o.getOne(orderId);
	}
	
	public Vector<Order> viewAllHistory(int id) {
		return o.viewAllHistory(id);
	}
	
//	public List viewById(int orderId) {
//		return null;
//		
//	}
//	public List viewOrderList(String status) {
//		return null;
//		
//	}
	public Vector<Order> viewDetailById(int orderId) {
		Vector<Order> order = o.viewDetailById(orderId);
		return order;
		
	}
	public boolean updateStatus(int orderId, String status) {
		o.updateStatus(orderId, status);
		return true;
		
	}
	public boolean takeOrder(int orderId, int driverId) {
		o.takeOrder(orderId, driverId);
		return true;
		
	}
	
	
//	public void viewTakenOrder() {
//		
//	}
	
//	private void viewDetails() {
//		// TODO Auto-generated method stub
//
//	}
	
	public HistoryView viewHistory() {
		return new HistoryView();
	}
	
//	private void viewManageStatusForm() {
//		// TODO Auto-generated method stub
//
//	}
	
	public ProfitView viewProfit() {
		return new ProfitView();
	}
	
//	public void filterDriver(int driverId) { //di class diagram ini dia return view 
//		// TODO Auto-generated method stub
//		
//	}
	
	public OrderQueueView viewOrderQueue() {
		return new OrderQueueView();
	}
	
	public void viewAvailableOrder(Integer id) {
		AvailableOrdersView v = new AvailableOrdersView(id);
	}
	
}
