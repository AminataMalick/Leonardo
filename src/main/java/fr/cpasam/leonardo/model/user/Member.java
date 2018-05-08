package fr.cpasam.leonardo.model.user;


import java.util.List;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

public class Member extends User{
	private final static String QUERY_FIND_MEMBERS = "SELECT * FROM Member ";
	
	private Geoloc geoloc;
	
	private List<Shop> shops ;
	
	private List<Recommandation> recommandations;

	public Member() {}
	public Member(long id, String firstName, String lastName, String email, String pwd) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pwd = pwd;
	}
	
	public Member(Geoloc geoloc, List<Shop> shops, List<Recommandation> recommandations) {
		super();
		this.geoloc = geoloc;
		this.shops = shops;
		this.recommandations = recommandations;
	}
	
	 public String toString() { 
         return("id : " + this.id + " firstName : " + this.firstName + " lastName : " + this.lastName + " email : " + this.email + " pwd : " + this.pwd);	
	}
	
}
