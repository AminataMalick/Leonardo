package fr.cpasam.leonardo.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TagDAO extends DAOManager{
	

	/**
	 * Attribut de la classe MemberDAO representant un compteur pour générer un identifiant automatiquement
	 */
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
			long id_Tag = 0;
			try {
				statement = con.createStatement();
				/* Récupération de l'identifiant du tag */
				ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Tag) FROM Tag");

				/* Récupération des données du résultat de la requête de lecture */
				if ( resultat.next() ) {
					/* Récupération du tag */
					id_Tag= resultat.getLong(1);
				}
			}catch (SQLException e) { 
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id_Tag;
		}


	
	
	
	/**
	 * Créer un Tag
	 * @param keyword
	 * @return Tag
	 */
	public static Tag create(String keyword) {
		Statement statement = null;		
		Tag tag = null;
		try {
			long tag_id = getCnt() ;
			statement = con.createStatement();
			
			/* Insertion d'un tag*/
			int res = statement.executeUpdate("INSERT INTO Tag(id_Tag, keyword)VALUES("+tag_id+",'"+keyword+"')");

			/* Création du tag */
			tag = new Tag(tag_id, keyword);
			 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tag;

	}

	
	/**
	 * Retourne tous les tags
	 * @return 
	 */
	public static ArrayList<Tag> all() {
		Statement statement = null;			
		ArrayList<Tag> tags = new ArrayList<Tag>();							

		try {
			Tag tag = null ;
			statement = con.createStatement();

			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag ");
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				tag= new Tag(resultat.getLong(1),resultat.getString(2));
				tags.add(tag);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { 
			statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return tags;
	}

	
	/**
	 * Récupère le tag lié à l'id donné
	 * @param tag_id
	 * @return Tag
	 */
	public static Tag getTagByID(long tag_id) {
		Tag tag = null;
		Statement statement = null;		

		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag WHERE id_Tag="+tag_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				tag = new Tag(resultat.getLong(1), resultat.getString(2));
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tag;
	}
	
	

	/**
	 * Retourne le Tag lié au keyword donné
	 * @param keyword
	 * @return Tag
	 */
	public static Tag getTagByName(String keyword) {
		Tag tag = null;
		Statement statement = null;		

		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag WHERE keyword='"+keyword+"'");

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				tag = new Tag(resultat.getLong(1), resultat.getString(2));
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tag;
	}
	
	
	/**
	 * Modification d'un tag
	 * @param tag_id
	 * @param keyword
	 * @return Tag
	 */
	public static Tag update(long tag_id, String keyword) {
		Statement statement = null;	
		Tag tag = null ;

		try {
			statement = con.createStatement();

			/* Modification du tag */
			int update = statement.executeUpdate("UPDATE Tag SET keyword ='"+keyword+"' WHERE id_Tag = "+tag_id );
		
			/* En cas d'erreur */
			if (update < 0){ return null ; }

			/* Création du tag */
			tag = new Tag(tag_id,keyword);
			

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return tag;

	}
	
	
	/**
	 * Supprimer un tag
	 * @param tag_id
	 */
	public static void delete(long tag_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			statement.executeUpdate("DELETE FROM ProductTag WHERE id_Tag="+tag_id);
			statement.executeUpdate("DELETE FROM Tag WHERE id_Tag="+tag_id);
			
		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
		

	}


}
