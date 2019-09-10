package com.leneannette.Data;

import java.util.List;

import com.leneannette.Entities.Message;

public class MessageDao { 	
	private DataHandler dh;
	
	public MessageDao() {
		this.dh = new DataHandler("Messages.dat");
	}
	
	public void setDataHandler(DataHandler dh) {
		this.dh = dh;
	}
	
	public List<Message> getAllMessages(){
		return this.dh.getMessages();
	}
	
	public Message getMessage(int id) {
		return this.dh.getMessage(id);
	}
	
	public int addMessage(Message pMessage) {
		Message message = this.dh.getMessage(pMessage.getId());
		if(message == null) {
			this.dh.saveMessage(pMessage);
			return 1;
		}
		return 0;
	}
	
	public int updateMessage(Message pMessage) {
		Message message = this.dh.getMessage(pMessage.getId());
		if(message != null) {
			this.dh.saveMessage(pMessage);
			return 1;
		}
		return 0;
	}
	
	public int deleteMessage(int id) {
		List<Message> messageList = this.dh.getMessages();
		for(Message m: messageList) {
			if(m.getId() == id) {
				messageList.remove(m);
				this.dh.saveMessageList(messageList);
				return 1;
			}
		}
		return 0;
	}
}
