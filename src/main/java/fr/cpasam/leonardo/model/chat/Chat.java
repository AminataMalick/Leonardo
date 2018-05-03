package fr.cpasam.leonardo.model.chat;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.user.Member;

	@NamedQueries({
		@NamedQuery(
		name = "findChatById",
		query = "from Chat c where c.CHAT_ID = :chatId"
		),
		@NamedQuery(
		name = "findAllChats",
		query = "from Chat"
		)
	})
	@Entity 
	@Table(name = "chats")
	public class Chat{
	
	@Id
	@GeneratedValue
	@Column (name ="CHAT_ID")
	private long id ;
	
	@Column (name = "USERS")
	List<Member> users;
	
	
	@Column (name = "MESSAGES")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "chat")
	List<Message> messages ;


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
