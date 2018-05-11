package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.utilities.DAOManager;


public class MemberDAO extends DAOManager {
	
	/**
	 * Affichage de tous les membres
	 * @return retourne une liste composée de tous les membres
	 */
	public static List<Member> all() {
		List<Member> members = new ArrayList<Member>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User");

			while (rset.next()) {
				Member member = new Member(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return members ;   
	}



	/**
	 * Crée un membre avec la récupération des données du formulaire et un id attribué automatiquement à l'aide d'un compteur
	 * @param firstName prénom du membre 
	 * @param lastName nom du membre
	 * @param email email du membre
	 * @param pwd mot de passe du membre
	 * @return retourne le nouveau membre créé
	 */
	public static Member create(String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		long id = User.getCnt();
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("INSERT INTO User(id_User, firstName_User, lastName_User, email_User, pwd_User)VALUES("+id+",'"+firstName+"','"+lastName+"','"+email+"','"+ pwd+"')");
			deleted = stmt.executeUpdate("INSERT INTO Member(id_Member, id_User, id_Geoloc) VALUES("+id+","+id+",null)");
			Member member = new Member(id,firstName,lastName,email, pwd);
			return member ;
		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return null ;
	}
	
	
	/**
	 * Met à jour un membre en récupérant toutes les données 
	 * @param id identifiant du membre à mettre à jour
	 * @param firstName prénom du membre
	 * @param lastName nom du membre
	 * @param email email du membre
	 * @param pwd mot de passe du membre
	 * @return retourne le membre mis à jour
	 */
	public static Member update(long id, String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("UPDATE User SET firstName_User = '"+firstName+"',lastName_User ='"+lastName+"', email_User='"+email+"', pwd_User='"+pwd+"' WHERE id_User ="+id+"");
			if (deleted > 0){
			Member member = new Member(id,firstName,lastName,email, pwd);
			return member ;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return null ;
	}
	
	
	
	
	

	/**
	 * Trouver un membre à partir de don ID
	 * @param memberID identifiant du membre que l'on cherche
	 * @return retourne le membre lié à l'identifiant passé en paramètre de la fonction ou null s'il n'existe pas
	 */
	public static Member get(Long memberID) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE id_User="+memberID);
			if(rset.next())
			{			
				while (rset.next()) {
					Member member = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
					return member ;
				}

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return null ;
	}


	/**
	 * Supprime un membre à partir de son identifiant
	 * @param memberID identifiant du membre à supprimer
	 */
	

	public static boolean delete(Long memberID) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("DELETE FROM ShopMember WHERE id_Member="+memberID);
			deleted += stmt.executeUpdate("DELETE FROM Message WHERE id_Member="+memberID);
			deleted += stmt.executeUpdate("DELETE FROM Shop WHERE id_Member="+memberID);
			deleted += stmt.executeUpdate("DELETE FROM Admin WHERE id_User="+memberID);
			deleted += stmt.executeUpdate("DELETE FROM Member WHERE id_Member="+memberID);
			deleted += stmt.executeUpdate("DELETE FROM User WHERE id_User="+memberID);
			
			if(deleted > 0) {
				return true;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return false;
	}


		/**
		 * Cherche un membre à partir d'une adresse mail
		 * @param email email du membre 
		 * @return retourne le membre associé à l'adresse mail passée en paramètre s'il existe, sinon null
		 */
		public static Member mailToMember(String email) {
			Statement stmt = null;
			Member member = null ;
			try {
				stmt = con.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE email_User='"+email+"'");

					while (rset.next()) {
						member = new Member(rset.getLong(2),rset.getString(4),rset.getString(5),rset.getString(6),rset.getString(7),rset.getString(8));
					}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}try { stmt.close();
			} catch (SQLException e) { e.printStackTrace();}
			return member ;
		}
		
		
}
