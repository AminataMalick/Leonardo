package fr.cpasam.leonardo.model.tag;

import fr.cpasam.leonardo.model.product.Product;

public class ProductTag extends Tag{
	protected Product product;

	public ProductTag() {}

	public ProductTag(Product product) {
		this.product = product;
	}
	/**
	 * Retourne le produit d'un mot clé de produit
	 * @return produit
	 */
	public Product getProduct() {return product;}
	
	/**
	 * Met à jour le produit d'un mot clé de produit
	 * @param product
	 */
	public void setProduct(Product product) {this.product = product;}
}
