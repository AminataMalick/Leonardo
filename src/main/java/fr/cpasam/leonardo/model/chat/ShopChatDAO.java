package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.utilities.DAOManager;
import fr.cpasam.leonardo.model.chat.ShopChat;

public class ShopChatDAO extends DAOManager {

	
	/**
	 * Création d'un ShopChat a partir d'un membre et d'un shop
	 * @param member
	 * @param shop
	 * @return ShopChat
	 */
	public static ShopChat create(Member member, Shop shop) {
		
		Statement statement = null;		
		ShopChat shopChat = null ;
		try {
			long chat_id = ShopChat.getCnt();
			statement = con.createStatement();
			
			/*Récupération id du membre*/
			long member_id = Member.getID(member);
			
			/*Récupération id du shop*/
			long shop_id = Shop.getID(shop);
			
			/* Insertion d'un shopChat */
			int res = statement.executeUpdate("INSERT INTO ShopChat(id_Chat, id_Shop, id_Member)VALUES("+chat_id+","+member_id+","+shop_id+")");

			/* Création shopChat */
			shopChat = new ShopChat(chat_id, member, shop );
			 
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChat;
	}	

	/**
	 * Récupère le chat associé à un membre et à une boutique
	 * @param memberID
	 * @param shopId
	 * @return 
	 */
	public static ShopChat getByMemberAndShop(long member_id, long shop_id) {
		Statement statement = null;		

		ShopChat shopChat = null ;
		Member member = null ;
		Shop shop = null ;
		
		try {
			statement = con.createStatement();

			/* Récupération du membre */
			member = MemberDAO.get(member_id);
			
			/* Récupération du shop */
			shop = ShopDAO.get(shop_id);

			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT id_Chat FROM ShopChat WHERE id_Shop = "+shop_id+" and id_Member = "+member_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				shopChat= new ShopChat(resultat.getLong(1), member, shop);
			}
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChat;
	}
	
	
	/**
	 * Retourne le ShopChat lié a l'idée donné
	 * @param shopchat_id
	 * @return ShopChat
	 */
	public static ShopChat get(long shopchat_id) {
		Statement statement = null;		

		ShopChat shopChat = null ;
		Member member = null ;
		Shop shop = null ;
		
		try {
			statement = con.createStatement();

			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM ShopChat WHERE id_Chat = "+shopchat_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération du membre */
				long member_id = resultat.getLong(3);
				member = MemberDAO.get(member_id);
				
				/* Récupération du shop */
				long shop_id = resultat.getLong(2);
				shop = ShopDAO.get(shop_id);
				
				shopChat= new ShopChat(resultat.getLong(1), member, shop);
			}
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChat;
	}

	
	/**
	 * Retourne la liste de ShopChat lié au membre donné
	 * @param user_id
	 * @return ArrayList<ShopChat>
	 */
	public static ArrayList<ShopChat> getByMember(long user_id) {
		ArrayList<ShopChat> shopChats = new ArrayList<ShopChat>();	

		Statement statement = null;		

		ShopChat shopChat = null ;
		Member member = null ;
		Shop shop = null ;
		
		try {
			statement = con.createStatement();

			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM ShopChat WHERE id_Member = "+user_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération du membre */
				member = MemberDAO.get(user_id);
				
				/* Récupération du shop */
				long shop_id = resultat.getLong(2);
				shop = ShopDAO.get(shop_id);
				
				shopChat= new ShopChat(resultat.getLong(1), member, shop);
				shopChats.add(shopChat);
			}
			
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChats;
	}

}
