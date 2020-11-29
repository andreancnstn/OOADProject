package Main;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.EmployeeHandler;
import Controller.FoodHandler;
import View.EmployeeView;
import View.FoodView;

public class MainTest extends JFrame implements ActionListener{
	
	JButton employeeBtn, foodBtn;
	JPanel menuPanel, btnPanel;
	
	FoodHandler fh = new FoodHandler();
	EmployeeHandler eh = new EmployeeHandler();

	public MainTest() {
//		DatabaseConnection connection = new DatabaseConnection(); //db connection oke
		
		menuPanel = new JPanel();
		menuPanel.setLayout(new BorderLayout(0,0));
		setContentPane(menuPanel);
		
		btnPanel = new JPanel(new GridLayout(1,2));
		menuPanel.add(btnPanel, BorderLayout.CENTER);
		
		employeeBtn = new JButton("Employee");
		employeeBtn.addActionListener(this);
		foodBtn = new JButton("Food");
		foodBtn.addActionListener(this);
		
		
		btnPanel.add(employeeBtn);
		btnPanel.add(foodBtn);
		
		init();
		
		
	}

	public static void main(String[] args) {
		new MainTest();

	}
	
	private void init() {
		setTitle("Manage Employee Form");
		setVisible(true);
		setSize(600,600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == employeeBtn) {
			eh.viewManageEmployeeForm();
			this.dispose();
		}
		else if (e.getSource() == foodBtn) {
			fh.viewManageFoodForm();
			this.dispose();
		}
	}

}
