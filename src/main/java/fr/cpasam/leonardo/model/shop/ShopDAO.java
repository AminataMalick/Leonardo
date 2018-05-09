package fr.cpasam.leonardo.model.shop;

import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.cj.api.jdbc.Statement;

public class ShopDAO {

	public static Shop get(Long shopId) {
		
		try {
			Statement statement = connexion.createStatement();
	        /* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Shop WHERE idShop="+ shopId+";" );
	            
	        	/* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	int idShop= resultat.getInt(1);
	                String nameShop = resultat.getString(2);
	                String descrShop = resultat.getString(3);
	            } 
		}catch (SQLException e) { e.printStackTrace();} 
			return null;
		}

	public static void getMember(long user_id) {
		// TODO Auto-generated method stub
		
	}

}
