package View.User;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Controller.UserHandler;
import Model.User;

import javax.swing.JButton;

public class UserInformationView extends JFrame {

	private JPanel contentPane;
	private JLabel nameValue, addressValue, phoneValue;
	private int userId;
	
	public UserInformationView(int userId) {
		this.userId = userId;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titleLbl = new JLabel("User Information");
		titleLbl.setFont(new Font("Times New Roman", Font.PLAIN, 24));
		titleLbl.setHorizontalAlignment(SwingConstants.CENTER);
		titleLbl.setBounds(232, 13, 217, 43);
		contentPane.add(titleLbl);
		
		JLabel nameLbl = new JLabel("Name");
		nameLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameLbl.setBounds(63, 118, 95, 18);
		contentPane.add(nameLbl);
		
		nameValue = new JLabel("-");
		nameValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		nameValue.setBounds(199, 118, 250, 18);
		contentPane.add(nameValue);
		
		JLabel addressLbl = new JLabel("Address");
		addressLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressLbl.setBounds(63, 178, 95, 18);
		contentPane.add(addressLbl);
		
		addressValue = new JLabel("-");
		addressValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		addressValue.setBounds(199, 178, 250, 18);
		contentPane.add(addressValue);
		
		JLabel phoneLbl = new JLabel("Phone Number");
		phoneLbl.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		phoneLbl.setBounds(63, 234, 95, 18);
		contentPane.add(phoneLbl);
		
		phoneValue = new JLabel("-");
		phoneValue.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		phoneValue.setBounds(199, 234, 250, 18);
		contentPane.add(phoneValue);
		
		JButton proceedBtn = new JButton("Proceed");
		proceedBtn.setBounds(292, 305, 97, 25);
		contentPane.add(proceedBtn);
		
		addData();
	}
	
	private void addData() {
		User u = UserHandler.getInstance().getUserbyId(userId);
		nameValue.setText(u.getName());;
		addressValue.setText(u.getAddress());;
		phoneValue.setText(u.getPhoneNumber());;
	}
}
