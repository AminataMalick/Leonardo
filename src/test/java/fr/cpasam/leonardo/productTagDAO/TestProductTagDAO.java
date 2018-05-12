package fr.cpasam.leonardo.productTagDAO;

import java.util.ArrayList;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.tag.ProductTagDAO;
import fr.cpasam.leonardo.model.tag.Tag;
import fr.cpasam.leonardo.model.tag.TagDAO;

public class TestProductTagDAO {
	public static void main(String[] args) {
		
		/*
		// Test addTag
		long product_id = 1;
		long tag_id = 1;
		Product product = null ;
		product = ProductTagDAO.addTag(product_id, tag_id);
		System.out.println(product);
		*/
		
		/*
		//Test delete
		long tag_id = 1;
		ProductTagDAO.delete(tag_id);
		System.out.println("ok");
		*/
		
		/*
		// Test getTagsByProduct
		long id = 1 ;
		ArrayList<Tag> tags = new ArrayList<Tag>();
		tags = ProductTagDAO.getTagsByProduct(id);
		System.out.println(tags);
		*/
		
		/*
		//Test getProductsByTag
		long tag_id = 1 ;
		ArrayList<Product> products = new ArrayList<Product>() ;
		products = ProductTagDAO.getProductsByTag(tag_id);
		System.out.println(products);
		*/
		
		/*
		// Test create
		String keyword = "carotte rapée" ;
		ProductTag productTag = null ;
		productTag = ProductTagDAO.create(keyword);
		System.out.println(productTag);
		*/

	}

		
}
