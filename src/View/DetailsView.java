package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
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

import Controller.EmployeeHandler;
import Controller.FoodHandler;
import Controller.OrderHandler;
import Main.DatabaseConnection;
import Model.Order;
import View.User.EmployeeLoginView;

public class DetailsView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	JButton viewDetailBtn, homeBtn;
	DefaultTableModel dtm;
	JLabel orderIdLbl, judulLbl;
	JTextField orderIdTxt;
	Vector<Object> v;
	
	
	Order ord = null;
	
	public DetailsView(Order ord) throws HeadlessException {
		this.ord = ord;
		
		// TODO Auto-generated constructor stub
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);

		
		table = new JTable();
		loadHistoryDetail();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 330);
		
		homeBtn = new JButton("HOME");
		homeBtn.setBounds(20, 10, 100, 25);
		homeBtn.addActionListener(this);

		panel.add(homeBtn);
		panel.add(scrollPane);
		
		init();
	}
	private void init() {
		setTitle("View Details");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == homeBtn) {
			dispose();
			new DriverView(EmployeeHandler.getInstance().getLogedinEmpId(EmployeeLoginView.empEmail));
		}
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void loadHistoryDetail () {
		DatabaseConnection c = new DatabaseConnection();
		String header[] = {"Order ID" , "Food ID", "Qty"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		int ordId = ord.getOrderId();
		
		c.resultSet = c.query("SELECT * FROM orderdetail WHERE orderId = " + ordId);
		
		try {
			while(c.resultSet.next() == true) {
				v = new Vector<>();
				for (int i = 1; i <= c.metaData.getColumnCount(); i++) {
					v.add(c.resultSet.getObject(i));
				}
				dtm.addRow(v);
			}
			table.setModel(dtm);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
