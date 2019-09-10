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
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
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
	public Response getMessage(@PathParam("messageid") int messageid) {
		Message message = mDao.getMessage(messageid);
		if(message != null) {
			return Response.status(Status.OK)
				.entity(message)
				.build();
		}
		return Response.status(Status.NOT_FOUND)
			.build();
	}
	
	@POST
	@Path("/messages")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response createMessage(Message message) {
		int result = mDao.addMessage(message);
		if(result == 1) {
			return Response.status(Status.CREATED)
					.entity(message)
					.build();
			
		}
		return Response.status(Status.BAD_REQUEST)
				.build();
	}
	
	@PUT
	@Path("/messages")
	@Produces(MediaType.APPLICATION_XML)
	@Consumes(MediaType.APPLICATION_XML)
	public Response updateMessage(Message message) {
		int result = mDao.updateMessage(message);
		if(result == 1) {
			return Response.status(Status.OK)
					.entity(message)
					.build();
		}
		return Response.status(Status.NOT_MODIFIED)
				.build();
	}
	
	@DELETE
	@Path("/messages/{messageid}")
	@Produces(MediaType.APPLICATION_XML)
	public Response deleteUser(@PathParam("messageid") int messageid) {
		int result = mDao.deleteMessage(messageid);
		if(result == 1) {
			return Response.status(204)
					.build();
		}
		return Response.status(404)
				.build();
	}
	
	
}
