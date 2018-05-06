package fr.cpasam.leonardo.model.chat;

import fr.cpasam.leonardo.model.user.Member;

import java.util.List;

import javax.persistence.Column;


public abstract class Message {

	private long id ;
	
	List<Member> transmitter;
	
	private java.util.Date date;
	
	private String content ;
	
	private Chat chat;

	public Message() {}
	
}

/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
