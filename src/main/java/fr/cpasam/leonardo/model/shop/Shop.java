package fr.cpasam.leonardo.model.shop;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "shops")
public class Shop {
	
	
	@Id
	@GeneratedValue
	@Column(name = "SHOP_ID")
	private long id;
	
	
	@NotNull
	@Column(name = "SHOP_NAME")
	@Length(min=3)
	protected String name;
	
	
	@Column(name = "DESCRIPTION")
	protected String description;


	@OneToMany(fetch = FetchType.EAGER, mappedBy= "shop")
	protected List<RetailPoint> retailPoints = new ArrayList<>();
}
