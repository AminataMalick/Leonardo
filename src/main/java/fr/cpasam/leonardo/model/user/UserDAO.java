package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class UserDAO extends DAOManager{


	/**
	 * Cherche et renvoi un utilisateur à partir de son identidiant id passé en paramètre de la fonction
	 * @param id identifiant de l'utilisateur permettant de le retrouver
	 * @return retourne l'utilisateur lié à l'id passé en paramètre ou null s'il n'existe pas
	 */
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
	
	/**
	 * Cherche et renvoi un utilisateur à partir de son adresse mail passée en paramètre de la fonction 
	 * @param email mail de l'utilisateur recherché
	 * @return retourne un utilisateur ou null s'il n'existe pas
	 */
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


			/**
			 * Supprime un token à partir de l'identifiant d'un utilisateur
			 * @param id identifiant de l'utilisateur dont on veut supprimer le token
			 * @return retourne un booléen si tout s'est bien déroulé
			 */
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
			
			
			/**
			 * Mise à jour du token d'un utilisateur
			 * @param user utilisateur avec le nouveau token passé en paramètre
			 * @return retourne un booléen si tout s'est bien déroulé
			 */
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
