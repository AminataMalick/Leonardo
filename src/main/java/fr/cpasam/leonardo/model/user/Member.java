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
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

@NamedQueries({
	@NamedQuery(
	name = Member.FIND_MEMBER_BY_ID,
	query = "from Member m where m.id = :memberId"
	),
	@NamedQuery(
	name = Member.FIND_ALL_MEMBERS,
	query = "from Member"
	),
	@NamedQuery(
	name = Member.DELETE_MEMBER,
	query = "delete from Member m where m.id = :memberId"
	)
})
@Entity
@Table(name="members")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Member extends User{

	public final static String FIND_MEMBER_BY_ID = "findMemberById";
	public final static String FIND_ALL_MEMBERS = "findAllMembers";
	public final static String DELETE_MEMBER = "deleteMember";
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="GEOLOC_ID")
	private Geoloc geoloc;
	
	@Column (name = "SHOPS")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "retailpoint")
	private List<Shop> shops ;
	
	@Column(name="RECOMMANDATION_ID")
	@OneToMany(fetch = FetchType.EAGER, mappedBy= "recommandations")
	private List<Recommandation> recommandations;

	public Member() {}

	public Member(Geoloc geoloc, List<Shop> shops, List<Recommandation> recommandations) {
		super();
		this.geoloc = geoloc;
		this.shops = shops;
		this.recommandations = recommandations;
	}
}
