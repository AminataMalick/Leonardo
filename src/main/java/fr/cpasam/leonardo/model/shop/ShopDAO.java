package fr.cpasam.leonardo.model.shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import fr.cpasam.leonardo.model.user.Member;

public class ShopDAO {

	public static Shop get(Long shopId) {
								
		try {
			Shop shop;
			Statement statement = connexion.createStatement();
	        /* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop WHERE idShop="+ shopId);
	            
	        	/* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	//int idShop= resultat.getInt(1);
	            	shop= new Shop(resultat.getString(2),resultat.getString(3),null,shop.getMember());
	          } return shop;
		}catch (SQLException e) { e.printStackTrace();} 
	}
	
	public static List<Shop> all() {
								
		try {
			List<Shop> shops=new ArrayList<Shop>();
			Statement statement = connexion.createStatement();
	        /* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop ");
	           /* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	//int idShop= resultat.getInt(1);
	            	Shop shop= new Shop(resultat.getString(2),resultat.getString(3),null,shop.getMember());
	            	shops.add(shop);
	            } return shops;
		}catch (SQLException e) { e.printStackTrace();} 
		
	}
}

