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

public class TakenOrderView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	
	JButton viewDetailBtn;
	JButton orderToChefBtn;
	JButton deliverBtn, homeBtn;
	
	DefaultTableModel dtm;
	JLabel orderIdLbl, judulLbl;
	JTextField orderIdTxt;
	Vector<Object> v;
	
	public TakenOrderView() throws HeadlessException {
		// TODO Auto-generated constructor stub
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);
		
		//ViewDetail
		viewDetailBtn = new JButton("View Order Detail");
		viewDetailBtn.addActionListener(this);
		
		//button buat suruh chef masakin orderan (ClickOrderButton)
		orderToChefBtn = new JButton("Tell Chef To Cook");
		orderToChefBtn.addActionListener(this);
		
		//button buat deliver (if status=cooked change status to delivered)
		deliverBtn = new JButton("Deliver");
		deliverBtn.addActionListener(this);
		
		homeBtn = new JButton("HOME");
		homeBtn.setBounds(20, 10, 100, 25);
		homeBtn.addActionListener(this);
	
		
		table = new JTable();
		loadEntries("SELECT * FROM tblorder WHERE status NOT LIKE 'not accepted'");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 330);
		
		panelBtn.add(viewDetailBtn);
		panelBtn.add(orderToChefBtn);

		panel.add(scrollPane);
		
		init();
	}
	private void init() {
		setTitle("View Taken Orders");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderHandler oh = new OrderHandler();

		if (e.getSource() == viewDetailBtn) {
			
			int row = table.getSelectedRow();
			String orderidd = "" + table.getValueAt(row, 0);
			
			Order ord = oh.getOne(Integer.parseInt(orderidd));
			DetailsView hdv = new DetailsView(ord, 1);
		}
		if (e.getSource() == deliverBtn) {
			
			int row = table.getSelectedRow();
			String orderidd = "" + table.getValueAt(row, 0);
			int ord = Integer.parseInt(orderidd);
			
			//mengupdate status yg diklik to 'delivered'
			if(oh.updateStatus(ord, "delivered")) {
				displayMsg("Success!  Ordered chef to cook!");
			}
			else {
				displayMsg("Order is not cooked yet! Cannot deliver");
			}
			
			
			loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Accepted'");
		}
		if (e.getSource() == orderToChefBtn) {
			
			int row = table.getSelectedRow();
			String orderidd = "" + table.getValueAt(row, 0);
			int ord = Integer.parseInt(orderidd);
			//mengupdate status yg diklik to 'ordered'
			oh.updateStatus(ord, "ordered");
			displayMsg("Success!  Ordered chef to cook!");
			
			loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Accepted'");
		}
		if (e.getSource() == homeBtn) {
			dispose();
			new DriverView(EmployeeHandler.getInstance().getLogedinEmpId(EmployeeLoginView.empEmail));
		}
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void loadEntries (String que) {
		DatabaseConnection c = new DatabaseConnection();
		String header[] = {"Order ID" , "Date", "Address", "userId"};
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
