package fr.cpasam.leonardo.model.shop;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name="retailpoints")
public class RetailPoint {

	@Id
	@GeneratedValue
	@Column(name="RETAILPOINT_ID")
	protected long id;
	
	@NotNull
	@Column(name = "NAME")
	@Length(min=3)
	protected String name;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GEOLOC_ID")
	@Column(name = "GEOLOCALISATION")
	protected Geoloc geolocation;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SHOP_ID")
	protected Shop shop;
}
