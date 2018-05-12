package fr.cpasam.leonardo.model.tag;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.product.ProductDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ProductTagDAO extends DAOManager{
	
	/**
	 * Ajoute un tag à un produit
	 * @param product_id
	 * @param tag_id
	 * @return Product
	 */
	public static Product addTag(long product_id, long tag_id) {
		// To do : Penser a vérifier avant d'ajouter que l'asociation n'existe pas déja
		
		Statement statement = null;	
		Product product = null ;
		ArrayList<Tag> tags = new ArrayList<Tag>() ;
		

		try {
			Tag tag = null ;
			statement = con.createStatement();
			/* Insertion d'un tag*/
			statement.executeUpdate("INSERT INTO ProductTag(id_Product, id_Tag)VALUES("+product_id+","+tag_id+")");
			
			/* Récupération du produit */
			product = ProductDAO.get(product_id);
			
			/* Récupération des infos du tag et ajout de celui */
			tag = TagDAO.getTagByID(tag_id);
			product.addTag(tag);
			
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return product;
	}
	
	/**
	 * Ajoute une liste de tag à un produit
	 * @param product_id
	 * @param tags
	 * @return Product
	 */
	public static Product addTags(long product_id, ArrayList<Tag> tags) {
		Product product = null ;
		
		for (Tag tag : tags) {
			long tag_id = tag.getId();
			addTag(product_id, tag_id);
		}
		
		product = ProductDAO.get(product_id);
		
		return product ;
		
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
		return ;
		

	}


}
