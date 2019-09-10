package com.leneannette.Entities;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "message")

public class Message implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String content;
	
	public Message() {}
	public Message(int id, String content) {
		this.id = id;
		this.content = content;
	}
	
	public int getId() {
		return id;
	}
	
	@XmlElement
	public void setId(int id) {
		this.id = id;
	}
	
	public String getContent() {
		return content;
	}
	
	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}
	
	

}
