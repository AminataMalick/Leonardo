package fr.cpasam.leonardo.model.shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.tag.ProductTag;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ShopDAO extends DAOManager {

	public static Shop get(long shop_id) {
		Statement statement = null;		
		Statement statement2 = null;		

		Shop shop = null ;
		List<Product> products = new ArrayList<Product>();	
		Member member = null ;					

		try {
			statement = con.createStatement();
			statement2 = con.createStatement();


			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop WHERE id_Shop="+ shop_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération des produits liés au shop */
				products = getProducts(shop_id) ;

				long member_id = resultat.getLong(4);

				/* Exécution d'une requête de lecture */
				ResultSet resultat1 = statement2.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ member_id);

				/* Récupération des données du résultat de la requête de lecture */
				while ( resultat1.next() ) {
					member= new Member(resultat1.getLong(2),resultat1.getString(4),resultat1.getString(5),resultat1.getString(6),resultat1.getString(7), resultat1.getString(8));
				}


				
				shop= new Shop(resultat.getLong(1),resultat.getString(2),resultat.getString(3),null,member, products);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { 
			statement.close();
			statement2.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shop;
	}

	public static List<Shop> all() {
		Statement statement = null;		
		Statement statement2 = null;		

		List<Shop> shops = new ArrayList<Shop>();	
		List<Product> products = new ArrayList<Product>();						
						

		try {
			Member member = null ;	
			Shop shop = null;
			statement = con.createStatement();
			statement2 = con.createStatement();

			
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop ");
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				long shop_id = resultat.getLong(1);
				
				/* Récupération des produits liés au shop */
				products = getProducts(shop_id) ;
				
				long member_id = resultat.getLong(4);

				/* Exécution d'une requête de lecture */
				ResultSet resultat1 = statement2.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ member_id);

				/* Récupération des données du résultat de la requête de lecture */
				while ( resultat1.next() ) {
					member= new Member(resultat1.getLong(2),resultat1.getString(4),resultat1.getString(5),resultat1.getString(6),resultat1.getString(7), resultat1.getString(8));
				}
				
				shop= new Shop(shop_id,resultat.getString(2),resultat.getString(3),null,member, products);
				shops.add(shop);
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		try { 
			statement.close();
			statement2.close();
		} catch (SQLException e) { e.printStackTrace();}

		return shops;
	}
	


	public static Shop createShop(String shopName, String description, long member_id) {
		Statement statement = null;		
		Shop shop = null ;
		Member member = null ;
		try {
			long shop_id = Shop.getCnt() ;
			statement = con.createStatement();
			/* Insertion d'un shop */
			int res = statement.executeUpdate("INSERT INTO Shop(id_Shop, name_Shop, description_Shop, id_Member)VALUES("+shop_id+",'"+shopName+"','"+description+"',"+member_id+")");

			/* Récupération du membre correspondant à l'id donné*/
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ member_id);

			/* Récupération valeurs attributs du membre */
			while ( resultat.next() ) {
				member= new Member(resultat.getLong(2),resultat.getString(4),resultat.getString(5),resultat.getString(6),resultat.getString(7), null);
			} 
			
			/* Création shop */
			shop= new Shop(shop_id, shopName, description, null,  member, null );
			 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shop;

	}

	public static Shop updateShop(long shop_id, String ShopName, String description, long member_id) {
		Statement statement = null;	
		Shop shop = null ;
		Member member = null ;
		List<Product> products = new ArrayList<Product>();		

		try {
			statement = con.createStatement();
			
			/* Modification du Shop */
			int update = statement.executeUpdate("UPDATE Shop SET id_Shop = "+shop_id+",name_Shop ='"+ShopName+"', description_Shop='"+description+"', id_Member="+member_id+" WHERE id_Shop ="+shop_id);
			/* En cas d'erreur */
			if (update < 0){ return null ; }
			
			/* Récupération du membre correspondant à l'id donné*/
			ResultSet resultat0 = statement.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ member_id);

			/* Récupération valeurs attributs du membre */
			while ( resultat0.next() ) {
				member= new Member(resultat0.getLong(2),resultat0.getString(4),resultat0.getString(5),resultat0.getString(6),resultat0.getString(7), null);
			} 
			
			shop = new Shop(shop_id,ShopName,description,null, member, null);
			
			/* Récupération des produits liés au shop */
			products = getProducts(shop_id) ;
			
			/*Ajout des produit lié au shop */
			shop.addProduct(products) ;
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return shop;
		
	}
	
	
	public static List<Product> getProducts(long shop_id) {
		Statement statement = null;	
		Statement statement2 = null;	

		List<Product> products = new ArrayList<Product>();	
		ArrayList<ProductTag> tags = new ArrayList<ProductTag>();						

		try {
			Product product = null;
			ProductTag tag = null ;
			statement = con.createStatement();
			statement2 = con.createStatement();


			/* Récupération des produits lié au shop donné */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product WHERE id_Shop =" + shop_id);
			/* Récupération de chaque produit et ajout dans l'arrayList Products */
			while ( resultat.next() ) {
				long product_id = resultat.getLong(1);
	
				/* Récupération des tags lié au produit */
				ResultSet resultat2 = statement2.executeQuery( "SELECT * FROM Tag NATURAL JOIN (SELECT id_Tag FROM ProductTag WHERE id_Product =" +product_id+") R"); 
				/* Récupération de chaque tag et ajout dans l'arrayList tags */
				while ( resultat2.next() ) {
				
					tag= new ProductTag(resultat2.getLong(1),resultat2.getString(2));
					tags.add(tag);
				}
					

				product= new Product(product_id,resultat.getString(2),shop_id,resultat.getFloat(4),tags);
				products.add(product);
				
			}
		}catch (SQLException e) { e.printStackTrace();} 
		try { 
			statement.close();
			statement2.close();
		} catch (SQLException e) { e.printStackTrace();}
		
		return products ;
			
	}
	

	public static Member getMember(long member_id, long shop_id) {
		Statement statement = null;		
		Member member = null ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "select * from User natural join Member where id_Member = (select id_Member from ShopMember where id_Member = "+member_id+" and id_Shop = "+shop_id+")" );

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				//int idShop= resultat.getInt(1);
				member = new Member(resultat.getLong(7),resultat.getString(2),resultat.getString(3),resultat.getString(4), resultat.getString(5), resultat.getString(6));
			} 

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return member;
	}
	
	
	public static Member getOwner(long shop_id) {
		Statement statement = null;		
		Member member = null ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM User NATURAL JOIN Member WHERE id_Member= (SELECT id_Member FROM Shop WHERE id_Shop = " + shop_id + ")" );

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				//int idShop= resultat.getInt(1);
				member = new Member(resultat.getLong(7),resultat.getString(2),resultat.getString(3),resultat.getString(4), resultat.getString(5), resultat.getString(6));
			} 

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return member;
	}

	public static void delete(long shop_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			int deleted =statement.executeUpdate("DELETE FROM ShopMember WHERE id_Shop="+shop_id);
			statement.executeUpdate("DELETE FROM Product WHERE id_Shop="+shop_id);
			statement.executeUpdate("DELETE FROM ShopRetail WHERE id_Shop="+shop_id);
			statement.executeUpdate("DELETE FROM Shop WHERE id_Shop="+shop_id);
		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
		

	}

	/**
	 * Renvoi toutes les boutiques dont le membre est propriétaire ou modérateur
	 * @param user_id
	 * @return
	 */
	public static ArrayList<Shop> getByMember(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}



}

