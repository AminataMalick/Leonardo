package fr.cpasam.leonardo.model.user;

import java.util.jar.Attributes.Name;

public abstract class User {
	
	protected long id;
	protected String firstName;
	protected String lastName;
	protected String email;
	protected String pwd;

	public User() {  }

	public User(String firstName, String lastName, String email, String pwd) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
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






