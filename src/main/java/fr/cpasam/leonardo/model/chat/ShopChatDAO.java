package fr.cpasam.leonardo.model.chat;

import java.util.ArrayList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;
import fr.cpasam.leonardo.model.chat.ShopChat;

public class ShopChatDAO extends DAOManager {

	public static ShopChat create(Member m, Shop s) {

		long id = ShopChat.getCnt();
		return null;
	}	

	/**
	 * Récupère le chat associé à un membre et à une boutique
	 * @param memberID
	 * @param shopId
	 * @return 
	 */
	public static ShopChat getByMemberAndShop(long memberID, long shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ShopChat get(long shopchat_id) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ArrayList<ShopChat> getByMember(long user_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
