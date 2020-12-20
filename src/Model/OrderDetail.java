package Model;

import java.awt.List;
import java.util.Vector;

import Main.DatabaseConnection;

public class OrderDetail {
	int orderId;
	int foodId;
	int qty;
	
	DatabaseConnection c = new DatabaseConnection();
	
	public OrderDetail(int orderId, int foodId, int qty) {
		super();
		this.orderId = orderId;
		this.foodId = foodId;
		this.qty = qty;
	}
	
	public OrderDetail() {
		// TODO Auto-generated constructor stub
	}

	public boolean addDetail(int orderId, int foodId, int qty) {
		//TODO perlu validate if exist yang sama persis return false ga ya
		c.addDetail(orderId, foodId, qty);
		return true;
	}
	public boolean removeOrder(int orderId) {
		//TODO ini mau validate gmn ya. kayak 1 atau 2. atau bukan disini validatenya
		//1.
		c.update("IF EXISTS ( SELECT 1 FROM orderdetail WHERE orderid ="+orderId+") "
				+ "BEGIN DELETE FROM orderdetail WHERE orderId="+ orderId + "END");
		//2.
		if (true/*TODO orderId exist gmn tulisnya ya*/) {
			c.update("DELETE FROM orderdetail WHERE orderId=" + orderId);
			return true;
		} else {
			return false;
		}
	}
	
	public Vector<OrderDetail> viewDetailById(int orderId) {
		Vector<OrderDetail> v = new Vector<OrderDetail>();
		OrderDetail od = null;
		
		c.resultSet = c.query("SELECT * FROM orderdetail where orderId =" + orderId);

		try {
			while(c.resultSet.next() == true) {
				for (int i = 0; i <= c.metaData.getColumnCount(); i++) {
					int orderIdd = c.resultSet.getInt(1);
					int foodIdd = c.resultSet.getInt(2);
					int qtyy = c.resultSet.getInt(3);
					od = new OrderDetail(orderIdd, foodIdd, qtyy);
				}
				v.add(od);
			}
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		return v;
	}
}
