package fr.cpasam.leonardo.model.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import fr.cpasam.leonardo.utilities.HibernateUtil;



public class MemberDAO {

	// Affichage de tous les membres de la BD
	public List<Member> getAllMembers() {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();


		List<Member> myList = new ArrayList<Member>();
		Query query = session.getNamedQuery("findAllMembers");
		myList = query.getResultList();

		session.getTransaction().commit();  
		session.close();
		return myList ;   
	}

	// Hashage du mot de passe
	private String hashPassword(String plainTextPassword){

		return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());

	}

	// Verification du hashage de mot de passe ==> non utilisee
	/* private void checkPass(String plainPassword, String hashedPassword) {

		if (BCrypt.checkpw(plainPassword, hashedPassword))

		System.out.println("The password matches.");

		else

		System.out.println("The password does not match.");

		}
	 */

	// Creation d'un membre avec recuperation des donnees du formulaire 
	public void CreateMember(long id, String firstName, String lastName, String email, String pwd ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member();

		member.id = id;
		member.firstName = firstName ;
		member.lastName = lastName ;
		member.email = email ;
		member.pwd = hashPassword(pwd) ;

		session.save(member);
		session.getTransaction().commit();
		session.close();
	}


	// Recherche d'un membre a partir de son id
	public Member GetMemberID(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member() ;
		member.id = id ;
		Query query = session.getNamedQuery("findMemberById");
		member = (Member)query;

		session.getTransaction().commit();
		session.close();
		return member;
	}

	// Mise a jour des informations d'un membre
	public void UpdateMember(long id, String firstName, String lastName, String email, String pwd) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member();

		member.id = id;
		member.firstName = firstName ;
		member.lastName = lastName ;
		member.email = email ;
		member.pwd = hashPassword(pwd) ;

		session.save(member);
		session.getTransaction().commit();
		session.close();
	}


	// Supprime un membre a l'aide de son id et d'une requete nommee
	public void DeleteMember(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member() ;
		member.id = id ;
		Query query = session.getNamedQuery("findMemberById");

		session.delete(query);
		session.save(query);
		session.getTransaction().commit();
		session.close();	

	}
}
