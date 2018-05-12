package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.recommandation.RecommandationDAO;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Admin;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.model.user.UserDAO;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TestJDBC extends DAOManager {


	public static void main(String[] args) throws Exception {
	//User user = new Member(10,"Hanna","Ri","rihanna@loveuse.com","riri","kjhgff");
		
		// Tests de creation de 3 recommandations
		// RecommandationDAO.create(5, "cool", MemberDAO.get(3),ShopDAO.get(10));
		// RecommandationDAO.create(4, "bons produits", MemberDAO.get(6),ShopDAO.get(10));
		// RecommandationDAO.create(5, "vraiment trop bien", MemberDAO.get(9),ShopDAO.get(10));
		
		
		// Test de calcul de la moyenne d'un shop
		// System.out.println(RecommandationDAO.averageShop(10));
		
		
		// Test pour trouver une recommandation à l'aide de son identifiant
		// System.out.println(RecommandationDAO.get(12));
		
		// Test pour retourner toutes les recommandations liées à un shop
		System.out.println(RecommandationDAO.all(10).toString());
	
		// Test pour supprimer une recommandation
		// System.out.println(RecommandationDAO.delete(13));
		
	}
}