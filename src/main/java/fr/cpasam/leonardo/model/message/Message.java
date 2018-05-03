package fr.cpasam.leonardo.model.message;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.cpasam.leonardo.model.member.Member;

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

}

/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
