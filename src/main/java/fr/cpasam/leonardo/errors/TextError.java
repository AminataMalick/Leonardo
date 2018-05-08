package fr.cpasam.leonardo.errors;

public class TextError {
	
	private String message;
	
	public TextError(String message) {
		this.message = message;
	}
	
	public String message() {
		return this.message;
	}
	
}
