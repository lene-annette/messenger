# messenger
This project demonstrates a small Restful Web Service, where messages can be created, read, updated and deleted. 

In this repository you will find a messenger.war file with the newest build of the project. 
When creating this project an Apache Tomcat 7 server has been used. 
To run this project, run it locally on a server, e.g. by placing the war-file of the project inside the Tomcat-server's 
webapps folder and start the server. 

Then the webservice can be found on 
http://localhost:8080/messenger/rest/messageservice/messages

Endpoints: 
This project has the following endpoints 
- GET: /messageservice/messages, returns a list of all messages in the system 
- GET: /messageservice/messages/:messageid, returns the message with the given id, if exists
- POST: /messageservice/messages, creates new message, consumes a message as XML and returns it if created successfully
- PUT: /messageservice/messages, updates a message, consumes a message as XML and returns it if created successfully
- DELETE: /messageservice/messages/:messageid, deletes the message with the given id if it exists 

First time running the project creates a .dat file in which it stores data. 

Note that this project does not yet have incremention of id (so if you try to create a message with id 1, and this already exists 
an error will occur), or any sort of user-system or user check, so everyone can update and delete all messages. 

