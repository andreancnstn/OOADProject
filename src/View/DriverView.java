package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.OrderHandler;

public class DriverView extends JFrame{
	JPanel contentPanel;
	JButton viewAvailOrderBtn, OrderHistoryBtn, viewHistoryBtn;
	JLabel pageTitle;
	
	private Integer id;

	public DriverView(Integer EmpId) {
		this.id = EmpId;
		OrderHandler oh = new OrderHandler();
		
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		pageTitle = new JLabel("Driver Main Menu");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setBounds(160, 50, 200, 25);
		contentPanel.add(pageTitle);
		
		viewAvailOrderBtn = new JButton("View Available Order");
		viewAvailOrderBtn.setBounds(160, 200, 300, 45);
		viewAvailOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				oh.viewAvailableOrder(id);
			}
		});
		contentPanel.add(viewAvailOrderBtn);
		
		OrderHistoryBtn = new JButton("View Order History");
		OrderHistoryBtn.setBounds(160, 260, 300, 45);
		OrderHistoryBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				oh.viewHistory();
			}
		});
		contentPanel.add(OrderHistoryBtn);
		
		init();
	}

	private void init() {
		setTitle("Manager Menu");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

}
