package View.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CartHandler;
import Controller.FoodHandler;
import Controller.UserHandler;
import Model.Food;
import View.OrdersView;

import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;

public class UserHomePageView extends JFrame {

	private JPanel contentPane;
	private JTable foodTable;
	private JLabel idValue, nameValue, priceValue, descValue;
	private JTextField qtyTxt;
	private JButton addToCart, viewCart, ordersViewBtn;
	
	private Vector<Food> item;
	
	public UserHomePageView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("JREBFOOD");
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(265, 13, 151, 43);
		contentPane.add(titleLbl);
		
		JLabel tableLbl = new JLabel("The List of Food Items");
		tableLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		tableLbl.setBounds(268, 72, 145, 16);
		contentPane.add(tableLbl);
		
		foodTable = new JTable();
		foodTable.setBounds(41, 101, 600, 150);
		
		JScrollPane foodSp = new JScrollPane(foodTable);
		foodSp.setBounds(41, 101, 600, 130);
		contentPane.add(foodSp);
		
		JLabel detailLbl = new JLabel("The Detail of Food Item :");
		detailLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		detailLbl.setBounds(41, 244, 151, 16);
		contentPane.add(detailLbl);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		idLbl.setBounds(41, 273, 80, 20);
		contentPane.add(idLbl);
		
		idValue = new JLabel("-");
		idValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		idValue.setBounds(133, 274, 150, 20);
		contentPane.add(idValue);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameLbl.setBounds(41, 304, 80, 20);
		contentPane.add(nameLbl);
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		priceLbl.setBounds(41, 337, 80, 20);
		contentPane.add(priceLbl);
		
		JLabel descLbl = new JLabel("Description");
		descLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		descLbl.setBounds(333, 304, 80, 20);
		contentPane.add(descLbl);
		
		JLabel qtyLbl = new JLabel("Quantity");
		qtyLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		qtyLbl.setBounds(333, 337, 80, 20);
		contentPane.add(qtyLbl);
		
		nameValue = new JLabel("-");
		nameValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		nameValue.setBounds(133, 305, 150, 20);
		contentPane.add(nameValue);
		
		priceValue = new JLabel("-");
		priceValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		priceValue.setBounds(133, 338, 150, 20);
		contentPane.add(priceValue);
		
		descValue = new JLabel("-");
		descValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		descValue.setBounds(440, 305, 150, 20);
		contentPane.add(descValue);
		
		qtyTxt = new JTextField();
		qtyTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		qtyTxt.setBounds(440, 338, 150, 20);
		contentPane.add(qtyTxt);
		qtyTxt.setColumns(10);
		
		addToCart = new JButton("Add to Cart");
		addToCart.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addToCart.setBounds(291, 404, 100, 25);
		contentPane.add(addToCart);
		
		viewCart = new JButton("My Cart");
		viewCart.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		viewCart.setBounds(544, 68, 97, 25);
		contentPane.add(viewCart);
		
		ordersViewBtn = new JButton("My Orders");
		ordersViewBtn.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		ordersViewBtn.setBounds(41, 68, 97, 25);
		contentPane.add(ordersViewBtn);
		
		loadData();
		addListener();
	}
	
	private void addListener() {
		foodTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = foodTable.getSelectedRow();
				idValue.setText(foodTable.getValueAt(row, 0).toString());
				nameValue.setText(foodTable.getValueAt(row, 1).toString());
				priceValue.setText(foodTable.getValueAt(row, 2).toString());
				descValue.setText(foodTable.getValueAt(row, 3).toString());
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		viewCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				CartHandler.getInstance().viewMyCart().setVisible(true);
			}
		});
		
		addToCart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer foodId = Integer.parseInt(idValue.getText());
				Integer qty = Integer.parseInt(qtyTxt.getText());
				
				Boolean addItem = CartHandler.getInstance().addToCart(foodId, qty);
				
				if (addItem == false) {
					String message = "Quantity must be more than 0";
					JOptionPane.showMessageDialog(addToCart, message);
				} else {
					String message = "Successfully Add to Cart!";
					JOptionPane.showMessageDialog(addToCart, message);
				}
			}
		});
		
		ordersViewBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new OrdersView();
			}
		});
	}

	private void loadData() {
		item = new FoodHandler().viewAll();
		String[] header = {"Food ID" , "Food Name", "Price", "Description"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		for(int i = 0; i < item.size(); i++) {
			dtm.addRow(new Object[] {item.get(i).getFoodId(), item.get(i).getName(), 
					item.get(i).getPrice(), item.get(i).getDescription()});
		}
		foodTable.setModel(dtm);		
	}
}
