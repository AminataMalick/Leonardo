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
		name = Product.FIND_PRODUCT_BY_ID,
		query = "from Product p where p.id = :productId"
		),
		@NamedQuery(
		name = Product.FIND_ALL_PRODUCTS,
		query = "from Product"
		),
		@NamedQuery(
		name = Product.FIND_PRODUCT_BY_TAG,
		query = "from Product p join p.tags where p.tags.keyword = :kw" //CETTE REQUETE EST FAUSSE
		)
	})
	@Entity 
	@Table(name = "products")
	public class Product {
	
	public static final String FIND_PRODUCT_BY_ID = "findProductById";
	public static final String FIND_ALL_PRODUCTS = "findAllProducts";
	public static final String FIND_PRODUCT_BY_TAG = "findProductByTag";
		
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

	public Product() {}
	
	public Product(String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		this.name = name;
		this.provenance = provenance;
		this.unityPrice = unityPrice;
		this.shop = shop;
		this.tags = tags;
	}

	
	}
