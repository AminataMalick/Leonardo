package fr.cpasam.leonardo.model.chat;

import java.time.LocalDateTime;

public class TextMessage<T> extends Message<T> {


	private String content;

	public TextMessage() {}

	/**
	 * Constructeur 3 parametres
	 * @param emiter
	 * @param content
	 * @param date
	 */
	public TextMessage(T emiter, String content, LocalDateTime date) {
		super( emiter,date);
		this.content = content;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
