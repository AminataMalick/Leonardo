package fr.cpasam.leonardo.memberDAO;

import fr.cpasam.leonardo.model.user.MemberDAO;

import java.util.List;

import fr.cpasam.leonardo.model.user.Member;
public class TestMemberDAO {

	public static void main(String[] args) {
		
		
		// Test mailToMember
		// System.out.println(MemberDAO.mailToMember("ce@de"));
		
			
		// Test create
		// System.out.println(MemberDAO.create("jacques", "deCompostel", "ja@de", "jade"));
		
		
		// Test all
		// System.out.println(MemberDAO.all());
		
		
		// Test update
		//  System.out.println(MemberDAO.update(200, "celine", "deCompostel", "ce@de", "cede"));
		
		
		/**
		 * Affichage d'un membre à partir de son identifiant
		 */
		// System.out.println(MemberDAO.get(200));
		
		
		
		/**
		 * Suppression d'un membre en passant l'identifiant en paramètre
		 */
		 System.out.println(MemberDAO.delete(200));
		
	}

}
