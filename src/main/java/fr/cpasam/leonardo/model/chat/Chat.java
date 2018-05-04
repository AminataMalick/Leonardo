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
		name = Chat.FIND_CHAT_BY_ID,
		query = "from Chat c where c.id = :chatId"
		),
		@NamedQuery(
		name = Chat.FIND_ALL_CHATS,
		query = "from Chat"
		)
	})
	@Entity 
	@Table(name = "chats")
	public class Chat{
	
	public static final String FIND_CHAT_BY_ID = "findChatById";
	public static final String FIND_ALL_CHATS = "findAllChats";
		
	@Id
	@GeneratedValue
	@Column (name ="CHAT_ID")
	private long id ;
	
	@Column (name = "USERS")
	List<Member> users;
	
	
	@Column (name = "MESSAGES")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "chat")
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
