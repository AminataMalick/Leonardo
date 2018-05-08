package fr.cpasam.leonardo.model.user;

import java.util.List;
import fr.cpasam.leonardo.model.chat.Chat;

public abstract class User {

	protected long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String pwd;
	protected List<Chat> chats;

	public User(String firstName, String lastName, String email, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
	}

	/**
	 * Retourne l'id d'un utilisateur
	 * @return id
	 */
	public long getUserId() {return id;}
	/**
	 * Retourne le prénom d'un utilisateur
	 * @return fistName
	 */
	public String getUserFistName() {return firstName;}
	/**
	 * Retourne le nom d'un utilisateur
	 * @return lastName
	 */
	public String getUserLastName() {return lastName ;}
	/**
	 * Retourne l'email d'un utilisateur
	 * @return email
	 */
	public String getUserEmail() {return email;}
	/**
	 * Retourne le mot de passe d'un utilisateur
	 * @return pwd
	 */
	public String getUserPwd() {return pwd;}
		
	/**
	 * Met à jour l'id d'un utilisateur
	 * @param id
	 */
	public void setUserId(long id) {this.id=id;}
	/**
	 * Met à jour le prenom d'un utilisateur
	 * @param firstname
	 */
	public void setUserFistName(String firstname) {this.firstName=firstname;}
	/**
	 * Met à jour le nom d'un utilisateur
	 * @param lastname
	 */
	public void setUserLastName(String lastname) {this.lastName=lastname;}
	/**
	 * Met à jour l'email d'un utilisateur
	 * @param email
	 */
	public void setUserEmail(String email) {this.email=email;}
	/**
	 * Met à jour le mot de passe d'un utilisateur
	 * @param pwd
	 */
	public void setUserPwd(String pwd) {this.pwd=pwd;}
	
	
	public void addChat(Chat nwChat) {

		if (!(this.chats.contains(nwChat))) {
			this.chats.add(nwChat);
		}

	}

	/*
	 * TODO
	 */
	public static Member get(Long memberID) {

		return null;
	}

	//TODO
	/*
	public boolean singhIn(String mail, String pwd) return true;

	public boolean singhIn(String firstname, String lastName,String mail, String pwd) return true;

	public boolean changeMail(String newMail) {
		Query query = session.createQuery("update.... set mail =: mail"+" where id =:id");
		query.setParameter("mail", newMail);
		query.setParameter("id", this.id);
		query.executeUpdate() != 1?return false:return true;
	}

	public boolean deleteAccount() {
		Query query = session.createQuery("delete... where id= :id");
		query.setParameter("id", this.id);
		query.executeUpdate()!=1?false:return true;
	}*/
}






