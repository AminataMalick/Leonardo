package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;

public class ShopChat extends Chat<Member, Shop> {

	public ShopChat(Member m, Shop s) {
		super(m, s);
	}
	
	public static Chat getChatByUsers(Long memberID, Long shopId) {
		
		
		// Faire une requête pour rechercher si un chat existe entre user1 et le user2
		ChatDAO cd = new ChatDAO();
		Chat c = cd.getByMemberAndShop(memberID, shopId);
		
		// Si le chat n'existe pas, le créer
		
		MemberDAO md = new MemberDAO();
		Member m = md.get(memberID);
		
		ShopDAO sd = new ShopDAO();  
		Shop s = sd.get(shopId);
		
		ShopChat nwChat = new ShopChat(m ,s );
				
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


}
