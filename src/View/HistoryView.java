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

public class HistoryView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	JButton viewDetailBtn;
	DefaultTableModel dtm;
	JLabel orderIdLbl, judulLbl;
	JTextField orderIdTxt;
	Vector<Object> v;
	
	public HistoryView() throws HeadlessException {
		// TODO Auto-generated constructor stub
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);
		

		viewDetailBtn = new JButton("View Detail");
		viewDetailBtn.addActionListener(this);

		
		table = new JTable();
		loadHistory();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 330);
		
		panelBtn.add(viewDetailBtn);

		panel.add(scrollPane);
		
		init();
	}
	private void init() {
		setTitle("View Order History");
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
			dialogBoxPanel = new JPanel();
			dialogBoxPanel.setSize(new Dimension(250,100));
			dialogBoxPanel.setLayout(null);
			
			int row = table.getSelectedRow();
			
			String orderidd = "" + table.getValueAt(row, 0);
			
			Order ord = oh.getOne(Integer.parseInt(orderidd));
			HistoryDetailView hdv = new HistoryDetailView(ord);
		}
		
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void loadHistory () {
		DatabaseConnection c = new DatabaseConnection();
		String header[] = {"Order ID" , "Date", "Address", "userId"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		c.resultSet = c.query("SELECT * FROM tblorder WHERE status LIKE 'Finished'");
		
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
