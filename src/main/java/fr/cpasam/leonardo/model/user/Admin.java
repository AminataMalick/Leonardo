package fr.cpasam.leonardo.model.user;


public class Admin extends User {

	public Admin(long id, String firstName, String lastName, String email, String pwd) {
		super(id, firstName, lastName, email, pwd);
	}
	
	public String toString() { 
		return("id : " + this.id + " firstName : " + this.firstName + " lastName : " + this.lastName + " email : " + this.email + " pwd : " + this.pwd);	
	}
	
	/*private static int level=1;
	
	public boolean deleteUser (long id) {
		Query query = session.createQuery("from Member where id =:id");
		query.getParameter("id",id);
		List<Member> res = query.list();
		return res.get(0).deleteAccount();
	}
	
	public boolean deleteProduct(long id) {
		Query query = session.createQuery("from Product where id =:id");
		query.getParameter("id",id);
		List<Product> res = query.list();
		return res.get(0).deleteAccount();
	}*/

}