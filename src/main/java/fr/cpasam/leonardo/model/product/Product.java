package fr.cpasam.leonardo.model.product;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import antlr.collections.List;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.*;

import java.util.ArrayList;

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
	
	
	@Column (name = "TAGS" )
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "product")
	protected ArrayList<ProductTag> tags;
	
	}
