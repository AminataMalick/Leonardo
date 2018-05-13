package fr.cpasam.leonardo.model.user;

import java.util.ArrayList;
import java.util.List;
import fr.cpasam.leonardo.model.chat.Chat;

public abstract class User {

	protected long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String pwd;
	protected List<Chat> chats;
	protected String token;

	public User() {
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String email, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.chats = new ArrayList<>();
	}
	
	public User(long id, String firstName, String lastName, String email, String pwd, List<Chat> chats) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.chats = chats ;
	}

	public User(long id, String firstName, String lastName, String email, String pwd, String token) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.token=token;
		this.chats = new ArrayList<>();
	}
	
	public User(long id, String firstName, String lastName, String email, String pwd, String token, List<Chat> chats) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.token=token;
		this.chats = chats;
	}
	
	public User(long id, String firstName, String lastName, String email, String pwd) {
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
		this.chats = new ArrayList<>();
	}
	
	/**
	 * Retourne l'id d'un utilisateur
	 * @return id
	 */
	public long getId() {return id;}
	/**
	 * Retourne le prénom d'un utilisateur
	 * @return fistName
	 */
	public String getFirstName() {return firstName;}
	/**
	 * Retourne le nom d'un utilisateur
	 * @return lastName
	 */
	public String getLastName() {return lastName ;}
	/**
	 * Retourne l'email d'un utilisateur
	 * @return email
	 */
	public String getEmail() {return email;}
	/**
	 * Retourne le mot de passe d'un utilisateur
	 * @return pwd
	 */
	public String getPwd() {return pwd;}
		
	/**
	 * Retourne le token associé à la session d'un utilisateur
	 * @return token
	 */
	public String getToken() {return this.token;}
	
	/**
	 * Met à jour l'id d'un utilisateur
	 * @param id
	 */
	public void setId(long id) {this.id=id;}
	/**
	 * Met à jour le prenom d'un utilisateur
	 * @param firstname
	 */
	public void setFistName(String firstname) {this.firstName=firstname;}
	/**
	 * Met à jour le nom d'un utilisateur
	 * @param lastname
	 */
	public void setLastName(String lastname) {this.lastName=lastname;}
	/**
	 * Met à jour l'email d'un utilisateur
	 * @param email
	 */
	public void setEmail(String email) {this.email=email;}
	/**
	 * Met à jour le mot de passe d'un utilisateur
	 * @param pwd
	 */
	public void setPwd(String pwd) {this.pwd=pwd;}

	
	/**
	 * Met à jour le token de la session d'un utilisateur
	 * @param token
	 */
	public void setToken(String token) {this.token = token;}
	
	public void addChat(Chat nwChat) {

		if (!(this.chats.contains(nwChat))) {
			this.chats.add(nwChat);
		}
	}
	
	/**
	 * Renvoie la représentation textuelle d'un utilisateur
	 * @return la chaine de caractère représentant l'utilisateur
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", pwd="
				+ pwd + ", chats=" + chats + ", token=" + token + "]";
	}
	
}






