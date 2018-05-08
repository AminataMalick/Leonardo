package fr.cpasam.leonardo.model.chat;

import java.util.Date;


public abstract class Message<T,U> {

	
	private long id ;
	private T emiter;
	private java.util.Date date;
	private U content ;
	private Chat chat;

	public Message() {}
	
	public Message(U content,Chat chat){
		this.id=id;
		this.emiter=emiter;
		this.date=date;
		this.content=content;
		this.chat=chat;
	}
	/**
	 * Retourne l'id d'un message
	 * @return id
	 */
	public long GetMessageId() {return id;}
	/**
	 * Retourne l'emetteur d'un message
	 * @return emiter
	 */
	public T GetMessageEmiter() {return emiter;}
	/**
	 * Retourne la date d'un message
	 * @return date
	 */
	public java.util.Date GetMessageDate() {return date;}
	/**
	 * Retourne le contenu d'un message
	 * @return content
	 */
	public String GetMessageContent() {return content;}
	/**
	 * Retourne le chat d'un message
	 * @return chat
	 */
	public Chat GetMessageChat() {return chat;}

	
	/**
	 * Met à jour l'id d'un message
	 * @param id
	 */
	public void SetMessageId(long id) {this.id=id;}
	/**
	 * Met à jour l'emetteur d'un message
	 * @param emiter
	 */
	public void SetMessageEmitter (T emiter) {this.emiter=emiter;}
	/**
	 * Met à jour la date d'un message
	 * @param date
	 */
	public void SetMessageDate(Date date) {this.date=date;}
	/**
	 * Met à jour le contenu d'un message
	 * @param content
	 */
	public void SetMessageContent(U content) {this.content=content;}
	/**
	 * Met à jour le chat d'un message
	 * @param chat
	 */
	public void SetMessageChat(Chat chat) {this.chat=chat;}
	



}

	
/*
public boolean sendMessage(Message m) {return true;}
public boolean sendNotification() {return true;}
*/
