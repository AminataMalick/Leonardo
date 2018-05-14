package fr.cpasam.leonardo.memberDAO;

import java.util.List;
import fr.cpasam.leonardo.model.user.Member;

public class TestMemberDAO {
	public static void main(String[] args) {
		// Creation d'un membre avec identifiant générer automatiquement
		// System.out.println(MemberDAO.create("jacques", "deCompostel", "ja@de", "jade"));
		// System.out.println(MemberDAO.create("jo", "Ny", "jo@ny", "jojo"));


		// Mise à jour d'un membre à partir de son identifiant et des données à changer
		// System.out.println(MemberDAO.update(501, "celine", "deCompostel", "e@de", "cede"));


		//Suppression d'un membre en passant l'identifiant en paramètre
		//  System.out.println(MemberDAO.delete(200));


		// Affichage de tous les membres de la base de données
		// System.out.println(MemberDAO.all());


		//Affichage d'un membre à partir de son identifiant 
		// System.out.println(MemberDAO.get(200));


		// Affichage d'un membre à partir de son email
		// System.out.println(MemberDAO.mailToMember("ce@de"));
	}
}
