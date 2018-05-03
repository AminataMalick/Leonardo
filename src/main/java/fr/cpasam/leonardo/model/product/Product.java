package fr.cpasam.leonardo.model.product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SHOP_ID")
	private Shop shop;
	
	}
