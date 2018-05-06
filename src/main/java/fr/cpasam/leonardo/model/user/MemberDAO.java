package fr.cpasam.leonardo.model.user;
import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.query.Query;

import fr.cpasam.leonardo.model.Prout;
import fr.cpasam.leonardo.utilities.CRUD_Service;
import fr.cpasam.leonardo.utilities.HibernateUtil;



public class MemberDAO implements CRUD_Service <Member> {

	public List<Member> getAllMembers() {

    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();

   
    List<Member> myList = new ArrayList<Member>();
    Query query = session.getNamedQuery("findAllMembers");
    myList = query.getResultList();
   
    session.getTransaction().commit();  
 return myList ;   
	}

	@Override
	public void Create(Member entity) {
		  Session session = HibernateUtil.getSessionFactory().openSession();
		  session.beginTransaction();

		  Member member = new Member();
		//	member.id 
		    
        //member. = "WOW!";
       // session.save(member);
        session.getTransaction().commit();
	}

	@Override
	public Member Read() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void Update(Member entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void Delete(Member entity) {
		// TODO Auto-generated method stub
		
	}
}
