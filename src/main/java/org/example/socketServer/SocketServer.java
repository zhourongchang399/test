package org.example.socketServer;

import java.io.IOException;
import java.net.*;

public class SocketServer extends Thread{
	ServerSocket serverSocket;
	
	@Override
	public void run() {
		HttpURLConnection connection;
		URL url;
		try {
			url = new URL("http://localhost:8088/userAccount/deleteOnlineListForAllServer");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setConnectTimeout(100);
			connection.setReadTimeout(60000);
			connection.connect();
			int code = connection.getResponseCode();
			System.out.println(code);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		try {
			serverSocket = new ServerSocket(12346);
			System.out.println("Socket服务器开启！");
			while (true) {
				Socket socket = serverSocket.accept();
				System.out.println("有用户连接到该服务器！");
				ChatSocket chatSocket = new ChatSocket(socket);
				chatSocket.start();
				SocketManager.getSocketManager().addSocket(chatSocket);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

}
