package fr.cpasam.leonardo.model.chat;

import javax.persistence.Entity;
import javax.persistence.Id;

import fr.cpasam.leonardo.model.member.Member;

import java.util.List;

import javax.persistence.Column;



	@Entity (name = "chats")
	public class Chat {
	
	@Id
	@Column (name ="ID")
	long id ;
	
	@Column (name = "USERS")
	List<Member> users;
	
	
	@Column (name = "MESSAGES")
	List<Message> messages ;
	
	/*@Column (name = "STATUT")
	Statut statut ;
	*/
	}

/*
	public boolean sendMessage(Message m) {return true;}
	public boolean sendNotification() {return true;}
	*/

