package fr.cpasam.leonardo.model.shop;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.user.Member;

public class Shop {
	
	private static long cnt = 106;
	public static long getCnt() {
		return cnt++;
	}
	
	private long id;
	protected String name;
	protected String description;
	protected List<RetailPoint> retailPoints;
	private Member member;
	private List<Product> products ;
	protected List<Chat> chats;

	public Shop() {
		// TODO Auto-generated constructor stub
	}
	
	public Shop(long id, String name, String description, List<RetailPoint> retailPoints, Member member, List<Product> products ) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.retailPoints = new ArrayList<RetailPoint>();
		this.member = member;
		this.products = products ;
		this.chats = new ArrayList<Chat>();
	}

	
	/**
	 * Retourne le shop lié à l'id donné
	 * @param shop_id
	 * @return shop
	 */
	public static Shop get(long shop_id) {
		Shop shop = ShopDAO.get(shop_id);
		return shop;
	}

	
	/**
	 * Ajoute le chat donné au shop
	 * @param nwChat
	 */
	public void addChat(Chat nwChat) {
		if(!this.chats.contains(nwChat)) {
			this.chats.add(nwChat);
		}
	}

	
	/**
	 * Retourne l'id du shop
	 * @return shop_id
	 */
	public long id() { return this.id; }

	/**
	 * Retourne le membre associé
	 * @param user_id
	 * @return member
	 */
	public Member getMember(long user_id) {
		Member member = ShopDAO.getMember(user_id,this.id);
		return member;
	}
	
	/**
	 * Retourne le membre du shop
	 * @return member
	 */
	public Member getMember() {return member;}

	
	/**
	 * Ajoute des produits au shop
	 * @param products
	 */
	public void addProduct(List<Product> products) {
		for(Product p : products) {
			this.products.add(p);
		}
		
	}

	/**
	 * Retourne l'id du shop
	 * @param shop
	 * @return shop_id
	 */
	public static long getID(Shop shop) {
		return shop.id;
	}

	
	
}
