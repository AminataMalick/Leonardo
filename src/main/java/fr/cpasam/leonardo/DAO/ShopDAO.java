package fr.cpasam.leonardo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.Tag;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ShopDAO extends DAOManager {

	

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
			long id_Shop = 0;
			try {
				statement = con.createStatement();
				/* Récupération de l'identifiant max du Shop */
				ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Shop) FROM Shop");

				/* Récupération des données du résultat de la requête de lecture */
				if ( resultat.next() ) {
					/* Récupération du shop */
					id_Shop= resultat.getLong(1);
				}
			}catch (SQLException e) { 
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id_Shop;
		}
	
	/**
	 * Retourne le shop lié à l'id donné
	 * @param shop_id
	 * @return shop
	 */
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

	/**
	 * Retourne la liste de tous les shops
	 * @return List<Shop>
	 */
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


	/**
	 * Créer un shop et le retourne
	 * @param shopName
	 * @param description
	 * @param member_id
	 * @return shop
	 */
	public static Shop createShop(String shopName, String description, long member_id) {
		Statement statement = null;		
		Shop shop = null ;
		Member member = null ;
		try {
			long shop_id = getCnt() ;
			statement = con.createStatement();
			/* Insertion d'un shop */
			int res = statement.executeUpdate("INSERT INTO Shop(id_Shop, name_Shop, description_Shop, id_Member)VALUES("+shop_id+",'"+shopName+"','"+description+"',"+member_id+")");

			/* Récupération du membre correspondant à l'id donné*/
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ member_id);

			/* Récupération valeurs attributs du membre */
			while ( resultat.next() ) {
				member= new Member(resultat.getLong("id_User"),
						resultat.getLong("id_Member"),
						resultat.getString("firstName_User"),
						resultat.getString("lastName_User"),
						resultat.getString("email_User"),
						resultat.getString("pwd_User"),
						resultat.getString("token_User"));
			} 

			/* Création shop */
			shop= new Shop(shop_id, shopName, description, null,  member, null );

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shop;

	}

	/**
	 * Met à jour un shop et le retourne
	 * @param shop_id
	 * @param ShopName
	 * @param description
	 * @param member_id
	 * @return shop
	 */
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
				member= new Member(resultat0.getLong("id_User"),
						resultat0.getLong("id_Member"),
						resultat0.getString("firstName_User"),
						resultat0.getString("lastName_User"),
						resultat0.getString("email_User"),
						resultat0.getString("pwd_User"),
						resultat0.getString("token_User"));	} 

			/* Récupération des produits liés au shop */
			products = getProducts(shop_id) ;

			/* Création du shop */
			shop = new Shop(shop_id,ShopName,description,null, member, products);


		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}

		return shop;

	}


	/**
	 * Retourne la liste des produits lié au shop
	 * @param shop_id
	 * @return List<Product>
	 */
	public static List<Product> getProducts(long shop_id) {
		Statement statement = null;	
		Statement statement2 = null;	

		List<Product> products = new ArrayList<Product>();	

		try {
			Product product = null;
			Tag tag = null ;
			statement = con.createStatement();
			statement2 = con.createStatement();


			/* Récupération des produits lié au shop donné */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Product WHERE id_Shop =" + shop_id);
			/* Récupération de chaque produit et ajout dans l'arrayList Products */
			while ( resultat.next() ) {
				long product_id = resultat.getLong(1);
				ArrayList<Tag> tags = new ArrayList<>();
				/* Récupération des tags lié au produit */
				ResultSet resultat2 = statement2.executeQuery( "SELECT * FROM Tag NATURAL JOIN (SELECT id_Tag FROM ProductTag WHERE id_Product =" +product_id+") R"); 
				/* Récupération de chaque tag et ajout dans l'arrayList tags */
				while ( resultat2.next() ) {

					tag= new Tag(resultat2.getLong(1),resultat2.getString(2));
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


	/**
	 * Retourne le membre si celui ci est bien membre d'un shop
	 * @param member_id
	 * @param shop_id
	 * @return member
	 */
	public static Member getMember(long member_id, long shop_id) {
		System.out.println("In getMember with member_id = "+member_id+" and shop_id = "+shop_id);
		Statement statement = null;		
		Member member = null ;
		Member owner = getOwner(shop_id);

		if(owner.getId() == member_id) member = owner;
		if(member == null ) {

			try {
				System.out.println("create statement ...");
				statement = con.createStatement();
				/* Exécution d'une requête de lecture */

				System.out.println("execute query...");
				ResultSet resultat = statement.executeQuery( "select * from User natural join Member where id_Member = (select id_Member from ShopMember where id_Member = "+member_id+" and id_Shop = "+shop_id+")" );

				/* Récupération des données du résultat de la requête de lecture */
				System.out.println("before while...");
				System.out.println("resultat "+resultat.isAfterLast());
				if(resultat.next()) {
					//int idShop= resultat.getInt(1);
					System.out.println("before creation of member");
					member = new Member(resultat.getLong(7),resultat.getString(2),resultat.getString(3),resultat.getString(4), resultat.getString(5), resultat.getString(6));
					System.out.println("after member creation");

				}

			}catch (SQLException e) { e.printStackTrace();} 


			try { statement.close();
			} catch (SQLException e) { e.printStackTrace();}
		}
		
		System.out.println("member est :"+member);
		if(member != null ) System.out.println("return member with email = "+member.getEmail());
		return member;
	}



	/**
	 * Retourne le membre propriétaire du shop donné
	 * @param shop_id
	 * @return member
	 */
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

	/**
	 * Supprime un shop donné
	 * @param shop_id
	 */
	public static void delete(long shop_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			statement.executeUpdate("DELETE FROM Recommandation WHERE id_Shop="+shop_id);
			statement.executeUpdate("DELETE FROM ShopMember WHERE id_Shop="+shop_id);
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
		ArrayList<Shop> shops = new ArrayList<Shop>();
		List<Product> products = new ArrayList<Product>();	
		Statement statement = null;
		Statement statement2 = null;

		Shop shop = null ;
		Member member = null ;
		try {

			statement = con.createStatement();
			statement2 = con.createStatement();

			/* Recherche de tous les shops non propriétaire lié à un membre */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop WHERE id_Shop = (SELECT id_Shop FROM ShopMember WHERE id_Member = "+user_id+ ")" );

			/* Attribution des valeurs récupérées */
			while ( resultat.next() ) {
				long shop_id = resultat.getLong(1);
				products = getProducts(shop_id);

				/* Récupération du membre correspondant à l'id donné*/
				ResultSet resultat0 = statement2.executeQuery( "SELECT * FROM Member natural join User WHERE id_Member="+ user_id);

				/* Récupération valeurs attributs du membre */
				while ( resultat0.next() ) {
					member= new Member(resultat0.getLong("id_User"),
							resultat0.getLong("id_Member"),
							resultat0.getString("firstName_User"),
							resultat0.getString("lastName_User"),
							resultat0.getString("email_User"),
							resultat0.getString("pwd_User"),
							resultat0.getString("token_User"));	} 

				shop = new Shop(shop_id,resultat.getString(2),resultat.getString(3),null,member, products);
				shops.add(shop);
			}
		}catch (SQLException e) { e.printStackTrace();} 
		try { 
			statement.close();
			statement2.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shops;
	}



}

