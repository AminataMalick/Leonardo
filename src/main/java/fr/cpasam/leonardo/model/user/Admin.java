package fr.cpasam.leonardo.model.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = "findAdminById",
	query = "from Admin a where a.USER_ID = :adminId"
	),
	@NamedQuery(
	name = "findAllAdmins",
	query = "from Admin"
	)
})
@Entity
@Table(name="admins")
public class Admin extends User {

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