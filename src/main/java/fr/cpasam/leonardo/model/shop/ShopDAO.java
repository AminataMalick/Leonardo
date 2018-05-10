package fr.cpasam.leonardo.model.shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ShopDAO extends DAOManager {

	public static Shop get(Long shopId) {
		Statement statement = null;		
		Shop shop = null ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop WHERE idShop="+ shopId);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				//int idShop= resultat.getInt(1);
				shop= new Shop(resultat.getLong(1),resultat.getString(2),resultat.getString(3),null,shop.getMember());
			} 
		}catch (SQLException e) { e.printStackTrace();} 
		return shop;
	}

	public static List<Shop> all() {
		List<Shop> shops = new ArrayList<Shop>();						
		try {
			Shop shop = null;
			Statement statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop ");
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				//int idShop= resultat.getInt(1);
				shop= new Shop(resultat.getLong(1),resultat.getString(2),resultat.getString(3),null,shop.getMember());
				shops.add(shop);
			} 
		}catch (SQLException e) { e.printStackTrace();} 

		return shops;
	}
	


	public static Shop createShop(String shopName, String description, long member_id) {
		return null;
		// TODO Auto-generated method stub

	}

	public static Shop updateShop(long shop_id, String ShopName, String description, long member_id) {

		// TODO Auto-generated method stub
		return null; //Attention à bien remettre la liste des produits avant de renvoyer la boutique
	}

	public static Member getMember(long member_id, long shop_id) {
		Statement statement = null;		
		Member member = null ;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM User NATURAL JOIN Member WHERE id_Member="+ member_id + "= (SELECT id_Member FROM Shop WHERE id_Shop = " + shop_id + ")" );

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				//int idShop= resultat.getInt(1);
				member = new Member(resultat.getLong(7),resultat.getString(2),resultat.getString(3),resultat.getString(4), resultat.getString(5), resultat.getString(6));
			} 

		}catch (SQLException e) { e.printStackTrace();} 
		return member;
	}
	public static Member getOwner(long shop_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static void delete(long shop_id) {
		// TODO Auto-generated method stub

	}



}

