package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class UserDAO extends DAOManager{



	// Renvoie un user a l'aide de son id
	public static User getUserById(long id) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE id_User="+id);
			if(rset.next())
			{			
				while (rset.next()) {
					User user = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
					return user ;
				}
			}
			else {	
				rset = stmt.executeQuery("SELECT * FROM Admin natural join User WHERE id_User="+id);
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

	public static User mailToUser(String email) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE email_User="+email);
			if(rset.next())
			{			
				while (rset.next()) {
					User user = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
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


	
			// Supprime un token a partir de l'id d'un utilisateur
			public static boolean deleteToken(long id) {
				Statement stmt = null;
				try {
					stmt = con.createStatement();
					int deleted = stmt.executeUpdate("UPDATE User SET token_User = null WHERE id_User="+id);		
					if(deleted > 0) {
						return true;
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
				return false ;
			}
			
			// Met à jour le token de l'utilisateur passé en parametre
			public static boolean saveToken(User user ) {
				Statement stmt = null;
				try {
					stmt = con.createStatement();
					int deleted = stmt.executeUpdate("UPDATE User SET token_User ='"+user.getToken()+"' WHERE id_User="+user.getId());		
					if(deleted > 0) {
						return true;
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
				return false ;
			}
	
}
