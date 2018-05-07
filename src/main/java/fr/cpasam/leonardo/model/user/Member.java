package fr.cpasam.leonardo.model.user;



import java.util.List;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

public class Member extends User{

	private Geoloc geoloc;
	
	private List<Shop> shops ;
	
	private List<Recommandation> recommandations;

	public Member() {}

	public Member(Geoloc geoloc, List<Shop> shops, List<Recommandation> recommandations) {
		super();
		this.geoloc = geoloc;
		this.shops = shops;
		this.recommandations = recommandations;
	}
}
