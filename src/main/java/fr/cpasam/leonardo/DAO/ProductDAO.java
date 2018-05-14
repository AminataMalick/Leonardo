package fr.cpasam.leonardo.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.tag.Tag;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ProductDAO extends DAOManager {

	/**
	 * Attribut de la classe ProductDAO representant un compteur pour générer un identifiant automatiquement
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
	/**
	 * Cherche l'identifiant maximum dans la table afin d'incrémenter celui-ci d'une unité et de  générer un nouvel identifiant automatiquement 
	 * @return retourne l'identifiant maximum de Product
	 */
	public static long getLastId() {
		Statement statement = null;
		long id_Product = 0;
		try {
			statement = con.createStatement();
			/* Récupération de l'identifiant max du Produit */
			ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Product) FROM Product");

			/* Récupération des données du résultat de la requête de lecture */
			if ( resultat.next() ) {
				/* Récupération du produit */
				id_Product= resultat.getLong(1);
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id_Product;
	}


	/**
	 * Recherche d'un produit à partir de son id
	 * @param product_id
	 * @return Product
	 **/
	public static Product get(long product_id)  {
		Statement statement = null;		
		Product product = null ;
		ArrayList<Tag> tags = new ArrayList<Tag>() ;

		try {
			statement = con.createStatement();
			tags = ProductTagDAO.getTagsByProduct(product_id);

			/* récupération du produit selon l'id donné */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product WHERE id_Product="+ product_id);

			/* Affectation valeur récupéré */
			while ( resultat.next() ) {				
				product= new Product(product_id,resultat.getString(2),resultat.getLong(4), resultat.getFloat(3),tags);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return product;
	}

	/**
	 * Retourne la liste de tous les produits de la BD
	 * @return List<Product>
	 **/
	public static ArrayList<Product> all() {
		ArrayList<Product> products = new ArrayList<Product>();		
		Statement statement = null;		

		try {
			statement = con.createStatement();
			Product product = null ;

			/* Récupération de tous les produits */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product ");

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {

				long id = resultat.getLong(1);
				String name = resultat.getString(2);
				float price = resultat.getFloat(3);
				long id_shop = resultat.getLong(4);
				ArrayList<Tag> tags = new ArrayList<>();
				tags= ProductTagDAO.getTagsByProduct(id);

				product= new Product(id, name, id_shop, price, tags);
				products.add(product);

			} 
		}catch (SQLException e) { e.printStackTrace();} 

		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return products;

	}


	/**
	 * Creation d'un produit à partir des attributs passés en paramètre de la fonction
	 * @param name nom du produit
	 * @param shop_id identifiant du shop associé à la vente du produit
	 * @param unityPrice prix du produit
	 * @return Product retourne le produit créé
	 */
	public static Product create( String name, long shop_id, float unityPrice ) {
		Statement statement = null;		
		Product product = null ;
		try {
			long product_id = getCnt() ;

			statement = con.createStatement();
			/* Insertion d'un produit */
			statement.executeUpdate("INSERT INTO Product(id_Product, name_Product, UnityPrice, id_Shop)VALUES("+product_id+",'"+name+"',"+unityPrice+","+shop_id+")");

			/* Création shop */
			product= new Product(product_id, name, shop_id, unityPrice, null );



		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return product;
	}


	/**
	 * Mise à jour d'un produit
	 * @param id identifiant du produit
	 * @param name nom du produit
	 * @param provenance provenance du produit
	 * @param unityPrice prix du produit
	 * @param tags mots clés à propos du produit
	 * @return Product retourne le produit mis à jour
	 */
	public static Product update(long product_id, String name, long shop_id, float unityPrice) {
		Statement statement = null;	
		Product product = null ;

		try {
			statement = con.createStatement();

			/* Modification du Produit */
			int update = statement.executeUpdate("UPDATE Product SET name_Product ='"+name+"', UnityPrice="+unityPrice+", id_Shop="+shop_id+" WHERE id_Product ="+product_id);
			/* En cas d'erreur */
			if (update < 0){ return null ; }
			ArrayList<Tag> tags = ProductTagDAO.getTagsByProduct(product_id);

			product = new Product(product_id, name, shop_id, unityPrice, tags);

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return product;
	}		



	/**
	 * Supprime un produit à l'aide de son id   
	 * @param product_id
	 **/
	public  static void delete(long product_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			statement.executeUpdate("DELETE FROM ProductTag WHERE id_Product="+product_id);
			statement.executeUpdate("DELETE FROM Product WHERE id_Product="+product_id);
		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
	}


}


