package fr.cpasam.leonardo.shopDAO;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;

public class TestShopDAO {

	public static void main(String[] args) {
		
		// Test all
		List<Product> products = new ArrayList<Product>();	
		long shop_id = 1;
		products = ShopDAO.getProducts(shop_id);
		System.out.println(products);
		
		// Test get
		Shop shop = null ;
		shop = ShopDAO.get(shop_id);
		System.out.println(shop) ;
		
		/*
		// Test create
		shop = null ;
		long member_id = 1 ;
		shop = ShopDAO.createShop("Le Shop de Celine", "venez chez moi", member_id) ;
		System.out.println(shop) ;
		*/

		// Test update
		shop = null ;
		long member_id = 1 ;
		shop_id = 10 ;
		shop = ShopDAO.updateShop(shop_id, "Le Petit shop", "tchoin tchoin", member_id);
		System.out.println(shop) ;

		// Test getProducts
		shop_id = 1 ;
		List<Product> products1 = new ArrayList<Product>() ;
		products1 = ShopDAO.getProducts(shop_id);
		System.out.println(products1) ;
		
		// Test getMember
		Member member = null ;
		member_id = 3;
		member = ShopDAO.getMember(member_id, shop_id);
		System.out.println(member) ;
		
		// Test getOwner
		member = null ;
		member = ShopDAO.getOwner(shop_id);
		System.out.println(member) ;
		
		// Test delete
		shop_id = 10 ;
		ShopDAO.delete(shop_id);
		System.out.println("ok") ;

		
	}

}
