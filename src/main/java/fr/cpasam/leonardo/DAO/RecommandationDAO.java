package fr.cpasam.leonardo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.DAO.MemberDAO;
import fr.cpasam.leonardo.DAO.ShopDAO;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class RecommandationDAO extends DAOManager  {


	/**
	 * Attribut de la classe RecommandationDAO representant un compteur pour générer un identifiant automatiquement
	 */
	private static long cnt = 0;
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
			long id_Recommandation = 0;
			try {
				statement = con.createStatement();
				/* Récupération de l'identifiant de la recommandation */
				ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Recommandation) FROM Recommandation");

				/* Récupération des données du résultat de la requête de lecture */
				if ( resultat.next() ) {
					/* Récupération de la recommandation */
					id_Recommandation= resultat.getLong(1);
				}
			}catch (SQLException e) { 
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id_Recommandation;
		}

	
	/**
	 * Affiche une recommandation
	 * @param recommandationid identifiant de la recommandation que l'on souhaite
	 * @return retourne la recommandation associée à l'identifiant passé en paramètre de la fonction
	 */
	public static Recommandation get(long recommandationid) {
		Statement statement = null;		
		Recommandation recommandation = null ;
		try {
			statement = con.createStatement();
			ResultSet rset = statement.executeQuery("SELECT grade_Recommandation,comment_Recommandation,id_Member,id_Shop FROM Recommandation WHERE id_Recommandation="+recommandationid);

			while (rset.next()) {
				recommandation = new Recommandation(recommandationid,rset.getInt(1),rset.getString(2),MemberDAO.get(rset.getLong(3)),ShopDAO.get(rset.getLong(4)));
				return recommandation;
			}

		}catch (SQLException e) {
			e.printStackTrace();
		}try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return recommandation;
	}



	/**
	 * Affiche toutes les recommandations d'un shop
	 * @param idShop identifiant du shop dont on veut les recommandations
	 * @return retourne une liste contenant toutes les recommandations
	 */
	public static List<Recommandation> all(long idShop) {
		List<Recommandation> recommandations = new ArrayList<Recommandation>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_Recommandation,grade_Recommandation,comment_Recommandation,id_Member,id_Shop FROM Recommandation WHERE id_Shop ="+idShop);

			while (rset.next()) {
				Recommandation recommandation = new Recommandation(rset.getLong(1),rset.getInt(2),rset.getString(3),MemberDAO.get(rset.getLong(4)),ShopDAO.get(rset.getLong(5)));
				recommandations.add(recommandation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return recommandations;
	}


	/**
	 * Calcule la note moyenne associée à un shop
	 * @param idShop identifiant du shop dont on veut la note moyenne 
	 * @return retourne la note moyenne comprise entre 0 et 5
	 */
	public static float averageShop (long idShop) {
		Statement stmt = null;
		float moyenne = 0 ;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT sum(grade_Recommandation)/count(grade_Recommandation) FROM Recommandation WHERE id_Shop ="+idShop);

			while (rset.next()) {
				moyenne = rset.getFloat(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return moyenne ; 
	}


	/**
	 * Crée une recommandation à propos d'un shop
	 * @param grade note attribuée au shop sur une échelle de 0 à 5
	 * @param comment commentaire du client
	 * @param member client qui soumet la recommandation
	 * @param shop vendeur lié à la vente
	 * @return
	 */
	public static Recommandation create(int grade,String comment, Member member, Shop shop) {
		Statement stmt = null;
		Recommandation recommandation = null ;
		long id = Recommandation.getCnt();
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Recommandation(id_Recommandation,id_Shop,grade_Recommandation,comment_Recommandation,id_Member) VALUES("+id+","+shop.id()+","+grade+",'"+comment+"',"+member.getId()+")");
			recommandation = new Recommandation(id, grade, comment, member, shop);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return recommandation ; 
	}
	
	/**
	 * Supprime une recommandation sur un shop
	 * @param idRecommandation identifiant de la recommandation
	 * @return retourne un booléen si la suppression s'est bien déroulée
	 */
	public static boolean delete(long idRecommandation) {
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				int deleted =stmt.executeUpdate("DELETE FROM Recommandation WHERE id_Recommandation="+idRecommandation);
				if (deleted <=0) {return false ;}

			}catch (SQLException e) {
				e.printStackTrace();
			}try { stmt.close();
			} catch (SQLException e) { e.printStackTrace();}
			return true;
		}

	
}
