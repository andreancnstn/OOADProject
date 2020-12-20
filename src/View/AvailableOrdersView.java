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

public class AvailableOrdersView extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JPanel panel1, panel, panelBtn, dialogBoxPanel;
	JTable table;
	JScrollPane scrollPane;
	JButton takeOrderBtn;
	DefaultTableModel dtm;
	JLabel orderIdLbl, judulLbl;
	JTextField orderIdTxt;
	Vector<Object> v;
	
	public AvailableOrdersView() throws HeadlessException {
		// TODO Auto-generated constructor stub
		panel1 = new JPanel();
		panel1.setLayout(new BorderLayout(0,0));
		setContentPane(panel1);
		
		panel = new JPanel();
		panelBtn = new JPanel(new GridLayout(1,3));
		
		panel1.add(panel, BorderLayout.CENTER);
		panel1.add(panelBtn, BorderLayout.SOUTH);
		panel.setLayout(null);
		

		takeOrderBtn = new JButton("Take Order");
		takeOrderBtn.addActionListener(this);

		
		table = new JTable();
		loadAvailableOrders();
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(table);
		scrollPane.setBounds(0, 20, 600, 330);
		
		panelBtn.add(takeOrderBtn);

		panel.add(scrollPane);
		
		init();
	}
	private void init() {
		setTitle("Available Orders");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		OrderHandler oh = new OrderHandler();
//		UserHandler uh = new UserHandler();
		if (e.getSource() == takeOrderBtn) {
			
			dialogBoxPanel = new JPanel();
			dialogBoxPanel.setSize(new Dimension(250,100));
			dialogBoxPanel.setLayout(null);
			
			int row = table.getSelectedRow();
			
			String orderidd = "" + table.getValueAt(row, 0);
			
			JLabel dialogText = new JLabel("Want to take this order? (OrderId: " + orderidd + ")");
			dialogText.setBounds(75,45,300,30);
			dialogBoxPanel.add(dialogText);
			
			UIManager.put("OptionPane.minimumSize", new Dimension(400,200));
			int result = JOptionPane.showConfirmDialog(null, dialogBoxPanel, "File", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
		
			switch(result) {
			case 0: // TODO : orderhandler takeorder()
				if (oh.takeOrder(Integer.parseInt(orderidd), 2)){ //TODO: agar '2' nya diganti driverId yang nerima order.. how..
					displayMsg("Order succesfully taken!");
				}
				//TODO user handler method
				//   uh.viewUserInformation();
				break;
			case 1:
				loadAvailableOrders();
				break;
			}
		}
		
	}
	
	public void displayMsg(String msg) {
		JOptionPane.showMessageDialog(this, msg);
	}
	
	public void loadAvailableOrders () {
		DatabaseConnection c = new DatabaseConnection();
		String header[] = {"Order ID" , "Date", "Address", "userId"};
		DefaultTableModel dtm = new DefaultTableModel(header, 0);
		
		c.resultSet = c.query("SELECT * FROM tblorder"); //TODO where status not received,ordered,finished
		
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
