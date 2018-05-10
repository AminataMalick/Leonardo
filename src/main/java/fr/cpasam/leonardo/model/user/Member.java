package fr.cpasam.leonardo.model.user;


import java.util.ArrayList;

import java.util.List;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.ShopChatDAO;
import fr.cpasam.leonardo.model.chat._ChatManager;
import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;


public class Member extends User implements _ChatManager<Member, Shop>{


	private Geoloc geoloc;
	private List<Shop> shops ;
	private List<Recommandation> recommandations;



	public String toString() { 
		return("id : " + this.id + " firstName : " + this.firstName + " lastName : " + this.lastName + " email : " + this.email + " pwd : " + this.pwd);	
	}

	public Member(long id,String first_name, String last_name, String email, String password, String token ) {
		super(id, first_name, last_name, email, password,token);
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}

	public Member(String first_name, String last_name, String email, String password ) {
		super(first_name, last_name, email, password);
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}
	
	public Member(long id, String first_name, String last_name, String email, String password ) {
		super(id, first_name, last_name, email, password);
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}


	/**
	 * Créé un nouveau chat pour une boutique
	 * @param shop
	 * @return nwChat
	 */
	public Chat openChat(Shop shop) {

		
		Chat nwChat = ShopChatDAO.getByMemberAndShop(this.id,shop.id());

		if( nwChat == null) {
			nwChat = ShopChatDAO.create(this, shop);
		}
		this.addChat(nwChat);
		shop.addChat(nwChat);
		return nwChat;
	}

	/**
	 * Retourne la geolocalisation d'un membre
	 * @return geoloc
	 */
	public Geoloc getGeoloc() {return geoloc;}
	/**
	 * Retourne les boutiques d'un membre
	 * @return shops
	 */
	public List<Shop> getShops() {return shops;}
	/**
	 * Retourne les recommendations laissées par un membre
	 * @return recommandations
	 */
	public List<Recommandation> getRecommandations() {return recommandations;}


	/**
	 * Met à jour la geolocalisation d'un membre
	 * @param geoloc
	 */
	public void setGeoloc(Geoloc geoloc) {this.geoloc = geoloc;}
	/**
	 * Met à jour les boutiques d'un membre
	 * @param shops
	 */
	public void setShops(List<Shop> shops) {this.shops = shops;}
	/**
	 * Met à jour les recommendations laissées par un membre
	 * @param recommandations
	 */
	public void setRecommandations(List<Recommandation> recommandations) {this.recommandations = recommandations;}


}
