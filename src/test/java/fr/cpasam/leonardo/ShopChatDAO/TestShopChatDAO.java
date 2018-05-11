package fr.cpasam.leonardo.ShopChatDAO;

import java.util.ArrayList;

import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.ShopChatDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;

public class TestShopChatDAO {

	public static void main(String[] args) {
		/*
		// Test create
		Member member = null ;
		member =MemberDAO.create("jacques", "deCompostel", "ja@de", "jade");
		
		Shop shop = null ;
		long member_id = 106 ;
		shop = ShopDAO.createShop("Le Shop de Celine", "venez chez moi", member_id) ;
		
		ShopChat shopchat = null ;
		
		shopchat = ShopChatDAO.create(member, shop);
		System.out.println(shopchat);
		*/
		
		/*
		// Test getByMemberAndShop
		long member_id = 1;
		long shop_id = 1 ;
		ShopChat shopChat = null ;
		shopChat = ShopChatDAO.getByMemberAndShop(member_id, shop_id);
		System.out.println(shopChat);
		*/
		
		/*
		// Test get
		long shopchat_id = 1;
		ShopChat shopChat = null ;
		shopChat = ShopChatDAO.get(shopchat_id);
		System.out.println(shopChat);
		*/
		
		/*
		// Test getByMember
		long member_id = 1 ;
		ArrayList<ShopChat> shopchats = new ArrayList<ShopChat>() ;
		shopchats = ShopChatDAO.getByMember(member_id);
		System.out.println(shopchats);
		*/
	}
	
}
