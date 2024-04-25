package org.sanskar.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.sanskar.chatapp.dao.UserDao;
import org.sanskar.chatapp.dto.UserDTO;
import org.sanskar.chatapp.utils.UserInfo;

public class USerScreen extends JFrame {
	private JTextField userIdTxt;
	private JPasswordField passwordField;


	public static void main(String[] args) {
					USerScreen window = new USerScreen();
	}
	
	UserDao userDao = new UserDao();
	private void loginUser() throws UnknownHostException, IOException {
		String userid = userIdTxt.getText();
		char[] password = passwordField.getPassword();
		try {
			boolean res = userDao.isLogin(new UserDTO(userid,password));	
			String message ="";
			if(res) {
				UserInfo.USER_NAME = userid;
				message=  "Let's Chat";
				setVisible(false);
				dispose();
				JOptionPane.showMessageDialog(this, "Login Successful");
				new ChatScreen();
			}
			else {
				message="Incorrect Username or Password";
				JOptionPane.showMessageDialog(this, message);
			}
		}catch(ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
			System.out.println("DB Issue..");
			ex.printStackTrace();
		}
		
	}
	private void register() {
		String userid = userIdTxt.getText();
		char[] password = passwordField.getPassword();
		try {
			int res = userDao.add(new UserDTO(userid,password));	
			if(res > 0) {
				JOptionPane.showMessageDialog(this, "Registered Succesfully");
			}
			else {
				JOptionPane.showMessageDialog(this, "Registered Succesfully");
			}
		}catch(ClassNotFoundException | SQLException | NoSuchAlgorithmException ex) {
			System.out.println("DB Issue..");
			ex.printStackTrace();
		}
	}

	/**
	 * Create the application.
	 */
	public USerScreen() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 10));
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(new Color(255, 255, 255));
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Let's Chat");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 40));
		lblNewLabel.setBounds(130, 8, 200, 47);
		getContentPane().add(lblNewLabel);
		
		userIdTxt = new JTextField();
		userIdTxt.setBounds(156, 77, 139, 28);
		getContentPane().add(userIdTxt);
		userIdTxt.setColumns(10);
		
		JLabel userIdLabel = new JLabel("User Id");
		userIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		userIdLabel.setBounds(79, 82, 59, 13);
		getContentPane().add(userIdLabel);
		
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordLabel.setBounds(79, 130, 59, 13);
		getContentPane().add(passwordLabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(156, 125, 139, 28);
		getContentPane().add(passwordField);
		
		JButton LoginBt = new JButton("Login\r\n");
		LoginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					loginUser();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		LoginBt.setBounds(118, 188, 97, 20);
		getContentPane().add(LoginBt);
		
		JButton registerButton = new JButton("Register");
		registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					register();
			}
		});
		registerButton.setBounds(223, 187, 97, 20);
		getContentPane().add(registerButton);
		setSize(450, 307);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
	}
}
