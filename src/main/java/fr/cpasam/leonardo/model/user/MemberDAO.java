package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.utilities.DAOManager;


public class MemberDAO extends DAOManager {

	/**
	 * Attribut de la classe MemberDAO representant un compteur pour générer un identifiant automatiquement
	 */
	private static long cnt = 500;
	/**
	 * Méthode pour incrémenter l'identifiant
	 * @return retourne le compteur incrémenter d'une unité
	 */
	public static long getCnt() {
		return cnt++;
	}
	
	/**
	 * Affichage de tous les membres sous forme de liste
	 * @return retourne une liste composée de tous les membres
	 */
	public static List<Member> all() {
		List<Member> members = new ArrayList<Member>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_Member, firstName_User, lastName_User, email_User,pwd_User,token_User FROM Member natural join User");

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
	 * Crée un membre avec un id attribué automatiquement à l'aide d'un compteur et véréfication que l'adresse mail ne soit pas déjà utilisée
	 * @param firstName prénom du membre 
	 * @param lastName nom du membre
	 * @param email email du membre
	 * @param pwd mot de passe du membre
	 * @return retourne le nouveau membre créé
	 */
	public static Member create(String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		Member member = null ;
		long idUser = User.getCnt();
		long idMember = getCnt();
		try {
			// vérifier si le membre n'existe pas déja
			member = mailToMember(email) ;
			// si non, on le crée
			if (member==null) {
				stmt = con.createStatement();
				int deleted =stmt.executeUpdate("INSERT INTO User(id_User, firstName_User, lastName_User, email_User, pwd_User)VALUES("+idUser+",'"+firstName+"','"+lastName+"','"+email+"','"+ pwd+"')");
				deleted += stmt.executeUpdate("INSERT INTO Member(id_Member, id_User, id_Geoloc) VALUES("+idMember+","+idUser+",null)");
				if (deleted <=0) {
					return null;
				}
				member = new Member(idMember,firstName,lastName,email, pwd);
			}
	}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return member ;
	}


	/**
	 * Met à jour un membre à partir de son identifiant et des données à modifier en vérifiant que l'adresse mail ne soit pas déjà utilisée si modification de celle-ci 
	 * @param id identifiant du membre à mettre à jour
	 * @param firstName prénom du membre
	 * @param lastName nom du membre
	 * @param email email du membre
	 * @param pwd mot de passe du membre
	 * @return retourne le membre mis à jour
	 */
	public static Member update(long id, String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		Member member = null ;
		long idUser = 0 ;
		try {
			// on vérifie si l'email n'est pas déjà associé à un autre membre
			member = mailToMember(email) ;
			if (member!=null && member.id!=id) {
				System.out.println("email déjà associé à un membre");
				return null ;
			}
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_User FROM User natural join Member where id_Member ="+ id);
			while (rset.next()) {
				idUser = rset.getLong(1);
			}
			int deleted =stmt.executeUpdate("UPDATE User SET firstName_User = '"+firstName+"',lastName_User ='"+lastName+"', email_User='"+email+"', pwd_User='"+pwd+"' WHERE id_User ="+idUser+"");
			if (deleted <= 0){ return null ;}

			member = new Member(id,firstName,lastName,email, pwd);
			
		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return member ;
	}




	/**
	 * Trouver un membre à partir de son ID
	 * @param memberID identifiant du membre que l'on cherche
	 * @return retourne le membre lié à l'identifiant passé en paramètre de la fonction ou null s'il n'existe pas
	 */
	public static Member get(long member_id) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT firstName_User, lastName_User, email_User,pwd_User,token_User FROM Member natural join User WHERE id_Member="+member_id);

			while (rset.next()) {
				Member member = new Member(member_id,rset.getString(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5));
				return member ;
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


	public static boolean delete(long member_id) {
		Statement stmt = null;
		long idUser = 0 ;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_User FROM User natural join Member WHERE id_Member ="+member_id);
			while(rset.next()) {
				idUser = rset.getLong(1);
			}
			int deleted =stmt.executeUpdate("DELETE FROM ShopMember WHERE id_Member="+member_id);
			deleted += stmt.executeUpdate("DELETE FROM Message WHERE id_Member="+member_id);
			deleted += stmt.executeUpdate("DELETE FROM Shop WHERE id_Member="+member_id);
			deleted += stmt.executeUpdate("DELETE FROM Admin WHERE id_User="+idUser);
			deleted += stmt.executeUpdate("DELETE FROM Member WHERE id_Member="+member_id);
			deleted += stmt.executeUpdate("DELETE FROM User WHERE id_User="+idUser);

			if (deleted <0) {return false ;}

		}catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return true;
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
			ResultSet rset = stmt.executeQuery("SELECT id_Member, firstName_User, lastName_User, email_User,pwd_User,token_User FROM Member natural join User WHERE email_User='"+email+"'");

			while (rset.next()) {
				member = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return member ;
	}


}
