package fr.cpasam.leonardo.model.user;

import java.util.jar.Attributes.Name;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;


@Entity
@Table(name="users")
public abstract class User {
	
	@Id
	@GeneratedValue
	@Column (name="USER_ID")
	private long id;
	@Column (name="USER_NAME")
	private Name name;
	@Column (name="USER_EMAIL")
	private String email;
	@Column (name="USER_PWD")
	private String pwd;
	
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






