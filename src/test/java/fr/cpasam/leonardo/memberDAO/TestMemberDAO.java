package fr.cpasam.leonardo.memberDAO;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;

public class TestMemberDAO {

	public static void main(String[] args) {
		
		Member member = null ;
		member = MemberDAO.mailToMember("me@ce");
		System.out.println(member);

	}

}
