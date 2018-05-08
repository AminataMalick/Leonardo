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
	public long getRetailPointId() {return id;}
	/**
	 *  Retourne le nom d'un point de vente
	 * @return name
	 */
	public String getRetailPointName() {return name;}
	/**
	 *  Retourne la géolocalisation d'un point de vente
	 * @return geoloc
	 */
	public Geoloc GetRetailPointGeoloc() {return geoloc;}
	/**
	 *  Retourne la boutique d'un point de vente
	 * @return shop
	 */
	public Shop getRetailPointShop() {return shop;}
	/**
	 *  Retourne les boutiques d'un point de vente
	 * @return shops
	 */
	public List<Shop> getRetailPointShops() {return shops;}
	
	
	/**
	 * Met à jour l'id d'un point de vente
	 * @param id
	 */
	public void setRetailPointId(long id) {this.id=id;}
	/**
	 * Met à jour le nom d'un point de vente
	 * @param name
	 */
	public void setRetailPointName(String name) {this.name= name;}
	/**
	 * Met à jour la geolocalisation d'un point de vente
	 * @param geoloc
	 */
	public void setRetailPointGeoloc(Geoloc geoloc) {this.geoloc=geoloc;}
	/**
	 * Met à jour la boutique d'un point de vente
	 * @param shop
	 */
	public void setRetailPointShop(Shop shop) {this.shop= shop;}
	/**
	 * Met à jour les boutiques d'un point de vente
	 * @param shops
	 */
	public void setRetailPointShops(List<Shop> shops) {this.shops=shops;}
	
	
}
