package View.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.UserHandler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class RegistrationView extends JFrame {

	private JPanel contentPane;
	private JTextField nameTxt;
	private JTextField addressTxt;
	private JTextField emailTxt;
	private JTextField phoneNumberTxt;
	private JPasswordField passwordTxt;


	public RegistrationView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("REGISTRATION");
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(238, 13, 206, 67);
		contentPane.add(titleLbl);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		nameLbl.setBounds(183, 93, 111, 50);
		contentPane.add(nameLbl);
		
		JLabel addressLbl = new JLabel("Address");
		addressLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		addressLbl.setBounds(183, 156, 111, 50);
		contentPane.add(addressLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		emailLbl.setBounds(183, 219, 111, 50);
		contentPane.add(emailLbl);
		
		JLabel phoneNumberLbl = new JLabel("Phone Number");
		phoneNumberLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		phoneNumberLbl.setBounds(183, 282, 111, 50);
		contentPane.add(phoneNumberLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordLbl.setBounds(183, 345, 111, 50);
		contentPane.add(passwordLbl);
		
		nameTxt = new JTextField();
		nameTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameTxt.setBounds(328, 105, 200, 30);
		contentPane.add(nameTxt);
		nameTxt.setColumns(10);
		
		addressTxt = new JTextField();
		addressTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressTxt.setBounds(328, 168, 200, 30);
		contentPane.add(addressTxt);
		addressTxt.setColumns(10);
		
		emailTxt = new JTextField();
		emailTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		emailTxt.setBounds(328, 231, 200, 30);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);
		
		phoneNumberTxt = new JTextField();
		phoneNumberTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		phoneNumberTxt.setBounds(328, 294, 200, 30);
		contentPane.add(phoneNumberTxt);
		phoneNumberTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordTxt.setBounds(328, 357, 200, 30);
		contentPane.add(passwordTxt);
		
		JButton register = new JButton("Register");
		register.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		register.setBounds(291, 415, 100, 25);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = nameTxt.getText();
				String address = addressTxt.getText();
				String email = emailTxt.getText();
				String phoneNumber = phoneNumberTxt.getText();
				String password = passwordTxt.getText();
				
				String validation = UserHandler.getInstance().validateFields(name, address, email, phoneNumber, password) ;
				
				if (validation.equals("Data is valid")) {
					UserHandler.getInstance().createAccount(name, address, email, phoneNumber, password);
					
					String message = "User Registration Successful! Please Login.";
					JOptionPane.showMessageDialog(register, message);
					
					dispose();
					UserHandler.viewLoginForm().setVisible(true);
				} else {
					JOptionPane.showMessageDialog(register, validation);
				}
			}
		});
		contentPane.add(register);
	}

}