package fr.cpasam.leonardo.model.user;


import java.util.ArrayList;

import java.util.List;

import fr.cpasam.leonardo.DAO.ShopChatDAO;
import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat._ChatManager;
import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.model.recommandation.Recommandation;
import fr.cpasam.leonardo.model.shop.Shop;

public class Member extends User implements _ChatManager<Member, Shop>{

	/**
	 * Attributs de la classe Member
	 */
	private Geoloc geoloc;
	private List<Shop> shops ;
	private List<Recommandation> recommandations;
	private long id_member;

	
	/**
	 * Méthode toString() pour décrire un objet Member
	 */
	public String toString() { 
		return("id : " + this.id + " firstName : " + this.firstName + " lastName : " + this.lastName + " email : " + this.email + " pwd : " + this.pwd);	
	}

	/**
	 * Constructeur Member avec 6 paramètres
	 * @param id identifiant du membre
	 * @param first_name prénom du membre
	 * @param last_name nomdu membre
	 * @param email email du membre
	 * @param password mot de passe du membre
	 * @param token token du membre
	 */
	public Member(long id,String first_name, String last_name, String email, String password, String token ) {
		super(id, first_name, last_name, email, password,token);
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}
	
	
	/**
	 * Constructeur avec 8 params
	 * @param id_user
	 * @param id_member
	 * @param first_name
	 * @param last_name
	 * @param email
	 * @param password
	 * @param token
	 * @param chats
	 */
	public Member(long id_user,long id_member,String first_name, String last_name, String email, String password, String token) {
		super(id_user, first_name, last_name, email, password,token);
		this.id_member = id_member;
		this.geoloc = null;
		this.shops = new ArrayList<Shop>();
		this.recommandations = new ArrayList<Recommandation>();
	}
	
	

	/**
	 * Créé un nouveau chat pour une boutique
	 * @param shop
	 * @return nwChat
	 * @throws UserNotFoundException 
	 * @throws ChatNotFoundException 
	 */
	public Chat openChat(Shop shop) throws ChatNotFoundException, UserNotFoundException {

		System.out.println("OpenChat shop_id : "+shop.id()+" user : "+this.id);
		Chat nwChat = ShopChatDAO.getByMemberAndShop(this.id,shop.id());

		if( nwChat == null) {
			System.out.println("Chat not found");
			System.out.println("Chat creation in progress ...");
			nwChat = ShopChatDAO.create(this, shop);
		}

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

	public long getMemberId() {return this.id_member;}
	
}
