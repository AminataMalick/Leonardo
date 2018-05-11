package fr.cpasam.leonardo.errors;

import com.google.gson.JsonObject;

public class TextError {
	
	private String message;
	
	public TextError(String message) {
		this.message = message;
	}
	
	public String message() {
		return this.message;
	}
	
	public JsonObject JsonMessage() {
		JsonObject message = new JsonObject();
		message.addProperty("error", this.message);
		return message;
	}
	
}
