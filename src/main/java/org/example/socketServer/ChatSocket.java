package org.example.socketServer;

import com.example.helloworld.socket.ClientInfo;
import org.example.mapper.MessageManagerMapper;
import org.example.service.MessageManagerService;
import org.example.service.UserAccountService;
import org.example.service.impl.UserServicelmpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.sql.Connection;

public class ChatSocket extends Thread{
	UserAccountService userAccountService = new UserServicelmpl();
	ClientInfo clientInfo;
	public Socket chaSocket;
	ObjectOutputStream objectOutputStream;
	ObjectInputStream objectInputStream;

	public ChatSocket(Socket socket) {
		this.chaSocket = socket;
		try {
			objectOutputStream = new ObjectOutputStream(chaSocket.getOutputStream());
			objectInputStream = new ObjectInputStream(chaSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
				while ((clientInfo = (ClientInfo) objectInputStream.readObject()) != null) {
					if (clientInfo.getId() == 0){
						SocketManager.getSocketManager().addOnline(Integer.toString(clientInfo.getUserId()),this);
						System.out.println(clientInfo.getUserId()+"注册chatSocket成功");
					}
				}
			objectInputStream.close();
			
		}catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} 
		catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				chaSocket.close();
			} catch (IOException ioException) {
				ioException.printStackTrace();
			}
			if (SocketManager.getSocketManager().putoutOnline(Integer.toString(clientInfo.getUserId()))) {
				System.out.println(clientInfo.getUserId()+"注销成功！");
				new myThread(clientInfo.getUserId()).start();
			}
			else
				System.out.println("注销失败！");
		}
	}

	public void outputObject(ClientInfo clientInfo) {
		try {
			objectOutputStream.writeObject(clientInfo);
			objectOutputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public class myThread extends Thread{
		HttpURLConnection connection;
		int i;
		public myThread(int i){
			this.i = i;
		}
		@Override
		public void run() {
			URL url = null;
			try {
				url = new URL("http://localhost:8088/userAccount/deleteOnlineListByServer?userid="+i);
				connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
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
		}
	}
}
