package org.example.socketServer;

import com.example.helloworld.socket.ClientInfo;
import com.example.helloworld.socket.MessageInfo;
import org.apache.el.lang.ELArithmetic;
import org.example.entity.Message;
import org.example.service.UserAccountService;
import org.example.service.impl.UserServicelmpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SocketManager {
	private static final SocketManager socketManager = new SocketManager();
	private SocketManager() {}
	public static SocketManager getSocketManager() {
		return socketManager;
	}
	
	Vector<ChatSocket> chatSockets = new Vector<ChatSocket>();
	Map<String,ChatSocket> online = new HashMap<>();

	public void addOnline(String key, ChatSocket value) {
		online.put(key, value);
	}

	public boolean getOnline(String key){
		ChatSocket chatSocket;
		for (String Key:online.keySet())
			System.out.println("Key"+Key+"Value"+online.get(Key));
		if((chatSocket = online.get(key)) == null) {
			System.out.println("空");
			return false;
		}
		else {
			System.out.println("不为空");
			return true;
		}
	}

	public boolean putoutOnline(String key){
		online.remove(key);
		if (online.get(key) != null)
			return false;
		else
			return true;
	}

	String userId;
	
	public void addSocket(ChatSocket chatSocket) {
		chatSockets.add(chatSocket);
	}

	public void deleteSocket(ChatSocket chatSocket) {
	}

	public void outputMessage(String userId,int j,Message message){
		ChatSocket chatSocket = null;
		for (int i = 0;i < chatSockets.size();i++){
			chatSocket = online.get(userId);
			if (chatSockets.get(i).equals(chatSocket)){
				chatSocket = chatSockets.get(i);
				break;
			}
		}
		if(j != 0 && j != 1) {
			MessageInfo messageInfo = message.transMessageInfo(message);
			ClientInfo clientInfo = new ClientInfo();
			clientInfo.setUserId(j);
			clientInfo.setMessageInfo(messageInfo);
			chatSocket.outputObject(clientInfo);
		}
		else {
			ClientInfo clientInfo = new ClientInfo();
			clientInfo.setUserId(j);
			chatSocket.outputObject(clientInfo);
		}
	}

	public void outputMessageGroup(List<Integer> menbers, int j, Message message) {
		ChatSocket chatSocket = null;
		MessageInfo messageInfo = message.transMessageInfo(message);
		ClientInfo clientInfo = new ClientInfo();
		clientInfo.setId(message.getReceiveId());
		clientInfo.setUserId(j);
		clientInfo.setMessageInfo(messageInfo);
			for (int i = 0; i < menbers.size(); i++) {
				if (getOnline(Integer.toString(menbers.get(i)))) {
					chatSocket = online.get(Integer.toString(menbers.get(i)));
					chatSocket.outputObject(clientInfo);
				}
			}
	}
}
