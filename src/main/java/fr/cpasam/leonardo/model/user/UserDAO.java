package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class UserDAO extends DAOManager{

	private static long cnt = 10;
	/**
	 * Méthode pour incrémenter l'identifiant
	 * @return retourne le compteur incrémenter d'une unité
	 */
	public static long getCnt() {
		return cnt++;
	}
	
	// Bloc static 
	  
	  static {	
	  	cnt = getLastId()+1;
	  }
	  
		public static long getLastId() {
			Statement statement = null;
			long id_User = 0;
			try {
				statement = con.createStatement();
				/* Récupération du Member */
				ResultSet resultat = statement.executeQuery( "SELECT MAX(id_User) FROM User");

				/* Récupération des données du résultat de la requête de lecture */
				if ( resultat.next() ) {
					/* Récupération du membre */
					id_User= resultat.getLong(1);
				}
			}catch (SQLException e) { 
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id_User;
		}
	

	/**
	 * Cherche et renvoi un utilisateur à partir de son identidiant id passé en paramètre de la fonction
	 * @param id identifiant de l'utilisateur permettant de le retrouver
	 * @return retourne l'utilisateur lié à l'id passé en paramètre ou null s'il n'existe pas
	 */
	// Renvoie un user a l'aide de son id
	public static User getUserById(long id) {
		Statement stmt = null;
		User user = null ;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_User, firstName_User,lastName_User,email_User,pwd_User,token_User  FROM Member natural join User WHERE id_User="+id);

			if(rset.next())
			{	

				user = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5), rset.getString(6));

			}
			else {	
				rset = stmt.executeQuery("SELECT id_User, firstName_User,lastName_User,email_User,pwd_User,token_User FROM Admin natural join User WHERE id_User="+id);
				while (rset.next()) {
					user = new Admin(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5), rset.getString(6));				
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return user ; 
	}

	/**
	 * Cherche et renvoi un utilisateur à partir de son adresse mail passée en paramètre de la fonction 
	 * @param email mail de l'utilisateur recherché
	 * @return retourne un utilisateur ou null s'il n'existe pas
	 */
	public static User mailToUser(String email) {
		Statement stmt = null;
		User user = null ;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_User, firstName_User,lastName_User,email_User,pwd_User  FROM Member natural join User WHERE email_User='"+email+"'");
						
				while (rset.next()) {
					 user = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));

				}
				rset = stmt.executeQuery("SELECT id_User, firstName_User,lastName_User,email_User,pwd_User  FROM Admin natural join User WHERE email_User='"+email+"'");
				while (rset.next()) {
					 user = new Admin(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));				

				}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return user ; 
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
