package fr.cpasam.leonardo.model.recommandation;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;

public class Recommandation {

	private static long cnt = 10;
	public static long getCnt() {
		return cnt++;
	}
	
	private long id;
	private int grade;
	private String comment;
	private Member member;
	private Shop shop;

	public Recommandation() {
		// TODO Auto-generated constructor stub
	}
	
	public Recommandation(long id,int grade, String comment, Member member, Shop shop) {
		this.id = id;
		this.shop = shop;
		this.grade = grade;
		this.comment = comment;
		this.member = member;
	}
	
	public String toString() { 
		return("id : " + this.id + " shop : " + this.shop + " grade : " + this.grade + " comment : " + this.comment + " member : " + this.member);	
	}

	
	
	/**
	 * Retourne l'id d'une recommandation
	 * @return id
	 */
	public long getId() {return id;}
	/**
	 * Retourne la note d'une recommandation
	 * @return grade
	 */
	public int getGrade() {return grade;}
	/**
	 * Retourne le commentaire d'une recommandation
	 * @return comment
	 */
	public String getComment() {return comment;}
	/**
	 * Retourne l'auteur d'une recommandation
	 * @return member
	 */
	public Member getMember() {return member;}
	
	
	/**
	 * Met à jour l'id d'une recommandation
	 * @param id
	 */
	public void setId(long id) {this.id=id;}
	/**
	 * Met à jour la note d'une recommandation
	 * @param grade
	 */
	public void setGrade(int grade) {this.grade = grade;}
	/**
	 * Met à jour le commentaire d'une recommandation
	 * @param comment
	 */
	public void setComment(String comment) {this.comment = comment;}
	/**
	 * Met à jour l'auteur d'une recommandation
	 * @param member
	 */
	public void setMember(Member member) {this.member = member;}

}




