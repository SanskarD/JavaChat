package org.sanskar.chatapp.network;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JTextArea;

public class ClientWorker extends Thread {
	private InputStream inputStream;
	private JTextArea textArea;
	public ClientWorker(InputStream in,JTextArea textArea) {
		inputStream = in;
		this.textArea = textArea;
	}
	@Override
	public void run() {
		BufferedReader br  = new BufferedReader(new InputStreamReader(inputStream));
		String line;
		try {
			while(true) {
				line = br.readLine();
				textArea.setText(textArea.getText()+line+"\n");
			}			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try{
				if(inputStream != null) inputStream.close();
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}
}
