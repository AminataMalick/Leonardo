package fr.cpasam.leonardo.model.chat;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.cpasam.leonardo.model.user.Member;

import java.util.List;

import javax.persistence.Column;

	@Entity (name = "chats")
	public class Chat {
	
	@Id
	@GeneratedValue
	@Column (name ="CHAT_ID")
	private long id ;
	
	@Column (name = "USERS")
	List<Member> users;
	
	
	@Column (name = "MESSAGES")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "chat")
	List<Message> messages ;
	
//	@Column (name = "STATUT")
//	Statut statut ;
	
}

/*
	public boolean sendMessage(Message m) {return true;}
	public boolean sendNotification() {return true;}	
*/
