package fr.cpasam.leonardo.model.chat;

import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;

public interface _ChatManager<T, U> {

	public Chat openChat(U entity) throws ChatNotFoundException, UserNotFoundException;
}
