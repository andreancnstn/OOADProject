package View.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controller.UserHandler;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.JButton;

public class UserLandingPage extends JFrame {

	private JPanel contentPane;

	public UserLandingPage() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("JrebFood");
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		titleLbl.setBounds(260, 46, 161, 79);
		contentPane.add(titleLbl);
		
		JButton register = new JButton("REGISTER");
		register.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		register.setBounds(197, 190, 125, 45);
		register.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserHandler.viewRegistrationForm().setVisible(true);
			}
		});
		contentPane.add(register);
		
		JButton login = new JButton("LOGIN");
		login.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		login.setBounds(347, 190, 125, 45);
		login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserHandler.viewLoginForm().setVisible(true);
			}
		});
		contentPane.add(login);
	}
}