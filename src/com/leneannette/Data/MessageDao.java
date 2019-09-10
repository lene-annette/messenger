package com.leneannette.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.leneannette.Entities.Message;

public class MessageDao {
	public List<Message> getAllMessages(){
		List<Message> messageList = null;
		
		try {
			File file = new File("Messages.dat");
			if(!file.exists()) {
				Message message = new Message(1,"First message");
				messageList = new ArrayList<Message>();
				messageList.add(message);
				saveMessageList(messageList);
			}else {
				FileInputStream fis = new FileInputStream(file);
				ObjectInputStream ois = new ObjectInputStream(fis);
				messageList = (List<Message>) ois.readObject();
				ois.close();
			}
		} catch(IOException e) {
			e.printStackTrace();
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	public Message getMessage(int id) {
		List<Message> messages = getAllMessages();
		
		for(Message m: messages) {
			if(m.getId() == id) {
				return m;
			}
		}
		return null;
	}
	
	public int addMessage(Message pMessage) {
		List<Message> messages = getAllMessages();
		boolean messageExists = false;
		for(Message m: messages) {
			if(m.getId() == pMessage.getId()) {
				messageExists = true;
				break;
			}
		}
		if(!messageExists) {
			messages.add(pMessage);
			saveMessageList(messages);
			return 1;
		}
		return 0;
	}
	
	public int updateMessage(Message pMessage) {
		List<Message> messages = getAllMessages();
		
		for(Message m: messages) {
			if(m.getId() == pMessage.getId()) {
				int index = messages.indexOf(m);
				messages.set(index, pMessage);
				saveMessageList(messages);
				return 1;
			}
		}
		return 0;
	}
	
	public int deleteMessage(int id) {
		List<Message> messages = getAllMessages();
		
		for(Message m: messages) {
			if(m.getId() == id) {
				int index = messages.indexOf(m);
				messages.remove(index);
				saveMessageList(messages);
				return 1;
			}
		}
		return 0;
	}
	
	private void saveMessageList(List<Message> messageList) {
		try {
			File file = new File("Messages.dat");
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(messageList);
			oos.close();
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
