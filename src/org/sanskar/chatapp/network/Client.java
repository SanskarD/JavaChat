package org.sanskar.chatapp.network;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JTextArea;

import org.sanskar.chatapp.utils.ConfigReader;

public class Client {
	Socket socket;
	OutputStream outputStream;
	InputStream inputStream;
	ClientWorker worker;
	JTextArea textArea;
	
	public Client(JTextArea textArea) throws UnknownHostException, IOException {
		final int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		socket = new Socket(ConfigReader.getValue("SERVER_IP"),PORT);
		outputStream = socket.getOutputStream();
		inputStream = socket.getInputStream();
		this.textArea = textArea;
		readMessages();
//		System.out.println("Client Joined");
//		InputStream inputStream = socket.getInputStream();
//		byte arr[] = inputStream.readAllBytes();
//		String string = new String(arr);
//		System.out.println("Message "+string);
//		inputStream.close();
//		socket.close();
	}
	
	public void sendMessage(String message) throws IOException {
		message += "\n";
		outputStream.write(message.getBytes());
	}
	
	public void readMessages() throws IOException {
		worker = new ClientWorker(inputStream, textArea);
		worker.start();
	}	
	
}
