package fr.cpasam.leonardo.memberDAO;

import java.util.List;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;

public class TestMemberDAO {

	public static void main(String[] args) {
		
		Member member = null ;
		member = MemberDAO.mailToMember("me@ce");
		System.out.println(member);
		
		
		Member member2 = null ;
		member2 =MemberDAO.create("jacques", "deCompostel", "ja@de", "jade");
		System.out.println(member2);
		
		List<Member> members = null ;
		members = MemberDAO.all();
		System.out.println(members);
		
	}

}
