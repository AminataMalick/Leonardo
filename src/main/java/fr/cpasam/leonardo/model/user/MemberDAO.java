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




	 
	/**
	 *  Créé un membre avec la recuperation des donnees du formulaire
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
	public void CreateMember(String firstName, String lastName, String email, String pwd ) {

		Member member = new Member(firstName, lastName, email, pwd);
	
}
	


	public Member get(Long memberID) {
		// TODO Auto-generated method stub
		return null;
	}
}
