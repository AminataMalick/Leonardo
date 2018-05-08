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

	/**
	 * Retourne l'id d'une geolocalisation
	 * @return id
	 */
	public long getGeolocId () {return id;}
	/**
	 * Retourne la latitude d'une geolocalisation
	 * @return lat
	 */
	public long getGeolocLat () {return lat;}
	/**
	 * Retourne la longitude d'une geolocalisation
	 * @return longit
	 */
	public long getGeolocLongit () {return longit;}
	/**
	 * Retourne la liste des membres d'une geolocalisation
	 * @return members
	 */
	public List<Member> getGeolocMembers () {return members;}
	/**
	 * Retourne la liste des points de vente d'une geolocalisation
	 * @return retailpoints
	 */
	public List<RetailPoint> getGeolocRetailpoints () {return retailpoints;}
	
	
	/**
	 * Met à jour l'id d'une geolocalisation
	 * @param id
	 */
	public void setGeolocId (long id) {this.id=id;}
	/**
	 * Met à jour la latitude d'une geolocalisation
	 * @param lat
	 */
	public void setGeolocLat (long lat) {this.lat=lat;}
	/**
	 * Met à jour la longitude d'une geolocalisation
	 * @param longit
	 */
	public void setGeolocLongit (long longit) {this.longit= longit;}
	/**
	 * Met à jour la liste des membres d'une geolocalisation
	 * @param members
	 */
	public void setGeolocMembers (List<Member> members) {this.members= members;}
	/**
	 * Met à jour la liste des points de vente d'une geolocalisation
	 * @param retailpoints
	 */
	public void setGeolocRetailPoints(List<RetailPoint> retailpoints) {this.retailpoints=retailpoints;}
}
