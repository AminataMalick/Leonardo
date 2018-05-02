package fr.cpasam.leonardo.model.chat;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.util.List;

import javax.persistence.Column;



	@Entity (name = "chat")
	public class Chat {
	
	@Id
	@Column (name ="ID")
	long id ;
	
	@Column (name = "USERS")
	List<Member> users;
	
	
	@Column (name = "MESSAGES")
	List<Message> messages ;
	
	@Column (name = "STATUT")
	Statut statut ;
	
	}


	public boolean sendMessage(Message m) {return true;}
	public boolean sendNotification() {return true;}
	

