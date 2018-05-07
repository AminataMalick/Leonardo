package fr.cpasam.leonardo.model.tag;

import fr.cpasam.leonardo.model.product.Product;

public class ProductTag extends Tag{
	protected Product product;

	public ProductTag() {}

	public ProductTag(Product product) {
		this.product = product;
	}
	
	public Product GetProductTagProduct() {return product;}
	public void SetProductTagProduct(Product product) {this.product = product;}
}
