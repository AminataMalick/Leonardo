package fr.cpasam.leonardo.model.user;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@NamedQueries({
	@NamedQuery(
	name = Admin.FIND_ADMIN_BY_ID,
	query = "from Admin a where a.id = :adminId"
	),
	@NamedQuery(
	name = Admin.FIND_ALL_ADMINS,
	query = "from Admin"
	)
})
@Entity
@Table(name="admins")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Admin extends User {

	public final static String FIND_ADMIN_BY_ID = "findAdminById";
	public final static String FIND_ALL_ADMINS = "findAllAdmins";
	
	public Admin() {}
	
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