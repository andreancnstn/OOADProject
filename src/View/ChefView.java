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

public class ChefView extends JFrame{
	JPanel contentPanel;
	JButton viewAvailOrderBtn, ManageFoodBtn;
	JLabel pageTitle;

	public ChefView() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		pageTitle = new JLabel("Chef Main Menu");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setBounds(160, 50, 200, 25);
		contentPanel.add(pageTitle);
		
		viewAvailOrderBtn = new JButton("View Order Queue");
		viewAvailOrderBtn.setBounds(160, 200, 300, 45);
		viewAvailOrderBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				OrderHandler.getInstance().viewOrderQueue();
			}
		});
		contentPanel.add(viewAvailOrderBtn);
		
		ManageFoodBtn = new JButton("Manage Food");
		ManageFoodBtn.setBounds(160, 260, 300, 45);
		ManageFoodBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				FoodView f = new FoodView();
			}
		});
		contentPanel.add(ManageFoodBtn);
		
		init();
	}

	private void init() {
		setTitle("Chef Menu");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

}
