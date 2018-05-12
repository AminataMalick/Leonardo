package fr.cpasam.leonardo.model.recommandation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Admin;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.DAOManager;

public class RecommandationDAO extends DAOManager  {

	public static Recommandation get(long recommandationid) {
		Statement statement = null;		
		Recommandation recommandation = null ;
		return recommandation;

	}

	public static List<Recommandation> all(long idShop) {
		List<Recommandation> recommandations = new ArrayList<Recommandation>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT id_Recommandation,grade_Recommandation,comment_Recommandation,id_Member,id_Shop FROM Recommandation WHERE id_Shop ="+idShop+")");

			while (rset.next()) {
				Recommandation recommandation = new Recommandation(rset.getLong(1),rset.getInt(2),rset.getString(3),MemberDAO.get(rset.getLong(4)),ShopDAO.get(rset.getLong(5)));
				recommandations.add(recommandation);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return recommandations;
	}
	


	public static float averageShop (long idShop) {
		Statement stmt = null;
		float moyenne = 0 ;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT sum(grade_Recommandation)/count(grade_Recommandation) FROM Recommandation WHERE id_Shop ="+idShop);

			while (rset.next()) {
				moyenne = rset.getFloat(1);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return moyenne ; 
	}
	
	
	
	public static Recommandation create(int grade,String comment, Member member, Shop shop) {
		Statement stmt = null;
		Recommandation recommandation = null ;
		long id = Recommandation.getCnt();
		try {
			stmt = con.createStatement();
			stmt.executeUpdate("INSERT INTO Recommandation(id_Recommandation,id_Shop,grade_Recommandation,comment_Recommandation,id_Member) VALUES("+id+","+shop.id()+","+grade+",'"+comment+"',"+member.getId()+")");
				recommandation = new Recommandation(id, grade, comment, member, shop);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		try { stmt.close();
		} catch (SQLException e) { e.printStackTrace();}
		return recommandation ; 
	}
	//public static Recommandation update(XXXXXX) {}

	//public static void Recommandation delete(XXXX) {}
}
