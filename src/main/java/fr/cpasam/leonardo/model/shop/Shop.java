package fr.cpasam.leonardo.model.shop;

import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.user.Member;

public class Shop {
	
	private long id;
	protected String name;
	protected String description;
	protected List<RetailPoint> retailPoints;
	private Member member;
	private List<Product> products ;
	protected List<Chat> chats;

	public Shop() {}
	
	public Shop(String name, String description, List<RetailPoint> retailPoints, Member member) {
		this.name = name;
		this.description = description;
		this.retailPoints = new ArrayList<RetailPoint>();
		this.member = member;
		this.products = new ArrayList<Product>();
		this.chats = new ArrayList<Chat>();
	}

	/*
	 * To Do
	 */
	public static Shop get(Long shopId) {
		// TODO Auto-generated method stub
		return null;
	}

	public void addChat(Chat nwChat) {
		
		if(!this.chats.contains(nwChat)) {
			this.chats.add(nwChat);
		}
		
	}

	public long id() {
		
		return this.id;
	}

	public Member getMember(long user_id) {
		ShopDAO.getMember(user_id);
		return null;
	}
	
}
