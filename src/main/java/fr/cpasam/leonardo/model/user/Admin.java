package fr.cpasam.leonardo.model.user;


public class Admin extends User {

	public Admin() {}
	public Admin(long id, String firstName, String lastName, String email, String pwd) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
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