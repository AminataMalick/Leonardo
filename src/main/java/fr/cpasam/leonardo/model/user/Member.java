package fr.cpasam.leonardo.model.user;



import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat._ChatManager;
import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

public class Member extends User implements _ChatManager<Member, Shop>{

	private Geoloc geoloc;
	private List<Shop> shops ;
	private List<Recommandation> recommandations;


	public Member(String first_name, String last_name, String email, String password ) {
		super(first_name, last_name, email, password);
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}
	

	
	public Chat openChat(Shop shop) {
		
		Chat nwChat = Chat.get(shop.id(), this.id);
		
		if( nwChat == null) {
			nwChat = new ShopChat(this, shop);
		}
		this.addChat(nwChat);
		shop.addChat(nwChat);
		return nwChat;
	}
	
	public Geoloc GetMemberGeoloc() {return geoloc;}
	public List<Shop> GetMemberShops() {return shops;}
	public List<Recommandation> GetMemberRecommandations() {return recommandations;}
	
	public void SetMemberGeoloc(Geoloc geoloc) {this.geoloc = geoloc;}
	public void SetMemberShops(List<Shop> shops) {this.shops = shops;}
	public void SetMemberRecommandations(List<Recommandation> recommandations) {this.recommandations = recommandations;}
	
}
