package fr.cpasam.leonardo.model.user;

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

	public long GetUserId() {return id;}
	public String GetUserFistName() {return firstName;}
	public String GetUserLastName() {return lastName ;}
	public String GetUserEmail() {return email;}
	public String GetUserPwd() {return pwd;}
		

	public void SetUserId(long id) {this.id=id;}
	public void SetUserFistName(String firstname) {this.firstName=firstname;}
	public void SetUserLastName(String lastname) {this.lastName=lastname;}
	public void SetUserEmail(String email) {this.email=email;}
	public void SetUserPwd(String pwd) {this.pwd=pwd;}
	
	
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






