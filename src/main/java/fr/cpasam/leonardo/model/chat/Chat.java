package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;


public abstract class Chat<T,U>{

	
	protected long id ;

	protected T entity1;
	
	protected U entity2;


	private List<Message> messages ;

	public Chat(T entity1, U entity2) {
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.messages = new ArrayList<Message>();
	}

	public boolean sendMessage(Message m) {return true;}
	public boolean sendNotification() {return true;}

	
	//TODO BDD Resquest
	public static Chat get(long id2, long id3) {
		// récupérer dans la BDD le chat avec les id2 et id3 correspondant
		
		return null;
	}

	
	
	

}



