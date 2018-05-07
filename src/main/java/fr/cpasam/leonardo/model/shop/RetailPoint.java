package fr.cpasam.leonardo.model.shop;

import java.util.List;

import org.hibernate.validator.constraints.Length;

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
	
	
}
