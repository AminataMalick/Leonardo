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
	name = ProductTag.FIND_TAG_BY_ID,
	query = "from ProductTag pt where pt.TAG_ID = :tagId"
	),
	@NamedQuery(
	name = "findAllTags",
	query = "from ProductTag"
	)
})
@Entity
@Table(name="product_tags")
@PrimaryKeyJoinColumn(name="TAG_ID")
public class ProductTag extends Tag{
	public final static String FIND_TAG_BY_ID = "findTagById";
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="PRODUCT_ID")
	protected Product product;

	public ProductTag(Product product) {
		this.product = product;
	}
}
