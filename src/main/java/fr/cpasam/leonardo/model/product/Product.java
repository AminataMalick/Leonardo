package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;


public class Product {
	private static long cnt = 100;
	public static long getCnt() {
		return cnt++;
	}
	
	
	long id;
	String name ;
	Long provenance ; // shop_id
	float unityPrice ;
	protected ArrayList<ProductTag> tags;

	public Product() {}
	
	public Product(long id, String name, long id_shop, float unityPrice, ArrayList<ProductTag> tags) {
		this.id = id;
		this.name = name;
		this.provenance = id_shop;
		this.unityPrice = unityPrice;
		this.tags = tags;
	}
	
	/**
	 * Retourne l'id d'un produit
	 * @return id
	 */
	public long getId() {return id;}
	/**
	 * Retourne le nom d'un produit
	 * @return name
	 */
	public String getName() {return name;}
	/**
	 *Retourne la provenance d'un produit 
	 * @return provenance
	 */
	public long getProvenance() {return provenance;}
	/**
	 * Retourne le prix à l'unité d'un produit
	 * @return unityPrice
	 */
	public float getUnityPrice() {return unityPrice;}
	/**
	 * Retourne les mots clés d'un produit
	 * @return tags
	 */
	public ArrayList<ProductTag> getTags() {return tags;}
	
	
	/**
	 * Met à jour l'id d'un produit
	 * @param id
	 */
	public void setId(long id) {this.id=id;}
	/**
	 * Met à jour le nom d'un produit
	 * @param name
	 */
	public void setName( String name) {this.name = name;}
	/**
	 * Met à jour la provenance d'un produit
	 * @param provenance
	 */
	public void setProvenance(Shop provenance) {this.provenance = provenance.id();}
	/**
	 * Met à jour le prix à l'unité d'un produit
	 * @param unityPrice
	 */
	public void setUnityPrice(float unityPrice) {this.unityPrice = unityPrice;}
	/**
	 * Met à jour les mots clés d'un produit
	 * @param tags
	 */
	public void setTags(ArrayList<ProductTag> tags) {this.tags = tags;}
	
}
