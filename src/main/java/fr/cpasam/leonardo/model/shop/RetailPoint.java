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
	
	public long GetRetailPointId() {return id;}
	public String GetRetailPointName() {return name;}
	public Geoloc GetRetailPointGeoloc() {return geoloc;}
	public Shop GetRetailPointShop() {return shop;}
	public List<Shop> GetRetailPointShops() {return shops;}
	
	
	public void SetRetailPointId(long id) {this.id=id;}
	public void SetRetailPointName(String name) {this.name= name;}
	public void SetRetailPointGeoloc(Geoloc geoloc) {this.geoloc=geoloc;}
	public void SetRetailPointShop(Shop shop) {this.shop= shop;}
	public void SetRetailPointShops(List<Shop> shops) {this.shops=shops;}
	
	
}
