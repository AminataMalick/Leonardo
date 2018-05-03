package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;


	@NamedQueries({
		@NamedQuery(
		name = "findProductsById",
		query = "from Product p where p.PRODUCT_ID = :productId"
		),
		@NamedQuery(
		name = "findAllProducts",
		query = "from Product"
		)
	})
	@Entity 
	@Table(name = "products")
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

	
	@Column (name = "TAGS" )
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "product")
	protected ArrayList<ProductTag> tags;


	public Product(String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		this.name = name;
		this.provenance = provenance;
		this.unityPrice = unityPrice;
		this.shop = shop;
		this.tags = tags;
	}

	
	}
