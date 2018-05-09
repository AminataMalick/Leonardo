package fr.cpasam.leonardo.model.user;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

import fr.cpasam.leonardo.utilities.HibernateUtil;



public class MemberDAO {

	
	/**
	 * Retourne les membres de la BD 
	 * @return myList
	 */
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

	
	/**
	 *  Hash le mot de passe
	 * @param plainTextPassword
	 * @return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt())
	 */
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

	 
	/**
	 *  Créé un membre avec la recuperation des donnees du formulaire
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
	public static Member CreateMember(String firstName, String lastName, String email, String pwd ) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member();

		member.firstName = firstName ;
		member.lastName = lastName ;
		member.email = email ;
		member.pwd = pwd ;

		session.save(member);
		session.getTransaction().commit();
		session.close();
	}


	
	/**
	 * Recherche un membre à partir de son id 
	 * @param id
	 * @return member
	 */
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

	
	/**
	 * Met à jour les informations d'un membre
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
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


	
	/**
	 *Supprime un membre à partir de son id et d'une requête nommée 
	 * @param id
	 */
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
