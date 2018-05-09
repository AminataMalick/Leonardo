package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;


public abstract class Chat<T,U>{

	protected int cnt;
	
	protected long id ;
	protected T entity1;
	protected U entity2;
	private List<Message> messages ;

	
	public Chat(T entity1, U entity2) {
		this.cnt = 0;
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.messages = new ArrayList<Message>();
	}
	
	protected void addMessages(Message m) {
		
		this.messages.add(m);
		this.cnt++;

	}
	
	protected int getNbMessage() {
		return this.cnt;
	}

	public U getEntity2() {
		
		return entity2;
	}
	
	public T getEntity1() {
		
		return entity1;
	}
	

}



