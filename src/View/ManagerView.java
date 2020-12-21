package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.EmployeeHandler;

public class ManagerView extends JFrame{
	
	JPanel contentPanel;
	JButton viewFinanceBtn, viewManageEmployeeBtn;
	JLabel pageTitle;

	public ManagerView() {
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5,5,5,5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
		pageTitle = new JLabel("Manager Main Menu");
		pageTitle.setHorizontalAlignment(SwingConstants.CENTER);
		pageTitle.setBounds(160, 50, 200, 25);
		contentPanel.add(pageTitle);
		
		viewFinanceBtn = new JButton("View Financial Report");
		viewFinanceBtn.setBounds(160, 200, 250, 45);
		viewFinanceBtn.addActionListener(new ActionListener() {
			//buat view Profit
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		contentPanel.add(viewFinanceBtn);
		
		viewManageEmployeeBtn = new JButton("Manage Employee");
		viewManageEmployeeBtn.setBounds(160, 260, 250, 45);
		viewManageEmployeeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				EmployeeHandler eh = new EmployeeHandler();
				eh.viewManageEmployeeForm();
			}
		});
		contentPanel.add(viewManageEmployeeBtn);
		
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
