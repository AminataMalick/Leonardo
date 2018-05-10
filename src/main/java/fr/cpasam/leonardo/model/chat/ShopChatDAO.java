package fr.cpasam.leonardo.model.chat;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.DAOManager;

public class ShopChatDAO extends DAOManager {

	public static ShopChat create(Member m, Shop s) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Récupère le chat associé à un membre et à une boutique
	 * @param memberID
	 * @param shopId
	 * @return 
	 */
	public static Chat getByMemberAndShop(Long memberID, Long shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	public static ShopChat get(long shopchat_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
