package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import Controller.FoodHandler;
import Model.Food;

public class FoodView extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	JButton addBtn, removeBtn, changeStatBtn, homeBtn;
	DefaultTableModel dtm;
	JLabel nameLbl, priceLbl, descLbl, judulLbl;
	JTextField nameTxt, priceTxt, descTxt;
	Vector<Object> v;
	
	public FoodView() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		judulLbl = new JLabel("Add New Menu");
		judulLbl.setBounds(262, 360, 300, 30);
		
		nameLbl = new JLabel("Food Name");
		nameLbl.setBounds(20, 400, 200, 25);
		priceLbl = new JLabel("Price");
		priceLbl.setBounds(20, 440, 200, 25);
		descLbl = new JLabel("Description");
		descLbl.setBounds(20, 480, 200, 25);
		
		nameTxt = new JTextField();
		nameTxt.setBounds(120, 400, 460, 25);
		priceTxt = new JTextField();
		priceTxt.setBounds(120, 440, 460, 25);
		descTxt = new JTextField();
		descTxt.setBounds(120, 480, 460, 25);
		
		addBtn = new JButton("Add new Food");
		addBtn.addActionListener(this);
		removeBtn = new JButton("Remove food");
		removeBtn.addActionListener(this);
		changeStatBtn = new JButton("Change Status");
		changeStatBtn.addActionListener(this);
		homeBtn = new JButton("HOME");
		homeBtn.setBounds(20, 10, 100, 25);
		homeBtn.addActionListener(this);
		
		table = new JTable();
		loadFoodData();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 50, 600, 300);
		
		panelBtn.add(addBtn);
		panelBtn.add(changeStatBtn);
		panelBtn.add(removeBtn);
		
		panel.add(judulLbl);
		panel.add(nameLbl);
		panel.add(priceLbl);
		panel.add(descLbl);
		
		panel.add(nameTxt);
		panel.add(priceTxt);
		panel.add(descTxt);
		panel.add(homeBtn);
		panel.add(scrollPane);
		
		init();
	}
	
	private void init() {
		setTitle("Manage Food");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		FoodHandler fh = new FoodHandler();
		if (e.getSource() == addBtn) {
			int price = 0;
			try {
				price = Integer.parseInt(priceTxt.getText());
			} catch (Exception e2) {
				// TODO: handle exception
				displayMsg("Price must be Number !");
			}
			if (fh.addFood(nameTxt.getText(), price, descTxt.getText())) {
				displayMsg("Food succesfully added");
				loadFoodData();
			}
		}
		else if (e.getSource() == removeBtn) {
			dialogBoxPanel = new JPanel();
			dialogBoxPanel.setSize(new Dimension(250,100));
			dialogBoxPanel.setLayout(null);
			
			int row = table.getSelectedRow();
			String foodname = "" + table.getValueAt(row, 1);
			
			JLabel dialogText = new JLabel("Are you sure want to delete menu : " + foodname + " ?");
			dialogText.setBounds(75,45,300,30);
			dialogBoxPanel.add(dialogText);
			
			UIManager.put("OptionPane.minimumSize", new Dimension(400,200));
			int result = JOptionPane.showConfirmDialog(null, dialogBoxPanel, "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		
			switch(result) {
			case 0:
				if (fh.deleteFood(Integer.parseInt(table.getValueAt(row, 0).toString()))) {
					displayMsg("Food successfully deleted");
				}
				loadFoodData();
				break;
			case 1:
				loadFoodData();
				break;
			}
		}
		else if (e.getSource() == changeStatBtn) {
			dialogBoxPanel = new JPanel();
			dialogBoxPanel.setSize(new Dimension(250,100));
			dialogBoxPanel.setLayout(null);
			
			int row = table.getSelectedRow();
			String foodname = "" + table.getValueAt(row, 1);
			
			JLabel dialogText = new JLabel("Are you sure want to change " + foodname + " status ?");
			dialogText.setBounds(50,45,300,30);
			dialogBoxPanel.add(dialogText);
			
			UIManager.put("OptionPane.minimumSize", new Dimension(400,200));
			int result = JOptionPane.showConfirmDialog(null, dialogBoxPanel, "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			switch(result) {
			case 0:
				if (fh.changeStatus(Integer.parseInt(table.getValueAt(row, 0).toString()))) {
					displayMsg("Status successfully change");
				}
				loadFoodData();
				break;
			case 1:
				loadFoodData();
				break;
			}
		}
		else if (e.getSource() == homeBtn) {
			dispose();
			ChefView cv = new ChefView();
		}
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void loadFoodData () {
		String header[] = {"Food ID" , "Food Name", "Price", "Description", "Status"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		Vector<Food> v = new FoodHandler().viewAll();
		
		for(int i=0; i < v.size(); i++) {
			dtm.addRow(new Object[] {v.get(i).getFoodId(), v.get(i).getName(), v.get(i).getPrice(), v.get(i).getDescription(), v.get(i).getStatus()});
		}
		
		table.setModel(dtm);
	}
}
