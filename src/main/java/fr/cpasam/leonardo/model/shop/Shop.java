package fr.cpasam.leonardo.model.shop;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.Length;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.user.Member;

public class Shop {
	
	private long id;
	protected String name;
	protected String description;
	protected List<RetailPoint> retailPoints = new ArrayList<>();
	private RetailPoint retailPoint;
	private Member member;
	List<Product> products ;

	public Shop() {}
	
	public Shop(String name, String description, List<RetailPoint> retailPoints,
			RetailPoint retailPoint, Member member, List<Product> products) {
		this.name = name;
		this.description = description;
		this.retailPoints = retailPoints;
		this.retailPoint = retailPoint;
		this.member = member;
		this.products = products;
	}
	
}
