package View.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.CartHandler;
import Controller.UserHandler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private JTextField emailTxt;
	private JPasswordField passwordTxt;
	public static String email1;

	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("LOGIN");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		titleLbl.setBounds(307, 13, 102, 44);
		contentPane.add(titleLbl);
		
		JLabel emailLbl = new JLabel("Email");
		emailLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		emailLbl.setBounds(223, 145, 90, 25);
		contentPane.add(emailLbl);
		
		JLabel passwordLbl = new JLabel("Password");
		passwordLbl.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		passwordLbl.setBounds(223, 218, 90, 25);
		contentPane.add(passwordLbl);
		
		emailTxt = new JTextField();
		emailTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		emailTxt.setBounds(359, 144, 200, 30);
		contentPane.add(emailTxt);
		emailTxt.setColumns(10);
		
		passwordTxt = new JPasswordField();
		passwordTxt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		passwordTxt.setBounds(359, 218, 200, 30);
		contentPane.add(passwordTxt);
		
		JButton login = new JButton("Login");
		login.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		login.setBounds(292, 318, 97, 25);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String email = emailTxt.getText();
				String password = passwordTxt.getText();
				email1 = email;
				Boolean validation = UserHandler.getInstance().validateAccount(email, password);
				
				if (validation == true) {
					CartHandler.getInstance().getUser(email);
					JOptionPane.showMessageDialog(login, "Login Success!");
					
					dispose();
					UserHomePageView hpv = new UserHomePageView();
					hpv.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(login, "Login Failed! Check your email or password.");
				}
			}
		});
		contentPane.add(login);
	}
}
