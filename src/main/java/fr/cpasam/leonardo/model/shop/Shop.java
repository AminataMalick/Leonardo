package fr.cpasam.leonardo.model.shop;

import java.util.ArrayList;
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

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.user.Member;

@NamedQueries({
	@NamedQuery(
	name = "findShopByChatId",
	query = "from Shop s where s.SHOP_ID = :shopId"
	),
	@NamedQuery(
	name = "findAllShops",
	query = "from Shop"
	)
})
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
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RETAILPOINT_ID")
	private RetailPoint retailPoint;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="MEMBER_ID")
	private Member member;
	
	@Column (name = "PRODUCTS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "shop")
	List<Product> products ;

	public Shop(@NotNull @Length(min = 3) String name, String description, List<RetailPoint> retailPoints,
			RetailPoint retailPoint, Member member, List<Product> products) {
		this.name = name;
		this.description = description;
		this.retailPoints = retailPoints;
		this.retailPoint = retailPoint;
		this.member = member;
		this.products = products;
	}
	
}
