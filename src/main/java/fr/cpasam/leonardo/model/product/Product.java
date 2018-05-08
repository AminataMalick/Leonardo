package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;


import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;


public class Product {
	
	long id;
	String name ;
	Shop provenance ;
	float unityPrice ;
	protected ArrayList<ProductTag> tags;

	public Product() {}
	
	public Product(String name, Shop provenance, float unityPrice, ArrayList<ProductTag> tags) {
		this.name = name;
		this.provenance = provenance;
		this.unityPrice = unityPrice;
		this.tags = tags;
	}
	
	/**
	 * Retourne l'id d'un produit
	 * @return id
	 */
	public long getProductId() {return id;}
	/**
	 * Retourne le nom d'un produit
	 * @return name
	 */
	public String getProductName() {return name;}
	/**
	 *Retourne la provenance d'un produit 
	 * @return provenance
	 */
	public Shop getProductProvenance() {return provenance;}
	/**
	 * Retourne le prix à l'unité d'un produit
	 * @return unityPrice
	 */
	public float getProductUnityPrice() {return unityPrice;}
	/**
	 * Retourne les mots clés d'un produit
	 * @return tags
	 */
	public ArrayList<ProductTag> getProductTags() {return tags;}
	
	
	/**
	 * Met à jour l'id d'un produit
	 * @param id
	 */
	public void setProductId(long id) {this.id=id;}
	/**
	 * Met à jour le nom d'un produit
	 * @param name
	 */
	public void setProductName( String name) {this.name = name;}
	/**
	 * Met à jour la provenance d'un produit
	 * @param provenance
	 */
	public void setProductProvenance(Shop provenance) {this.provenance = provenance;}
	/**
	 * Met à jour le prix à l'unité d'un produit
	 * @param unityPrice
	 */
	public void setProductUnityPrice(float unityPrice) {this.unityPrice = unityPrice;}
	/**
	 * Met à jour les mots clés d'un produit
	 * @param tags
	 */
	public void setProductTags(ArrayList<ProductTag> tags) {this.tags = tags;}
	
}
