package fr.cpasam.leonardo.model.recommandation;

import fr.cpasam.leonardo.model.user.Member;

public class Recommandation {

	private long id;
	
	private int grade;
	
	private String comment;
	
	private Member member;

	public Recommandation() {}
	
	public Recommandation(int grade, String comment, Member member) {
		this.grade = grade;
		this.comment = comment;
		this.member = member;
	}
	
	
}
