package fr.cpasam.leonardo.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.synth.SynthSpinnerUI;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;
import fr.cpasam.leonardo.DAO.MemberDAO;
import fr.cpasam.leonardo.DAO.ShopDAO;
import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.Message;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.TextMessage;

public class ShopChatDAO extends DAOManager {


	/**
	 * Attribut de la classe ShopChatDAO representant un compteur pour générer un identifiant automatiquement
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
	 * Retourne tous les ShopsChats
	 * @return ArrayList<ShopChat>
	 */
	public static ArrayList<ShopChat> all() {
		ArrayList<ShopChat> shopChats = new ArrayList<ShopChat>();	
		Statement statement = null;		

		ShopChat shopChat = null ;
		Member member = null ;
		Shop shop = null ;

		try {
			statement = con.createStatement();

			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM ShopChat");

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération du membre */
				long member_id = resultat.getLong(3);
				member = MemberDAO.get(member_id);

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
			long chat_id = getCnt();
			statement = con.createStatement();

			/*Récupération id du membre*/
			long member_id = member.getId();

			/*Récupération id du shop*/
			long shop_id = shop.id();

			/* Insertion d'un shopChat */
			statement.executeUpdate("INSERT INTO ShopChat(id_Chat, id_Shop, id_Member)VALUES("+chat_id+","+shop_id+","+member_id+")");

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
	 * @return shopChat 
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 */
	public static ShopChat getByMemberAndShop(long member_id, long shop_id) throws ChatNotFoundException, UserNotFoundException {
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

			System.out.println("Retrievement of a chat with member : shop n°" + shop_id+" user n°"+member_id);
			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT id_Chat FROM ShopChat WHERE id_Shop = "+shop_id+" and id_Member = "+member_id);
			boolean cont = resultat.next() ;

			/* Récupération des données du résultat de la requête de lecture */
			if ( cont) {


				long chat_id = resultat.getLong("id_chat");
				shopChat= new ShopChat(chat_id, member, shop, getMessagesByChat(chat_id));

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
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 */
	public static ShopChat get(long shopchat_id) throws ChatNotFoundException, UserNotFoundException {
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

				long chat_id = resultat.getLong("id_chat");
				shopChat= new ShopChat(chat_id, member, shop,getMessagesByChat(chat_id));

			}

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChat;
	}


	/**
	 * Retourne la liste des ShopChat lié au membre donné
	 * @param user_id
	 * @return ArrayList<ShopChat> 
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 */
	public static List<Chat> getByMember(long user_id) throws ChatNotFoundException, UserNotFoundException {
		List<Chat> shopChats = new ArrayList<>();	


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
				long chat_id = resultat.getLong("id_chat");
				shopChat= new ShopChat(chat_id, member, shop, getMessagesByChat(chat_id) );
				shopChats.add(shopChat);
			}
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChats;
	}


	/**
	 * Retourne la liste des ShopChats liée à un shop donné
	 * @param shop_id
	 * @return ArrayList<ShopChat>
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 */
	public static ArrayList<ShopChat> getByShop(long shop_id) throws ChatNotFoundException, UserNotFoundException {
		ArrayList<ShopChat> shopChats = new ArrayList<ShopChat>();	

		Statement statement = null;		

		ShopChat shopChat = null ;
		Member member = null ;
		Shop shop = null ;

		try {
			statement = con.createStatement();

			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM ShopChat WHERE id_Shop = "+shop_id);

			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération du membre */
				long member_id = resultat.getLong(3);
				member = MemberDAO.get(member_id);

				/* Récupération du shop */
				shop = ShopDAO.get(shop_id);

				long chat_id = resultat.getLong("id_chat");
				shopChat= new ShopChat(chat_id, member, shop, getMessagesByChat(chat_id) );
				shopChats.add(shopChat);
			}

		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return shopChats;
	}


	/**
	 * Supprime un shopChat donné
	 * @param shopchat_id
	 */
	public static void delete(long shopchat_id) {
		Statement statement = null;
		try {
			statement = con.createStatement();
			statement.executeUpdate("DELETE FROM Message WHERE id_Chat="+shopchat_id);
			statement.executeUpdate("DELETE FROM ShopChat WHERE id_Chat="+shopchat_id);
		}catch (SQLException e) { e.printStackTrace();}
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return ;
	}

	/**
	 * Cherche l'identifiant maximum dans la table afin d'incrémenter celui-ci d'une unité et de  générer un nouvel identifiant automatiquement 
	 * @return retourne l'identifiant maximum de ShopChatDAO
	 */
	public static long getLastId() {
		Statement statement = null;
		long chat_id = 0;
		try {
			statement = con.createStatement();
			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT MAX(id_chat) FROM ShopChat");

			/* Récupération des données du résultat de la requête de lecture */
			if ( resultat.next() ) {
				/* Récupération du membre */
				chat_id= resultat.getLong(1);
			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chat_id;
	}

	/**
	 * Retourne tous les messages d'un chat à partir de son id
	 * @param chat_id 
	 * @return List of message
	 * @throws ChatNotFoundException 
	 * @throws UserNotFoundException
	 */
	public static List<Message> getMessagesByChat(long chat_id) throws ChatNotFoundException, UserNotFoundException{
		List<Message> messages = new ArrayList<>();	
		Statement statement = null;		
		TextMessage message = null;
		try {
			statement = con.createStatement();
			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Message WHERE id_chat = "+chat_id);
			/* Récupération des données du résultat de la requête de lecture */
			while ( resultat.next() ) {
				/* Récupération du membre */
				Member emiter = MemberDAO.get(resultat.getLong("id_Member"));
				if(emiter == null) throw new UserNotFoundException();
				/* Récupération du contenue du message */
				String content = resultat.getString("content_Message");
				/* Récupération de la date */ 
				String dateS = resultat.getString("date_Message");
				LocalDateTime date = LocalDateTime.parse(dateS,Message.dTF);
				message = new TextMessage<Member>(emiter, content, date);
				messages.add(message);
			}
		}catch (SQLException e) { e.printStackTrace();} 
		try { statement.close();
		} catch (SQLException e) { e.printStackTrace();}
		return messages;
	}
}
