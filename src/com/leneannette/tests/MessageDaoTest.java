package com.leneannette.tests;

import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.leneannette.java.Data.DataHandler;
import com.leneannette.java.Data.MessageDao;
import com.leneannette.java.Entities.Message;

public class MessageDaoTest {
	private DataHandler dh;

	@Before
	public void setUp() throws Exception {
		this.dh = new DataHandler("TestMessages.dat");
	}

	@After
	public void tearDown() throws Exception {
		File file = new File("TestMessages.dat");
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test
	public void getMessageListTest() {
		MessageDao dao = new MessageDao();
		dao.setDataHandler(dh);
		
		List<Message> messageList = dao.getAllMessages();
		assertTrue(messageList.size() >= 1);
	}

	@Test
	public void getMessageTest() {
		MessageDao dao = new MessageDao();
		dao.setDataHandler(dh);
		Message result = dao.getMessage(1);
		
		assertTrue(result != null);
	}
	
	@Test
	public void createMessageTest() {
		MessageDao dao = new MessageDao();
		dao.setDataHandler(dh);
		Message message = new Message(2, "testMessage");
		
		dao.addMessage(message);
		
		assertTrue(dao.getAllMessages().size() == 2);	
	}
	
	@Test
	public void updateMessageTest() {
		MessageDao dao = new MessageDao();
		dao.setDataHandler(dh);
		String newContent = "updated message";
		Message message = new Message(1,newContent);
		
		dao.updateMessage(message);
		
		assertEquals(newContent,dao.getMessage(1).getContent());
	}
	
	@Test
	public void deleteMessageTest() {
		MessageDao dao = new MessageDao();
		dao.setDataHandler(dh);
		
		Message message = new Message(3,"message to delete");
		dao.addMessage(message);
		
		dao.deleteMessage(3);
		
		assertTrue(dao.getMessage(3) == null);
	}

}
