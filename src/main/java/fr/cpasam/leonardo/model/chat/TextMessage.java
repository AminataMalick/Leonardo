package fr.cpasam.leonardo.model.chat;

public class TextMessage<T> extends Message<T> {

	
	private String content;
	
	public TextMessage(Chat chat, T emiter, String content) {
		super(chat, emiter);
		this.content = content;
		
		this.chat.addMessages(this);
	}

	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
}
