package fr.cpasam.leonardo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

import fr.cpasam.leonardo.model.chat.Message;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.TextMessage;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TextMessageDAO extends DAOManager{

	private static long cnt;
	/**
	 * Retourne le prochain id disponible
	 * @return long
	 */
	public static long getCnt() {
		return cnt++;
	}


	static {cnt = getLastId()+1;}

	
	public static TextMessage<Member> create(Member member, ShopChat chat, String content) {
		
		TextMessage<Member> message = null ;
		Statement stmt = null;
		try {
			
			stmt = con.createStatement();
			long id_Message = getCnt();

			LocalDateTime dateTime = LocalDateTime.now();
			String date_Message = Message.dTF.format(dateTime);
			System.out.println(date_Message);		
			String content_Message = content;
			
			/*Récupération id du membre*/
			long id_Member = member.getId();
					
			/*Récupération id du shop*/
			long id_Chat =  chat.id();

			
			/* Insertion d'un shopChat */
			int res = stmt.executeUpdate("INSERT INTO Message(id_Message, date_Message, content_Message, id_Member, id_Chat )VALUES("
					+ id_Message+", '"
					+date_Message+"', \""
					+content_Message+"\","
					+id_Member+","
					+id_Chat +")");

			/* Création shopChat */
			message = new TextMessage<Member>(member,content,dateTime);


		}catch (SQLException e) { e.printStackTrace();} 
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return message;
		
		
	}


	/**
	 * Retourne le dernier id rentré
	 * @return
	 */
	private static long getLastId() {
		Statement statement = null;
		long message_id = 0;
		try {
			statement = con.createStatement();
			/* Récupération du ShopChat */
			ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Message) FROM Message");

			/* Récupération des données du résultat de la requête de lecture */
			if ( resultat.next() ) {
				/* Récupération du membre */
				message_id= resultat.getLong(1);


			}
		}catch (SQLException e) { 
			e.printStackTrace();
		}
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return message_id;
	}
	
}
