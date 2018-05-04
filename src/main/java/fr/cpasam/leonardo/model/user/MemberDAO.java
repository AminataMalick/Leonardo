package fr.cpasam.leonardo.model.user;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.cpasam.leonardo.utilities.CRUD_Service;
import fr.cpasam.leonardo.utilities.HibernateUtil;



public abstract class MemberDAO <T> implements CRUD_Service <Member> {

	public List<Member> getAllMembers() {

    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

   
    List<Member> myList = new ArrayList<Member>();
    Query query = session.getNamedQuery("findAllMembers");
    myList = query.list();
   
    session.getTransaction().commit();  
 return myList ;   
	}
}
