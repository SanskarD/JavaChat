package org.sanskar.chatapp.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerWorker extends Thread {
	
	private Socket clientSocket;
	private InputStream inputStream;
	private OutputStream outputStream;
	private Server server;
	public ServerWorker(Socket clientSocket,Server server) throws IOException {
		this.clientSocket = clientSocket;
		this.server = server;
		inputStream = clientSocket.getInputStream();
		outputStream = clientSocket.getOutputStream();
		System.out.println("New Client");
	}
	
	@Override
	public void run() {
		// Read data and broadcast
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
		while(true) {
				line =br.readLine();
				if(line.equalsIgnoreCase("quit")) {
					break;
					//client chat end
				}
				for(ServerWorker worker: server.workers) {
					line += "\n";
					System.out.println(line);
					worker.outputStream.write(line.getBytes());
				}
			}
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
		finally {
			try {
				if(br!=null) br.close();
				if(inputStream != null) inputStream.close();
				if(outputStream != null) outputStream.close();
				if(clientSocket != null) clientSocket.close();				
			}
			catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		
		
		
	}
	
}
