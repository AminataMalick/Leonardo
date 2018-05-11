package fr.cpasam.leonardo.shopTest;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;

public class TestShop {
	public static void main(String[] args) {
		Shop shop = null ;
		shop = ShopDAO.createShop("celinette", "celine la tchoin", 1);
		System.out.println(shop);

	}
}
