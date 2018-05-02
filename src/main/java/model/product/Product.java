package model.product;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Column;



	@Entity (name = "product")
	public class Product {
	
	@Id
	@Column (name ="ID")
	int id;
	
	@Column (name = "NAME")
	String name ;

	@Column (name = "PROVENANCE")
	Shop provenance ;
	
	@Column (name = "UNITYPRICE")
	float unityPrice ;
	
	}
