package fr.cpasam.leonardo.shopDAO;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.shop.ShopDAO;

public class TestShopDAO {

	public static void main(String[] args) {
		
		List<Product> products = new ArrayList<Product>();	
		long shop_id = 1;
		
		products = ShopDAO.getProducts(shop_id);
		
		for(Product p : products) {
			System.out.println(p);
		}

	}

}
