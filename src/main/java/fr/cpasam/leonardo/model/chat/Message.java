package fr.cpasam.leonardo.model.chat;

import fr.cpasam.leonardo.model.user.Member;

import java.util.Date;
import java.util.List;


public abstract class Message {

	private long id ;
	List<Member> transmitter;
	private java.util.Date date;
	private String content ;
	private Chat chat;

	public Message() {}
	
	public Message(long id, List<Member> transmitter, java.util.Date date,String content,Chat chat){
		this.id=id;
		this.transmitter=transmitter;
		this.date=date;
		this.content=content;
		this.chat=chat;
	}
	
	public long GetMessageId() {return id;}
	public List<Member> GetMessageTransmitter() {return transmitter;}
	public java.util.Date GetMessageDate() {return date;}
	public String GetMessageContent() {return content;}
	public Chat GetMessageChat() {return chat;}

	public void SetMessageId(long id) {this.id=id;}
	public void SetMessageTransmitter(List<Member> transmitter) {this.transmitter=transmitter;}
	public void SetMessageDate(Date date) {this.date=date;}
	public void SetMessageContent(String content) {this.content=content;}
	public void SetMessageChat(Chat chat) {this.chat=chat;}
	



}

	
/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
