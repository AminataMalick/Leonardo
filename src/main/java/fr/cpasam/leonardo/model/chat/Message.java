package fr.cpasam.leonardo.model.chat;

import fr.cpasam.leonardo.model.user.Member;

import java.util.List;

import javax.persistence.Column;


public abstract class Message<T> {

	private long id ;
	
	private T emiter;
	
	private java.util.Date date;
	
	private String content ;
	
	private Chat chat;

	public Message() {}
	
}

/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
