package fr.cpasam.leonardo.model.chat;

import java.time.LocalDateTime;
import java.util.Date;

public class TextMessage<T> extends Message<T> {


	private String content;

	public TextMessage() {
		// TODO Auto-generated constructor stub
	}

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
