package fr.cpasam.leonardo.model.chat;

import java.util.List;

import fr.cpasam.leonardo.model.user.Member;


public class Chat{

	private long id ;

	List<Member> users;


	List<Message> messages ;

	public Chat() {}

	public Chat(List<Member> users, List<Message> messages) {
		this.users = users;
		this.messages = messages;
	}




	//	@Column (name = "STATUT")
	//	Statut statut ;

}

/*
	public boolean sendMessage(Message m) {return true;}
	public boolean sendNotification() {return true;}	
 */
