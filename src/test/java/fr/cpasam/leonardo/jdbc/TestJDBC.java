package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.user.Admin;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.Connexion;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TestJDBC extends DAOManager {


	public static void main(String[] args) throws Exception {
		List<Member> members = new ArrayList<Member>();
		Statement stmt = null;
		Connection myConn = Connexion.getInstance() ;
		try {
			// Retourne l'id et le nom de tous les membres
			stmt = myConn.createStatement();
			
			ResultSet rset = stmt.executeQuery("SELECT * FROM User WHERE email_User='am@ba'");
			if(rset == null) {
					throw new Exception("email non lie a un membre");
			}
			else {
				ResultSet rset2 = stmt.executeQuery("SELECT * FROM Member WHERE email_Member='am@ba'");
				if (rset.getInt(1) == rset2.getInt(1)) {
					Member member = new Member(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
				}
				else {
					rset2 = stmt.executeQuery("SELECT * FROM Admin WHERE email_Admin='am@ba'");
					if (rset.getInt(1) == rset2.getInt(1)) {
						Admin admin = new Admin(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
				}
			}
			}
			
			/*ResultSet rset = stmt.executeQuery("SELECT * FROM Member");
			while (rset.next()) {
				Member member = new Member(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
				members.add(member);
			}

			System.out.println(members.toString());*/

		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {

			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

		}
	}
}