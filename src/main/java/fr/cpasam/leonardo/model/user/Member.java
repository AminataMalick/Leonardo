package fr.cpasam.leonardo.model.user;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.shop.Shop;

@Entity
@Table(name="members")
public class Member extends User{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GEOLOC_ID")
	private Geoloc geoloc;
	
	@Column (name = "SHOPS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "retailpoint")
	List<Shop> shops ;
	
}
