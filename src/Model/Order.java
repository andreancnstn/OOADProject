package Model;

import java.sql.Date;
import java.util.Vector;
import Main.DatabaseConnection;

public class Order {
	
	private int orderId;
	private Date date;
	private String address;
	private int userId;
	private int driverId;
	private String status;
	
	DatabaseConnection c = new DatabaseConnection();
	
	
	public Order(int orderId, Date date, String address, int userId, int driverId, String status)
			//			DatabaseConnection c) {
	{
		super();
		this.orderId = orderId;
		this.date = date;
		this.address = address;
		this.userId = userId;
		this.driverId = driverId;
		this.status = status;
		//		this.c = c;
	}
	
	public boolean addOrder(int orderId, Date date, String address, int userId, int driverId, String status)/*(User user, Date date)*/ {
		c.addOrder(orderId, date, address, userId, driverId, status);
		return true;
		
	}
	
	public void addDetail(int orderId, int foodId, int qty) {
		//TODO
	}
	
	public Order getOne(int orderId) {
		Order ord = null;
		//TODO
		return ord;
		
	}
	public boolean updateStatus(int orderId, String status) {
		//-	Must be either “accepted”, “ordered” or “finished”
		c.update("UPDATE order SET status='"+ status +"' WHERE orderID=" + orderId);
		return true;
		
	}
	public boolean takeOrder(int orderId, int driverId) {
		//TODO
		return true;
		
	}
	public boolean removeOrder(int orderId) {
		c.update("DELETE FROM order WHERE orderId=" + orderId);
		return true;
		
	}
	public void removeDetail(int orderId) {
		//TODO
	}
	
	public Vector<Order> getAll() { //di diagramnya list<order> tp ini bikin vector. Gapapah?
		Vector<Order> allOrder = new Vector<Order>();
		Order ordd = null;
		
		c.resultSet = c.query("SELECT * FROM order");
		
		try {
			while(c.resultSet.next() == true) {
				for (int i = 0; i <= c.metaData.getColumnCount(); i++) {
					int orderIdd = c.resultSet.getInt(1);
					Date datee = c.resultSet.getDate(2);
					String addresss = c.resultSet.getString(3);
					int userIdd = c.resultSet.getInt(4);
					int driverIdd = c.resultSet.getInt(5);
					String statuss = c.resultSet.getString(6);
					ordd = new Order(orderIdd, datee, addresss, userIdd, driverIdd, statuss);
				}
				allOrder.add(ordd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		return allOrder;
	}
	public Vector<Order> viewDetailById(int orderId) { // diagram: return list<orderdetail>
		Vector<Order> viewDetail = new Vector<Order>();
		return viewDetail;
	}
	

}
