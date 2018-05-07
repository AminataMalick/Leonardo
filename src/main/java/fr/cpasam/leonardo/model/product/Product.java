package fr.cpasam.leonardo.model.product;

import java.util.ArrayList;


import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.tag.ProductTag;


public class Product {
	
	long id;
	String name ;
	Shop provenance ;
	float unityPrice ;
	protected Shop shop;
	protected ArrayList<ProductTag> tags;

	public Product() {}
	
	public Product(String name, Shop provenance, float unityPrice, Shop shop, ArrayList<ProductTag> tags) {
		this.name = name;
		this.provenance = provenance;
		this.unityPrice = unityPrice;
		this.shop = shop;
		this.tags = tags;
	}
	
	public long GetProductId() {return id;}
	public String GetProductName() {return name;}
	public Shop GetProductProvenance() {return provenance;}
	public float GetProductUnityPrice() {return unityPrice;}
	public Shop GetProductShop() {return shop;}
	public ArrayList<ProductTag> GetProductTags() {return tags;}
	
	
	public void SetProductId(long id) {this.id=id;}
	public void SetProductName( String name) {this.name = name;}
	public void SetProductProvenance(Shop provenance) {this.provenance = provenance;}
	public void SetProductUnityPrice(float unityPrice) {this.unityPrice = unityPrice;}
	public void SetProductShop(Shop shop) {this.shop = shop;}
	public void SetProductTags(ArrayList<ProductTag> tags) {this.tags = tags;}
	
}
