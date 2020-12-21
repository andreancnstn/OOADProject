package View;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
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

import Controller.FoodHandler;
import Controller.OrderHandler;
import Main.DatabaseConnection;

public class ProfitView extends JFrame implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	JButton filterByDriverIdBtn, homeBtn;
	DefaultTableModel dtm;

	Vector<Object> v;
	
	public ProfitView() {
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		filterByDriverIdBtn = new JButton("Filter by DriverId");
		filterByDriverIdBtn.addActionListener(this);
		
		homeBtn = new JButton("HOME");
		homeBtn.setBounds(20, 10, 100, 25);
		homeBtn.addActionListener(this);
		
		table = new JTable();
		loadProfit("SELECT * FROM tblorder WHERE status LIKE finished");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 50, 600, 300);
		
		panelBtn.add(filterByDriverIdBtn);


		panel.add(homeBtn);
		panel.add(scrollPane);
		
		init();
	}
	
	private void init() {
		setTitle("View Financial Summary (Profit)");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderHandler oh = new OrderHandler();
		if (e.getSource() == filterByDriverIdBtn) {
			
			int row = table.getSelectedRow();
			String driverIdd = "" + table.getValueAt(row, 4);
	
		//KALAU VERSI SEQUENCE DIAGRAM
//			Vector v = oh.filterDriver(driverIdd); //methodnya masih kosong
//			dtm.addRow(v);
//			table.setModel(dtm);
			
		//KALAU VERSI RAW QUERY
			loadProfit("SELECT * FROM tblorder WHERE status LIKE finished AND driverId =" + driverIdd);
		}
		else if (e.getSource() == homeBtn) {
			dispose();
			ChefView cv = new ChefView();
		}
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void  loadProfit (String que) {
		DatabaseConnection c = new DatabaseConnection();
		String header[] =  {"Order ID" , "Date", "Address", "userId"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		c.resultSet = c.query(que);
		
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
