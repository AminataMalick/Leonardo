package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class UserDAO extends DAOManager{



	//Retourne un user grace a son email
	// Renvoie un user a l'aide de son email
	public static User mailToUser(String email) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE email_User="+email);
			if(rset.next())
			{			
				while (rset.next()) {
					User user = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
					return user ;
				}
			}
			else {	
				rset = stmt.executeQuery("SELECT * FROM Admin natural join User WHERE email_User="+email);
				while (rset.next()) {
					User user = new Admin(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));				
					return user ;
				}
			}
		}
		catch (SQLException e) {
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
		return null ;
	}

}
