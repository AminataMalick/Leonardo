package fr.cpasam.leonardo.model.tag;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.product.Product;

@NamedQueries({
	@NamedQuery(
	name = ProductTag.FIND_PRODUCT_TAG_BY_ID,
	query = "from ProductTag pt where pt.id = :tagId"
	),
	@NamedQuery(
	name = ProductTag.FIND_ALL_PRODUCT_TAGS,
	query = "from ProductTag"
	)
})
@Entity
@Table(name="product_tags")
@PrimaryKeyJoinColumn(name="TAG_ID")
public class ProductTag extends Tag{
	public final static String FIND_PRODUCT_TAG_BY_ID = "findProductTagById";
	public final static String FIND_ALL_PRODUCT_TAGS = "findAllProductTags";
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	protected Product product;

	public ProductTag() {}

	public ProductTag(Product product) {
		this.product = product;
	}
	
}
