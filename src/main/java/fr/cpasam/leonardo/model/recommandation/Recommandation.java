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
	
	public long GetRecommandationId() {return id;}
	public int GetRecommandationGrade() {return grade;}
	public String GetRecommandationComment() {return comment;}
	public Member GetRecommandationMember() {return member;}
	
		
	public void SetRecommandationId(long id) {this.id=id;}
	public void SetRecommandationGrade(int grade) {this.grade = grade;}
	public void SetRecommandationComment(String comment) {this.comment = comment;}
	public void SetRecommandationMember(Member member) {this.member = member;}

}


