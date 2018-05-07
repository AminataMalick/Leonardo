package fr.cpasam.leonardo.model.geoloc;

import java.util.List;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.shop.RetailPoint;
import fr.cpasam.leonardo.model.user.Member;

public class Geoloc {

	protected long id;
	
	protected long lat;
	
	protected long longit;
	
	List<Member> members ;
	
	List<RetailPoint> retailpoints ;

	public Geoloc() {}
	
	public Geoloc(long lat, long longit, List<Member> members, List<RetailPoint> retailpoints) {
		this.lat = lat;
		this.longit = longit;
		this.members = members;
		this.retailpoints = retailpoints;
	}
	
	
}
