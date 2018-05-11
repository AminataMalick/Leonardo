package fr.cpasam.leonardo.model.tag;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.product.Product;

public class ProductTag extends Tag{
	
	private List<Product> products;
	
	public ProductTag() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public ProductTag(long id, String keyword) {
		super(id,keyword);
		this.products = new ArrayList<Product>();
	}
	
	/**
	 * Retourne le produit d'un mot clé de produit
	 * @return produit
	 */
	public List<Product> getProducts() {return products;}
	
	public void addProduct(Product product) {
		this.products.add(product); 
	}
	
	public void deleteProduct(Product p) {
		for(Product i : products) {
			if(i.getId() == p.getId()) {
				products.remove(i);
			}
		}
	}
}
