package fr.cpasam.leonardo.model.shop;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import fr.cpasam.leonardo.model.geoloc.Geoloc;

@NamedQueries({
	@NamedQuery(
	name = RetailPoint.FIND_RETAIL_POINT_BY_ID,
	query = "from RetailPoint rp where rp.id = :retailPointId"
	),
	@NamedQuery(
	name = RetailPoint.FIND_ALL_RETAIL_POINTS,
	query = "from RetailPoint"
	),
	@NamedQuery(
	name = RetailPoint.DELETE_RETAIL_POINT,
	query = "delete from RetailPoint rp where rp.id = :retailPointId"
	)
})
@Entity
@Table(name="retail_points")
public class RetailPoint {

	public final static String FIND_RETAIL_POINT_BY_ID = "findRetailPointById";
	public final static String FIND_ALL_RETAIL_POINTS = "findAllRetailPoints";
	public final static String DELETE_RETAIL_POINT = "deleteRetailPoint";
	
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
	private Geoloc geoloc;
	
		
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SHOP_ID")
	protected Shop shop;
	
	@Column (name = "SHOPS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "retailpoint")
	List<Shop> shops ;

	public RetailPoint() {}
	
	public RetailPoint(@NotNull @Length(min = 3) String name, Geoloc geoloc, Shop shop, List<Shop> shops) {
		this.name = name;
		this.geoloc = geoloc;
		this.shop = shop;
		this.shops = shops;
	}
	
	
}
