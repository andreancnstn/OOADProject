package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.EmployeeHandler;
import Controller.FoodHandler;
import Controller.OrderHandler;
import Controller.UserHandler;
import Model.Driver;
import Model.User;
import View.EmployeeView;
import View.FoodView;
import View.HistoryDetailView;
import View.HistoryView;
import View.MenuView;
import View.AvailableOrdersView;

public class MainTest extends JFrame implements ActionListener{
	
	JButton employeeBtn, foodBtn;
	JPanel menuPanel, btnPanel;
	
	FoodHandler fh = new FoodHandler();
	EmployeeHandler eh = new EmployeeHandler();

	User userModelTes = null;
	
	
	public MainTest() {
//		DatabaseConnection connection = new DatabaseConnection(); //db connection oke
		
//		menuPanel = new JPanel();
//		menuPanel.setLayout(new BorderLayout(0,0));
//		setContentPane(menuPanel);
//		
//		btnPanel = new JPanel(new GridLayout(1,2));
//		menuPanel.add(btnPanel, BorderLayout.CENTER);
//		
//		employeeBtn = new JButton("Employee");
//		employeeBtn.addActionListener(this);
//		foodBtn = new JButton("Food");
//		foodBtn.addActionListener(this);
//		
//		
//		btnPanel.add(employeeBtn);
//		btnPanel.add(foodBtn);
//		
//		init();
		
//		MenuView v = new MenuView();
//		FoodView v = new FoodView();
//		EmployeeView v = new EmployeeView();
		
		//INIT UNTUK VIEW AVAILABLE ORDERS
		simulasiBikinFood();					//	BUTUH VIEW:
		simulasiBikinUser();					//		REGISTER USER
		simulasiMasukinOrder();					// 		CART CHECKOUT
		simulasiMasukinOrderDetails();			// 		CART CHECKOUT
		//INIT UNTUK PENCET TAKE ORDER
		simulasiBikinDriver();					//		REGISTER DRIVER

//		AvailableOrdersView ov = new AvailableOrdersView();		// BUTUH VIEW ' VIEW USER INFO '
		HistoryView hdv = new HistoryView();
	}

	private void simulasiBikinDriver() {
		// TODO Auto-generated method stub
		Driver d = new Driver("B 4678 TY", 1);
		d.createDriver(1,"B 1111 TY");
		d.createDriver(2,"B 2222 TY");
		d.createDriver(1,"B 3333 TY");
	}

	private void simulasiBikinFood() {
		FoodHandler fh = new FoodHandler();
//		fh.addFood(name, price, desc);
		fh.addFood("makanan Satu", 11000, "desc makanan Satu");
		fh.addFood("maknana dua", 22000, "desc makanan Dua");
	}

	private void simulasiBikinUser() {
		UserHandler uh = new UserHandler();
//		uh.createAccount(name, address, email, phoneNumber, password);
		uh.createAccount("Budiman", "Jalan Mangga no.3", "budi_man@gmail.com", "0812345678", "inipasswordbudi");
		userModelTes = new User(105,"Budiman", "Jalan Mangga no.3", "budi_man@gmail.com", "0812345678", "inipasswordbudi");
	}

	private void simulasiMasukinOrder() {
		OrderHandler oh= new OrderHandler();
		oh.addOrder(userModelTes);
		
		
	}
	private void simulasiMasukinOrderDetails() {
		OrderHandler oh = new OrderHandler();
//		oh.addDetail(orderId, foodId, qty);
		oh.addDetail(1, 1, 5);
		oh.addDetail(1, 2, 8);
	}

	public static void main(String[] args) {
		new MainTest();

	}
	
	private void init() {
		setTitle("Manage Employee Form");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == employeeBtn) {
			eh.viewManageEmployeeForm();
			this.dispose();
		}
		else if (e.getSource() == foodBtn) {
			fh.viewManageFoodForm();
			this.dispose();
		}
	}

}
