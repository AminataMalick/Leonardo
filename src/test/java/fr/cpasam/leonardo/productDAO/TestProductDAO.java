package fr.cpasam.leonardo.productDAO;

import java.util.ArrayList;

import fr.cpasam.leonardo.DAO.ProductDAO;
import fr.cpasam.leonardo.model.product.Product;

public class TestProductDAO {

	public static void main(String[] args) {
	
		// Test get
		Product product = null ;
		long product_id = 1;
		product = ProductDAO.get(product_id );
		System.out.println(product);
		
		
		// Test all
		ArrayList<Product> products = new ArrayList<Product>() ;
		products = ProductDAO.all();
		System.out.println(products);
		
		/*
		// Test create
		product = null ;
		long shop_id = 1 ;
		float UnityPrice = 5 ;
		product = ProductDAO.create("nave", shop_id, UnityPrice);
		System.out.println(product);
		*/
		
		// Test update
		product = null ;
		long shop_id = 1 ;
		long product_id2 = 100 ;
		float UnityPrice = 5 ;

		product = ProductDAO.update(product_id2, "nave2", shop_id, UnityPrice);
		System.out.println(product);

		// Test delete
		ProductDAO.delete(product_id2);
		System.out.println("ok");


	}
}
