package org.sanskar.chatapp.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import org.sanskar.chatapp.utils.ConfigReader;

public class Server {
	ServerSocket serverSocket;
	ArrayList<ServerWorker> workers = new ArrayList<ServerWorker>();
	
	public Server() throws IOException {
		final int PORT = Integer.parseInt(ConfigReader.getValue("PORTNO"));
		serverSocket = new ServerSocket(PORT);
		System.out.println("Server Started");
		handleClientRequest();
	}
	
	public void handleClientRequest() throws IOException {
		while(true){
			Socket clientSocket = serverSocket.accept();
			//Per client per thread
			ServerWorker serverWorker = new ServerWorker(clientSocket,this); //new thread
			workers.add(serverWorker);
			serverWorker.start();
		}
	}
	public static void main(String[] args) throws IOException {
		Server server = new Server();
	}
}
