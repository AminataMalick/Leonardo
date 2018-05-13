package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.errors.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;

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
				
		// Ajouter le chat au membre et à la boutique
		
		m.addChat(nwChat);
		s.addChat(nwChat);
		
		// Retourner le chat
		return nwChat;
	}	
	
	public static ShopChat getByShop(long id2, Long shopId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Member getMember() {
		
		return super.getEntity1();
	}
	
	public Shop getShop() {
		
		return super.getEntity2();
	}


}
