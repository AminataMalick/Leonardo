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
