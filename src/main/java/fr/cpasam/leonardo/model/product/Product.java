package fr.cpasam.leonardo.model.product;

import javax.persistence.Entity;
import javax.persistence.Id;

import fr.cpasam.leonardo.model.shop.Shop;

import javax.persistence.Column;



	@Entity (name = "product")
	public class Product {
	
	@Id
	@Column (name ="PRODUCT_ID")
	int id;
	
	@Column (name = "PRODUCT_NAME")
	String name ;

	@Column (name = "PROVENANCE")
	Shop provenance ;
	
	@Column (name = "UNITYPRICE")
	float unityPrice ;
	
	}
