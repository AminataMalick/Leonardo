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

public class ProductTagDAO extends DAOManager{
	
	/**
	 * Créer un productTag
	 * @param keyword
	 * @return ProductTag
	 */
	public static ProductTag create(String keyword) {
		Statement statement = null;		
		ProductTag tag = null;
		try {
			long tag_id = Tag.getCnt() ;
			statement = con.createStatement();
			
			/* Insertion d'un tag*/
			int res = statement.executeUpdate("INSERT INTO Tag(id_Tag, keyword)VALUES("+tag_id+",'"+keyword+"')");

			/* Création shop */
			tag = new ProductTag(tag_id, keyword);
			 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tag;

	}


	/**
	 * Retourne la liste de tags lié au produit donné
	 * @param id
	 * @return ArrayList<ProductTag>
	 */
	public static ArrayList<ProductTag> getTagsByProduct(long product_id) {
		
		ArrayList<ProductTag> tags = new ArrayList<ProductTag>();
		Statement statement = null;		

		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag Natural Join ProductTag WHERE id_product="+product_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				ProductTag pt = new ProductTag(resultat.getLong(1), resultat.getString(2));
				tags.add(pt);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tags;
	}

	/**
	 * Retourne le ProductTag lié au keyword donné
	 * @param keyword
	 * @return ProductTag
	 */
	public static ProductTag getTagByName(String keyword) {
		ProductTag tag = null;
		Statement statement = null;		

		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag Natural Join ProductTag WHERE keyword='"+keyword+"'");

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				tag = new ProductTag(resultat.getLong(1), resultat.getString(2));
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return tag;
	}
	
	public static void delete(long tag_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			int deleted = statement.executeUpdate("DELETE FROM ProductTag WHERE id_Tag="+tag_id);
		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
		

	}


}
