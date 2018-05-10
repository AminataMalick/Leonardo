package fr.cpasam.leonardo.model.product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.tag.ProductTag;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.DAOManager;
import fr.cpasam.leonardo.utilities.HibernateUtil;

public class ProductDAO extends DAOManager {

	/**
	 * Recherche d'un produit à partir de son id
	 */
	public static Product get(long productId)  {
		Statement statement = null;		
		Product product = null ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product WHERE idproduct="+ productId);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {

				product= new Product(resultat.getLong(1),resultat.getString(2),resultat.getLong(4),(float) resultat.getDouble(3),null);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		return product;
	}

	/**
	 * Retourne la liste de tous les produits de la BD
	 */
	public static ArrayList<Product> all() {


		ArrayList<Product> products = new ArrayList<Product>();						
		try {

			Product product = null ;
			Statement statement = con.createStatement();

			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product ");

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {

				long id = resultat.getLong(1);
				String name = resultat.getString(2);
				float price = resultat.getFloat(3);
				long id_shop = resultat.getLong(4);
				ArrayList<ProductTag> tags = ProductTagDAO.getTagsByProduct(id);


				product= new Product(id, name, id_shop, price, tags);

				products.add(product);

			} 
		}catch (SQLException e) { e.printStackTrace();} 

		System.out.println("Test");
		return products ;   
	}


	/**
	 * Creation d'un produit avec récuperation des donnees du formulaire 
	 */
	public static Product create( String name, long shop_id, float unityPrice, ArrayList<ProductTag> tags ) {
		Statement stmt = null;
		Product product=null;
		long id = Product.getCnt();
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Product(id_Product, name_Product, provenance_Product, unityPrice_Product, tag_Product)VALUES("+id+",'"+name+"','"+shop_id+"','"+unityPrice+"','"+tags+"')");
			product = new Product(id, name, shop_id,unityPrice,tags );
		}catch (SQLException e) {e.printStackTrace();}
		finally {
			if (stmt != null){
				try {stmt.close();}
				catch (SQLException e) {e.printStackTrace();}
			}
		}return product ;
	}


	/* Met à jour les informations d'un produit 	  */
	public static Product update(long id, String name, long shop_id, float unityPrice, ArrayList<ProductTag> tags) {
		//public static Member update(long id,String firstName, String lastName, String email, String pwd, String token) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("UPDATE Member SET name_Product = '"+name+"',provenance_Product ='"+shop_id+"', unityPrice_Product='"+unityPrice+"', tag_Product='"+tags+"' WHERE id_Product ="+id+"");
			if (deleted > 0){
				Product product = new Product(id, name, shop_id,unityPrice,tags );
				return product ;
			}
		}catch (SQLException e) {e.printStackTrace();}
		finally {
			if (stmt != null) {
				try {stmt.close();}
				catch (SQLException e) {e.printStackTrace();}
			}
		}return null ;
	}		


	/**
	 * Supprime un produit à l'aide de son id   */
	public  static void delete(long id) {
		//public static void delete(Long memberID) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("DELETE FROM ProductTag WHERE id_Product="+id);
			stmt.executeUpdate("DELETE FROM Product WHERE  id_Product="+id);

		}catch (SQLException e) {e.printStackTrace();}
		finally {
			if (stmt != null) {
				try {stmt.close();}
				catch (SQLException e) {e.printStackTrace();}
			}
		}	
	}}

