package org.sanskar.chatapp.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.sanskar.chatapp.network.Client;
import org.sanskar.chatapp.utils.UserInfo;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textArea = new JTextArea();;
	private Client client;

	public static void main(String[] args) {
		try {
			ChatScreen frame = new ChatScreen();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void sendIt() {
		String message = textField.getText();
		textField.setText("");
		try {
			client.sendMessage(UserInfo.USER_NAME+": "+message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Create the frame.
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public ChatScreen() throws UnknownHostException, IOException {
		client = new Client(textArea);
		setTitle("Let's Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 844, 443);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 817, 317);
		contentPane.add(scrollPane);
		
		textArea.setFont(new Font("Nirmala UI Semilight", Font.PLAIN, 16));
		textArea.setBounds(10, 10, 817, 317);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 16));
		textField.setBounds(10, 354, 682, 41);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton sendMessage = new JButton("Send Message");
		sendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendIt();
			}
		});
		sendMessage.setFont(new Font("Tempus Sans ITC", Font.PLAIN, 13));
		sendMessage.setBounds(702, 355, 121, 41);
		contentPane.add(sendMessage);
		
		setVisible(true);
	}
}
