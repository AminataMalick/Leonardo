package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;


public abstract class Chat<T,U>{

	private static long cnt = 20;
	/**
	 * Retourne le prochain id disponible
	 * @return long
	 */
	public static long getCnt() {
		return cnt++;
	}
	protected int cntMessage;
	
	protected long id ;
	protected T entity1;
	protected U entity2;
	private List<Message> messages ;
	
	public Chat() {
		// TODO Auto-generated constructor stub
	}

	// Bloc static 
	  
	  static {
	  	
	  	cnt = ShopChatDAO.getLastId()+1;
	  }
	  
	  
	 
	/**
	 * Creation of a new chat
	 * @param id id in the database
	 * @param entity1 one end of the channel
	 * @param entity2 the other end of the channel
	 */
	public Chat(long id,T entity1, U entity2) {
		this.cntMessage = 0;
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.messages = new ArrayList<Message>();
		this.id = id;
	}
	
	
	/**
	 * Instantiation of an existing chat
	 * @param id id in database
	 * @param entity1 one end of the channel
	 * @param entity2 the other end of the channel
	 * @param messages loading of the messages link to the chat in the database
	 */
	public Chat(long id, T entity1, U entity2, List<Message> messages) {
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.id=id;
		this.messages = messages;
		this.cntMessage = messages.size();
	}


	/**
	 * Ajoute un message au chat
	 * @param Message
	 */
	protected void addMessage(Message m) {
		
		this.messages.add(m);
		this.cntMessage++;

	}
	
	/**
	 * Retourne le nombre de message d'un chat
	 * @return int
	 */
	protected int getNbMessage() {
		return this.cntMessage;
	}

	public U getEntity2() {
		
		return entity2;
	}
	
	public T getEntity1() {
		
		return entity1;
	}
	
	public long id() {
		return this.id;
	}
	
	

}



