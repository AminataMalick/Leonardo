package fr.cpasam.leonardo.model.user;



import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

@NamedQueries({
	@NamedQuery(
	name = "findMemberById",
	query = "from Member m where m.USER_ID = :memberId"
	),
	@NamedQuery(
	name = "findAllMembers",
	query = "from Member"
	)
})
@Entity
@Table(name="members")
public class Member extends User{

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GEOLOC_ID")
	private Geoloc geoloc;
	
	@Column (name = "SHOPS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "retailpoint")
	private List<Shop> shops ;
	
	@Column(name="RECOMMANDATION_ID")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "recommandations")
	private List<Recommandation> recommandations;

	public Member(Geoloc geoloc, List<Shop> shops, List<Recommandation> recommandations) {
		this.geoloc = geoloc;
		this.shops = shops;
		this.recommandations = recommandations;
	}

	public Member() {}
}
