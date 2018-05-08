package fr.cpasam.leonardo.model.chat;

import java.util.Date;


public abstract class Message<T> {

	
	protected long id ;
	protected T emiter;
	protected java.util.Date date;
	protected Chat chat;
	
	
	public Message(Chat chat, T emiter){
		
		this.id = chat.getNbMessage()+1;
		this.emiter=emiter;
		this.date=new Date();
		this.chat=chat;

	}
	
	
	
	/**
	 * Retourne l'id d'un message
	 * @return id
	 */
	public long getId() {return this.id;}

	/**
	 * Retourne l'emetteur d'un message
	 * @return emiter
	 */
	public T getEmiter() {return emiter;}
	
	
	/**
	 * Retourne la date d'un message
	 * @return date
	 */
	public Date getDate() {return date;}
	
	
	/**
	 * Retourne le chat d'un message
	 * @return chat
	 */
	
	public Chat getChat() {return chat;}	

}
