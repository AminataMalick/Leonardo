package fr.cpasam.leonardo.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.product.ProductDAO;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ProductTagDAO extends DAOManager{

	
	/**
	 * Test si un l'association entre un produit et un tag existe déjà
	 * @param product_id
	 * @param tag_id
	 * @return boolean [true s'il existe déjà, false sinon]
	 */
	public static boolean estProductTag (long product_id, long tag_id) {
		Statement statement = null;	
		boolean b = true ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM ProductTag WHERE id_Product="+product_id+" and id_Tag="+tag_id);
	
			System.out.println(resultat);
			/* Récupération des données du résultat de la requête de lecture */
			if (!resultat.next()) {
				b = false ;
			}
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return b;
	}
	
	
	/**
	 * Ajoute un tag à un produit
	 * @param product_id
	 * @param tag_id
	 * @return Product
	 */

	public static void addTag(long product_id, long tag_id) {

		Statement statement = null;	
		Product product = null ;
		ArrayList<Tag> tags = new ArrayList<Tag>() ;


		try {
			Tag tag = null ;
			statement = con.createStatement();
			/* Insertion d'un tag*/
			
			if (!estProductTag(product_id, tag_id)) {
				statement.executeUpdate("INSERT INTO ProductTag(id_Product, id_Tag)VALUES("+product_id+","+tag_id+")");
			}
			/* Récupération du produit */
			product = ProductDAO.get(product_id);
	
			/* Récupération des infos du tag et ajout de celui */
			tag = TagDAO.getTagByID(tag_id);
			product.addTag(tag);
			

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
	}

	/**
	 * Ajoute une liste de tag à un produit
	 * @param product_id
	 * @param tags
	 * @return Product
	 */
	public static void addTags(long product_id, ArrayList<Tag> tags) {

		for (Tag tag : tags) {
			long tag_id = tag.getId();
			addTag(product_id, tag_id);
		}

		
		return  ;

	}


	/**
	 * Retourne la liste de tags lié au produit donné
	 * @param id
	 * @return ArrayList<Tag>
	 */
	public static ArrayList<Tag> getTagsByProduct(long product_id) {

		ArrayList<Tag> tags = new ArrayList<Tag>();
		Statement statement = null;		
		
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Tag Natural Join ProductTag WHERE id_product="+product_id);
		
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				Tag pt = new Tag(resultat.getLong(1), resultat.getString(2));
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
	public static ArrayList<Product> getProductsByTag(long tag_id) {

		ArrayList<Product> products = new ArrayList<Product>();
		ArrayList<Tag> tags = new ArrayList<Tag>();

		Statement statement = null;		

		try {
			Product product = null ;
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product Natural Join ProductTag WHERE id_Tag="+tag_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				long product_id = resultat.getLong(1) ;
				tags = getTagsByProduct(product_id) ;
				product = new Product(product_id, resultat.getString(2), resultat.getLong(4), resultat.getFloat(3), tags);
				products.add(product);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return products;
	}


	public static void delete(long tag_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			int deleted = statement.executeUpdate("DELETE FROM ProductTag WHERE id_Tag="+tag_id);

		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}


	}


}
