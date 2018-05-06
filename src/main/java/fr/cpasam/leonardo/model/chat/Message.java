package fr.cpasam.leonardo.model.chat;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.cpasam.leonardo.model.user.Member;

import java.util.List;

import javax.persistence.Column;


@Entity 
@Table (name = "message")
public abstract class Message {

	@Id
	@Column (name ="MESSAGE_ID")
	private long id ;
	
	@Column (name = "TRANSMITTER")
	List<Member> transmitter;
	
	@Column (name = "DATE")
	@Temporal(TemporalType.DATE)
	private java.util.Date date;
	
	@Column (name = "CONTENT")
	private String content ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="CHAT_ID")
	private Chat chat;

	public Message() {}
	
}

/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
