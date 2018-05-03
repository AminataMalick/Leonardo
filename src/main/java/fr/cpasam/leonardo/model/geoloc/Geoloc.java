package fr.cpasam.leonardo.model.geoloc;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="geolocalisations")
public class Geoloc {

	
	@Id
	@GeneratedValue
	@Column(name="GEOLOC_ID")
	protected long id;
	
	@Column(name="LAT")
	protected long lat;
	
	@Column(name="LONG")
	protected long longit;
}
