package fr.cpasam.leonardo.model.user;


public class Admin extends User {
	
	
	/**
	 * 
	 * @param id identifiant de l'administrateur
	 * @param firstName prénom de l'administrateur
	 * @param lastName nom de l'administrateur
	 * @param email email de l'administrateur
	 * @param pwd mot de passe de l'administrateur
	 */
	public Admin(long id, String firstName, String lastName, String email, String pwd) {
		super(id, firstName, lastName, email, pwd,"");
	}
	
	/**
	 * 
	 * @param id identifiant de l'administrateur
	 * @param firstName prénom de l'administrateur
	 * @param lastName nom de l'administrateur
	 * @param email email de l'administrateur
	 * @param pwd mot de passe de l'administrateur
	 * @param token token de l'administrateur
	 */
	public Admin(long id, String firstName, String lastName, String email, String pwd, String token) {
		super(id, firstName, lastName, email, pwd, token);
	}
	
	/**
	 * méthode toString pour décrire l'objet Admin
	 */
	public String toString() { 
		return("id : " + this.id + " firstName : " + this.firstName + " lastName : " + this.lastName + " email : " + this.email + " pwd : " + this.pwd);	
	}
	

}