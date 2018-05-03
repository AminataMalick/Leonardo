package fr.cpasam.leonardo.model.tag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.product.Product;

@Entity
@Table(name="product_tags")
@PrimaryKeyJoinColumn(name="TAG_ID")
public class ProductTag extends Tag{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	protected Product product;
	
}
