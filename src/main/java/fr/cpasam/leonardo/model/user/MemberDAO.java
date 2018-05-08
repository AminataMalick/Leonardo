package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import fr.cpasam.leonardo.utilities.DAOManager;




public class MemberDAO extends DAOManager {

	
	// Affichage de tous les membres de la BD
	public static List<Member> All() {
		List<Member> members = new ArrayList<Member>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("QUERY_FIND_MEMBERS");

			while (rset.next()) {
				Member member = new Member(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					// Le stmt.close ferme automatiquement le rset.
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}   

		return members ;   
	}




	// Creation d'un membre avec recuperation des donnees du formulaire 
	public void CreateMember(long id, String firstName, String lastName, String email, String pwd ) {

		Member member = new Member();

		member.id = id;
		member.firstName = firstName ;
		member.lastName = lastName ;
		member.email = email ;
		member.pwd = pwd ;

	}
}

	// Recherche d'un membre a partir de son id
	/*	public Member GetMemberID(long id) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member() ;
		member.id = id ;
		Query query = session.getNamedQuery("findMemberById");
		member = (Member)query;

		session.getTransaction().commit();
		session.close();
		return member;
	}*/

	// Mise a jour des informations d'un membre
	/*	public void UpdateMember(long id, String firstName, String lastName, String email, String pwd) {
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
	}*/


	// Supprime un membre a l'aide de son id et d'une requete nommee
	/*public void DeleteMember(long id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();

		Member member = new Member() ;
		member.id = id ;
		Query query = session.getNamedQuery("findMemberById");

		session.delete(query);
		session.save(query);
		session.getTransaction().commit();
		session.close();	

	}*/


