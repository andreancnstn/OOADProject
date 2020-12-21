package Model;

import java.sql.Date;
import java.time.LocalDate;
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


	public Order() {
		// TODO Auto-generated constructor stub
	}


	public boolean addOrder(User user, java.sql.Date date){
		c.addOrder(user,date);
		return true;
	}
	
	public void addDetail(int orderId, int foodId, int qty) {
		c.addDetail(orderId, foodId, qty);
	}
	
	public Order getOne(int orderId) {
		Order ord = null;
		
		c.resultSet = c.query("SELECT * FROM tblorder WHERE orderId=" + orderId);
		
		try {
			while(c.resultSet.next() == true) {
				for (int i = 0; i <= c.metaData.getColumnCount(); i++) {
					int orderIdd = c.resultSet.getInt(1);
					Date datee = c.resultSet.getDate(2);
					String addresss = c.resultSet.getString(3);
					int userIdd = c.resultSet.getInt(4);
					int driverIdd = c.resultSet.getInt(5);
					String statuss = c.resultSet.getString(6);
					ord = new Order(orderIdd, datee, addresss, userIdd, driverIdd, statuss);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		//TODO
		return ord;
		
	}
	public boolean updateStatus(int orderId, String status) { //default status: Not Accepted
		//accepted, ordered, cooked, finished
		c.update("UPDATE order SET status='"+ status +"' WHERE orderId=" + orderId); //TODO fix syntax
		return true;
		
	}
	public boolean takeOrder(int orderId, int driverId) {
		updateStatus(orderId, "Accepted");
		c.update("UPDATE order SET driverId='"+ driverId +"' WHERE orderId=" + orderId); //TODO fix syntax
		return true;
		
	}
	public boolean removeOrder(int orderId) {
		c.update("DELETE FROM order WHERE orderId=" + orderId);
		return true;
		
	}
	public void removeDetail(int orderId) {
		//TODO
	}
	
	public Vector<Order> getAll() {
		Vector<Order> allOrder = new Vector<Order>();
		Order ordd = null;
		
		c.resultSet = c.query("SELECT * FROM tblorder");
		
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
		Order ordd = null;
		
		c.resultSet = c.query("SELECT * FROM tblorder WHERE orderId=" + orderId);
		
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
				viewDetail.add(ordd);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		return viewDetail;
	}

	//SETTER GETTER

	public int getOrderId() {
		return orderId;
	}


	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}


	public int getDriverId() {
		return driverId;
	}


	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	

}
