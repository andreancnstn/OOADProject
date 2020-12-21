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

import Controller.FoodHandler;
import Controller.OrderHandler;
import Main.DatabaseConnection;
import Model.Order;

public class OrdersView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	
	JButton FilterFinishedOrderBtn;
	JButton FilterActiveOrderBtn;
	JButton viewDetailBtn;
	JButton cancelBtn;
	
	DefaultTableModel dtm;
	JLabel orderIdLbl, judulLbl;
	JTextField orderIdTxt;
	Vector<Object> v;
	
	public OrdersView() throws HeadlessException {
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
		
		//Filter
		FilterFinishedOrderBtn = new JButton("View Only Finished Orders");
		FilterFinishedOrderBtn.addActionListener(this);
		FilterActiveOrderBtn = new JButton("View Only Active Order");
		FilterActiveOrderBtn.addActionListener(this);
		
		//cancelBtn (muncul setelah klik filterActiveOrderBtn)
		cancelBtn = new JButton("Cancel order");
		cancelBtn.addActionListener(this);
		cancelBtn.setVisible(false);

		
		table = new JTable();
		loadEntries("SELECT * FROM tblorder");
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 330);
		
		panelBtn.add(viewDetailBtn);
		panelBtn.add(FilterFinishedOrderBtn);
		panelBtn.add(FilterActiveOrderBtn);
		panelBtn.add(cancelBtn);

		panel.add(scrollPane);
		
		init();
	}
	private void init() {
		setTitle("View Orders");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderHandler oh = new OrderHandler();
		if (e.getSource() == FilterFinishedOrderBtn) {
//			TODO yg skrg masih pakai raw query, blm pakai OrderHandler samsek
			loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Finished'"); //WHERE userId = CURRENT USER ID
			

//			harusnya kayak gw comment ini
//			Vector v = oh.viewAllHistory(/*CURRENT USERID*/);
//			lalu tampilin vectornya di tabel

		}
		if (e.getSource() == FilterActiveOrderBtn) {
			//TODO ini juga masih pakai raw query, blm pakai OrderHandler samsek
			loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Not Accepted'");
			
			
			FilterActiveOrderBtn.setVisible(false);
			FilterFinishedOrderBtn.setVisible(false);
			cancelBtn.setVisible(true);
		}
		
		if (e.getSource() == cancelBtn) {
			
			int row = table.getSelectedRow();
			String orderidd = "" + table.getValueAt(row, 0);
			int ord = Integer.parseInt(orderidd);
			//confirm dialog
			JLabel dialogText = new JLabel("Are you sure to cancel order?");
			dialogText.setBounds(50,45,300,30);
			dialogBoxPanel.add(dialogText);
			
			UIManager.put("OptionPane.minimumSize", new Dimension(400,200));
			int result = JOptionPane.showConfirmDialog(null, dialogBoxPanel, "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
			switch(result) {
			case 0:
				if (oh.removeOrder(ord)){
					displayMsg("Cancel successful!");
				}
				else {
					displayMsg("Cancel unsuccessful!");
				}

				FilterActiveOrderBtn.setVisible(true);
				FilterFinishedOrderBtn.setVisible(true);
				cancelBtn.setVisible(false);
				loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Not Accepted'");
				break;
			case 1:
				FilterActiveOrderBtn.setVisible(true);
				FilterFinishedOrderBtn.setVisible(true);
				cancelBtn.setVisible(false);
				loadEntries("SELECT * FROM tblorder WHERE status LIKE 'Not Accepted'");
				break;
			}
		}
		
		if (e.getSource() == viewDetailBtn) {
			
			int row = table.getSelectedRow();
			String orderidd = "" + table.getValueAt(row, 0);
			
			Order ord = oh.getOne(Integer.parseInt(orderidd));
			DetailsView hdv = new DetailsView(ord);
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
