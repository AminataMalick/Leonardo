package fr.cpasam.leonardo.model.shop;

import java.util.List;

import fr.cpasam.leonardo.model.geoloc.Geoloc;

public class RetailPoint {

	protected long id;
	protected String name;
	private Geoloc geoloc;
	protected Shop shop;
	List<Shop> shops ;

	public RetailPoint() {}
	
	public RetailPoint(String name, Geoloc geoloc, Shop shop, List<Shop> shops) {
		this.name = name;
		this.geoloc = geoloc;
		this.shop = shop;
		this.shops = shops;
	}
	/**
	 * Retourne l'id d'un point de vente
	 * @return id
	 */
	public long GetRetailPointId() {return id;}
	public String GetRetailPointName() {return name;}
	public Geoloc GetRetailPointGeoloc() {return geoloc;}
	public Shop GetRetailPointShop() {return shop;}
	public List<Shop> GetRetailPointShops() {return shops;}
	
	
	/**
	 * Met à jour l'id d'un point de vente
	 * @param id
	 */
	public void SetRetailPointId(long id) {this.id=id;}
	/**
	 * Met à jour le nom d'un point de vente
	 * @param name
	 */
	public void SetRetailPointName(String name) {this.name= name;}
	/**
	 * Met à jour la geolocalisation d'un point de vente
	 * @param geoloc
	 */
	public void SetRetailPointGeoloc(Geoloc geoloc) {this.geoloc=geoloc;}
	/**
	 * Met à jour la boutique d'un point de vente
	 * @param shop
	 */
	public void SetRetailPointShop(Shop shop) {this.shop= shop;}
	/**
	 * Met à jour les boutiques d'un point de vente
	 * @param shops
	 */
	public void SetRetailPointShops(List<Shop> shops) {this.shops=shops;}
	
	
}
