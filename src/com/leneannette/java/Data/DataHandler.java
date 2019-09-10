package com.leneannette.java.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.leneannette.java.Entities.Message;

public class DataHandler {
	private File messageFile;
	private String fileName;

	public DataHandler(String fileName) {
		this.fileName = fileName;
		messageFile = new File(this.fileName);
	}

	public List<Message> getMessages() {
		List<Message> messageList = null;
		try {
			if (!this.messageFile.exists()) {
				Message message = new Message(1, "First message");
				messageList = new ArrayList<Message>();
				messageList.add(message);
				saveMessageList(messageList);
			} else {
				FileInputStream fis = new FileInputStream(this.messageFile);
				ObjectInputStream ois = new ObjectInputStream(fis);
				messageList = (List<Message>) ois.readObject();
				ois.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return messageList;
	}

	public Message getMessage(int id) {
		List<Message> messages = getMessages();
		for (Message m : messages) {
			if (m.getId() == id) {
				return m;
			}
		}
		return null;
	}

	public void saveMessage(Message message) {
		List<Message> messageList = getMessages();
		boolean messageExists = false;
		for(Message m: messageList) {
			if(m.getId() == message.getId()) {
				int index = messageList.indexOf(m);
				messageList.set(index, message);
				messageExists = true;
				break;
			}
		}
		if(!messageExists) {
			messageList.add(message);
		}
		saveMessageList(messageList);		
	}

	public void saveMessageList(List<Message> messageList) {
		try {
			File file = new File(this.fileName);
			FileOutputStream fos;
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(messageList);
			oos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
