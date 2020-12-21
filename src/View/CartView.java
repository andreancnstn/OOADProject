package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Controller.CartHandler;
import Controller.OrderHandler;
import Model.Cart;
import View.User.UserHomePageView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class CartView extends JFrame {

	private JPanel contentPane;
	private JTable cartTable;
	private JLabel idValue, nameValue, priceValue;
	private JTextField qtyTxt;
	private JButton removeAll, removeOne, updateQty, backToHome, checkOut;
	
	private Vector<Vector<String>> item;
	private Vector<String> header, detail;

	public CartView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("MY CART");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		titleLbl.setBounds(271, 13, 139, 65);
		contentPane.add(titleLbl);
		
		cartTable = new JTable();
		cartTable.setBounds(41, 101, 600, 150);
		
		JScrollPane cartSp = new JScrollPane(cartTable);
		cartSp.setBounds(41, 101, 600, 130);
		contentPane.add(cartSp);
		
		JLabel detailLbl = new JLabel("The Detail of Food Item :");
		detailLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		detailLbl.setBounds(41, 244, 151, 16);
		contentPane.add(detailLbl);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		idLbl.setBounds(41, 273, 80, 20);
		contentPane.add(idLbl);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameLbl.setBounds(41, 304, 80, 20);
		contentPane.add(nameLbl);
		
		JLabel priceLbl = new JLabel("Price");
		priceLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		priceLbl.setBounds(333, 273, 80, 20);
		contentPane.add(priceLbl);
		
		JLabel qtyLbl = new JLabel("Quantity");
		qtyLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		qtyLbl.setBounds(333, 304, 80, 20);
		contentPane.add(qtyLbl);
		
		idValue = new JLabel("-");
		idValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		idValue.setBounds(133, 274, 150, 20);
		contentPane.add(idValue);
		
		nameValue = new JLabel("-");
		nameValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		nameValue.setBounds(133, 305, 150, 20);
		contentPane.add(nameValue);
		
		priceValue = new JLabel("-");
		priceValue.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		priceValue.setBounds(440, 274, 150, 20);
		contentPane.add(priceValue);
		
		qtyTxt = new JTextField();
		qtyTxt.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		qtyTxt.setBounds(440, 305, 150, 20);
		contentPane.add(qtyTxt);
		qtyTxt.setColumns(10);
		
		removeAll = new JButton("Remove All Items");
		removeAll.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		removeAll.setBounds(95, 380, 140, 25);
		contentPane.add(removeAll);
		
		removeOne = new JButton("Remove Selected Item");
		removeOne.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		removeOne.setBounds(247, 380, 140, 25);
		contentPane.add(removeOne);
		
		updateQty = new JButton("Update Quantity");
		updateQty.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		updateQty.setBounds(399, 380, 140, 25);
		contentPane.add(updateQty);
		
		backToHome = new JButton("Back to Home");
		backToHome.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		backToHome.setBounds(41, 64, 105, 25);
		contentPane.add(backToHome);
		
		checkOut = new JButton("Check Out");
		checkOut.setFont(new Font("Times New Roman", Font.PLAIN, 12));
		checkOut.setBounds(541, 64, 100, 25);
		checkOut.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (OrderHandler.getInstance().addOrder(CartHandler.getInstance().getUserObj())) {
					String message = "Success add order";
					JOptionPane.showMessageDialog(checkOut, message);
					loadData();
				}
				else {
					String message = "failed add order";
					JOptionPane.showMessageDialog(checkOut, message);
				}
			}
		});
		contentPane.add(checkOut);
		
		loadData();
		addListener();
	}

	private void addListener() {
		
		removeAll.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CartHandler.getInstance().removeAll();
				
				String message = "Successfully Remove All Items.";
				JOptionPane.showMessageDialog(removeAll, message);
				loadData();
			}
		});
		
		removeOne.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer foodId = Integer.parseInt(idValue.getText());
				CartHandler.getInstance().removeFromCart(foodId);
				
				String message = "Successfully Remove The Item.";
				JOptionPane.showMessageDialog(removeOne, message);
				loadData();
			}
		});
		
		backToHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserHomePageView homepage = new UserHomePageView();
				homepage.setVisible(true);
			}
		});
		
		updateQty.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer qty = Integer.parseInt(qtyTxt.getText());
				Integer foodId = Integer.parseInt(idValue.getText());
				
				CartHandler.getInstance().updateQty(qty, foodId);
				
				String message = "Successfully Update The Quantity";
				JOptionPane.showMessageDialog(updateQty, message);
				loadData();
			}
		});
		
		cartTable.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = cartTable.getSelectedRow();
				idValue.setText(cartTable.getValueAt(row, 0).toString());
				nameValue.setText(cartTable.getValueAt(row, 1).toString());
				priceValue.setText(cartTable.getValueAt(row, 2).toString());
				qtyTxt.setText(cartTable.getValueAt(row, 3).toString());
			}
		});
	}

	private void loadData() {
		item = new Vector<>();
		
		header = new Vector<>();
		header.add("Food ID");
		header.add("Food Name");
		header.add("Food Price");
		header.add("Quantity");
		
		Vector<Cart> listItem = CartHandler.getInstance().viewAll();
		
		for (Cart cartModel : listItem) {
			Cart cm = cartModel;
			detail = new Vector<>();
			
			detail.add(cm.getFoodId().toString());
			detail.add(cm.getFood().getName());
			detail.add(cm.getFood().getPrice().toString());
			detail.add(cm.getQty().toString());
			
			item.add(detail);
		}
		
		DefaultTableModel dtm = new DefaultTableModel(item, header);
		cartTable.setModel(dtm);
	}

}
