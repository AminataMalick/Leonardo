package fr.cpasam.leonardo.model.chat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


public abstract class Message<T> {
	
	protected long id ;
	protected T emiter;
	protected LocalDateTime date;
	
	public final static DateTimeFormatter dTF = DateTimeFormatter.ofPattern("uuuu-MM-dd kk:mm:ss");
	
	public Message() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Create new entity in db
	 * @param emiter
	 */
	public Message(T emiter, LocalDateTime date){

		this.emiter=emiter;
		this.date = date;;

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
	public LocalDateTime getDate() {return date;}
	
	/**
	 * Retourne le chat d'un message
	 * @return chat
	 */
}
