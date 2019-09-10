package com.leneannette;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path; 
import javax.ws.rs.Produces; 
import javax.ws.rs.core.MediaType;
import javax.ws.rs.PathParam;

import com.leneannette.Data.MessageDao;
import com.leneannette.Entities.Message;

@Path("/messageservice")

public class MessageService {
	MessageDao mDao = new MessageDao();
	
	@GET
	@Path("/messages")
	@Produces(MediaType.APPLICATION_XML)
	public List<Message> getMessages(){
		return mDao.getAllMessages();
	}
	
	@GET
	@Path("/messages/{messageid}")
	@Produces(MediaType.APPLICATION_XML)
	public Message getMessage(@PathParam("messageid") int messageid) {
		return mDao.getMessage(messageid);
	}
	
	@POST
	@Path("/messages")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Message createMessage(Message message) {
		int result = mDao.addMessage(message);
		if(result == 1) {
			return message;
		}
		return null;
	}
	
	@PUT
	@Path("/messages")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Message updateMessage(Message message) {
		int result = mDao.updateMessage(message);
		if(result == 1) {
			return message;
		}
		return null;
	}
	
	@DELETE
	@Path("/messages/{messageid}")
	@Produces(MediaType.APPLICATION_XML)
	public String deleteUser(@PathParam("messageid") int messageid) {
		int result = mDao.deleteMessage(messageid);
		if(result == 1) {
			return "<result>success</result>";
		}
		return "<result>failure</result>";
	}
	
	
}
