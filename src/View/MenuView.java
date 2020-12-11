package View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Controller.FoodHandler;
import Model.Food;

public class MenuView extends JFrame implements ActionListener{
	JPanel mainPanel, panel1, btnPanel;
	JLabel label, qtyLabel;
	JTextField qtyTxt;
	JButton homeBtn, menuBtn, addToCartBtn;
	JTable table;
	DefaultTableModel dtm;
	JScrollPane jScrollPane;
	
	int menuCount;

	Vector<Food> menu;

	public MenuView() {
		menu = new FoodHandler().viewAll();
		menuCount = menu.size();
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout(0,0));
		setContentPane(mainPanel);
		
		panel1 = new JPanel();
		btnPanel = new JPanel(new GridLayout(3,1));
		
		mainPanel.add(panel1, BorderLayout.NORTH);
		mainPanel.add(btnPanel, BorderLayout.SOUTH);
		
		label = new JLabel("MENU LIST");
		homeBtn = new JButton("HOME");
		homeBtn.setBounds(0, 0, 20, 20);
		homeBtn.addActionListener(this);
		
		qtyLabel = new JLabel("Quantity");
		qtyLabel.setBounds(10, 500, 100, 25);
		qtyTxt = new JTextField();
		qtyTxt.setBounds(110, 550, 300, 25);
		addToCartBtn = new JButton("Add to Cart");
		
		table = new JTable();
		loadMenu();
		jScrollPane = new JScrollPane();
		jScrollPane.setViewportView(table);
		
		panel1.add(homeBtn);
		panel1.add(label);
		panel1.add(jScrollPane);
		
		btnPanel.add(qtyLabel);
		btnPanel.add(qtyTxt);
		btnPanel.add(addToCartBtn);
		
		init();
	}
	
	private void loadMenu() {
		// TODO Auto-generated method stub
		
		String[] colName = {"Food ID" , "Food Name", "Price", "Description", "Status"};
		dtm = new DefaultTableModel(colName, 0);
		
		for(int i = 0; i < menuCount; i++) {
			dtm.addRow(new Object[] {menu.get(i).getFoodId(), menu.get(i).getName(), menu.get(i).getPrice(), menu.get(i).getDescription(), menu.get(i).getStatus()});
		}
		table.setModel(dtm);
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
		if (e.getSource() == homeBtn) {
			//Ke Home
//			System.out.println(e.getActionCommand());
		}
		else if (e.getSource() == addToCartBtn) {
			//get qty
			//isi ke cart
		}
		else {
			System.out.println(e.getActionCommand());
			String foodName = e.getActionCommand();
			FoodHandler fh = new FoodHandler();
			int foodId = fh.getFoodID(foodName);
			System.out.println(e.getActionCommand());
			
			//masuk ke detail food page
		}
	}

}
