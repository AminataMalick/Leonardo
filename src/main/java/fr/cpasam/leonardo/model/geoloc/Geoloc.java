package fr.cpasam.leonardo.model.geoloc;

import java.util.List;

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
	
	public long GetGeolocId () {return id;}
	public long GetGeolocLat () {return lat;}
	public long GetGeolocLongit () {return longit;}
	public List<Member> GetGeolocmMembers () {return members;}
	public List<RetailPoint> GetGeolocRetailpoints () {return retailpoints;}
	
	public void SetGeolocId (long id) {this.id=id;}
	public void SetGeolocLat (long lat) {this.lat=lat;}
	public void SetGeolocLongit (long longit) {this.longit= longit;}
	public void SetGeolocMembers (List<Member> members) {this.members= members;}
	public void SetGeolocRetailPoints(List<RetailPoint> retailpoints) {this.retailpoints=retailpoints;}
}
