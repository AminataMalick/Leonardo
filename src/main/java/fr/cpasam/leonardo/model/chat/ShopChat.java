package fr.cpasam.leonardo.model.chat;

import java.util.List;

import fr.cpasam.leonardo.DAO.MemberDAO;
import fr.cpasam.leonardo.DAO.ShopChatDAO;
import fr.cpasam.leonardo.DAO.ShopDAO;
import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;

public class ShopChat extends Chat<Member, Shop> {

	/**
	 * Constructeur du ShopChat
	 * @param long
	 * @param member
	 * @param shop
	 */
	public ShopChat(long id, Member m, Shop s) {
		super(id, m, s);
	}
	

	public ShopChat(long chat_id, Member member, Shop shop, List<Message> messages) {
		super(chat_id, member, shop,messages);
	}


	public static ShopChat getChatByUsers(Long memberID, Long shopId) throws ChatNotFoundException, UserNotFoundException {
		
		
		// Faire une requête pour rechercher si un chat existe entre user1 et le user2
		ShopChat c = ShopChatDAO.getByMemberAndShop(memberID, shopId);
		
		// Si le chat n'existe pas, le créer
		Member m = MemberDAO.get(memberID);
		
		Shop s = ShopDAO.get(shopId);
		
		ShopChat nwChat = ShopChatDAO.create(m,s);
				
		// Retourner le chat
		return nwChat;
	}	
	
	/**
	 * Retourne l'entité 1
	 * @return entity1
	 */
	public Member getMember() {
		
		return super.getEntity1();
	}
	
	/**
	 * Retourne l'entité 2
	 * @return entity2
	 */
	public Shop getShop() {
		
		return super.getEntity2();
	}


}
