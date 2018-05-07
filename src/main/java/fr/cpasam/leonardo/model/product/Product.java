package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;


import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;


	public class Product {
	long id;
	
	String name ;

	Shop provenance ;
	
	float unityPrice ;
	

	protected Shop shop;

	
	protected ArrayList<ProductTag> tags;

	public Product() {}
	
	public Product(String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		this.name = name;
		this.provenance = provenance;
		this.unityPrice = unityPrice;
		this.shop = shop;
		this.tags = tags;
	}

	
	}
