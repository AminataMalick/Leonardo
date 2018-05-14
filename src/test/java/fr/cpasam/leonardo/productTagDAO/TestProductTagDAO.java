package fr.cpasam.leonardo.productTagDAO;

import java.util.ArrayList;

import fr.cpasam.leonardo.DAO.ProductTagDAO;
import fr.cpasam.leonardo.DAO.TagDAO;
import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.tag.Tag;

public class TestProductTagDAO {
	public static void main(String[] args) {
		
		/*
		// Test estProductTag
		long product_id = 1;
		long tag_id = 1 ;
		boolean b = ProductTagDAO.estProductTag(product_id, tag_id);
		System.out.println(b);
		*/
		
		/*
		// Test addTag
		long product_id = 5;
		long tag_id = 1;
		ProductTagDAO.addTag(product_id, tag_id);
		System.out.println("ok");
		*/
		
		/*
		// Test addTags
		ArrayList<Tag> tags = new ArrayList<Tag>() ;
		Tag tag = TagDAO.getTagByID(1);
		Tag tag2 = TagDAO.getTagByID(2);
		Tag tag3 = TagDAO.getTagByID(3);
		tags.add(tag);
		tags.add(tag2);
		tags.add(tag3);
		long product_id = 100;
		ProductTagDAO.addTags(product_id, tags);
		System.out.println("ok");
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
