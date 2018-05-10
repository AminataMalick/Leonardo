package fr.cpasam.leonardo.model.user;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.utilities.DAOManager;


public class MemberDAO extends DAOManager {
	
	// Affichage de tous les membres de la BD
	public static List<Member> all() {
		List<Member> members = new ArrayList<Member>();
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User");

			while (rset.next()) {
				Member member = new Member(rset.getInt(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
				members.add(member);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (stmt != null) {
				try {
					// Le stmt.close ferme automatiquement le rset.
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}   
		return members ;   
	}



	/**
	 *  Créer un membre avec la recuperation des donnees du formulaire
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param email
	 * @param pwd
	 */
	public static Member create(String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		long id = User.getCnt();
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("INSERT INTO User(id_User, firstName_User, lastName_User, email_User, pwd_User)VALUES("+id+",'"+firstName+"','"+lastName+"','"+email+"','"+ pwd+"')");
			deleted = stmt.executeUpdate("INSERT INTO Member(id_Member, id_User, id_Geoloc) VALUES("+id+","+id+",null)");
			Member member = new Member(id,firstName,lastName,email, pwd);
			return member ;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return null ;
	}
	
	
	
	public static Member update(long id, String firstName, String lastName, String email, String pwd) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("UPDATE User SET firstName_User = '"+firstName+"',lastName_User ='"+lastName+"', email_User='"+email+"', pwd_User='"+pwd+"' WHERE id_User ="+id+"");
			if (deleted > 0){
			Member member = new Member(id,firstName,lastName,email, pwd);
			return member ;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return null ;
	}
	
	
	
	
	

	// Trouver un membre a l'aide de on ID
	public static Member get(Long memberID) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE id_User="+memberID);
			if(rset.next())
			{			
				while (rset.next()) {
					Member member = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
					return member ;
				}

			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
		return null ;
	}



	public static void delete(Long memberID) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			int deleted =stmt.executeUpdate("DELETE FROM ShopMember WHERE id_Member="+memberID);
			stmt.executeUpdate("DELETE FROM Message WHERE id_Member="+memberID);
			stmt.executeUpdate("DELETE FROM Shop WHERE id_Member="+memberID);
			stmt.executeUpdate("DELETE FROM Admin WHERE id_User="+memberID);
			stmt.executeUpdate("DELETE FROM Member WHERE id_Member="+memberID);
			stmt.executeUpdate("DELETE FROM User WHERE id_User="+memberID);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}	
	}

	//Retourne un user grace a son email
		// Renvoie un user a l'aide de son email
		public static Member mailToMember(String email) {
			Statement stmt = null;
			try {
				stmt = con.createStatement();
				ResultSet rset = stmt.executeQuery("SELECT * FROM Member natural join User WHERE email_User="+email);		
					while (rset.next()) {
						Member member = new Member(rset.getLong(1),rset.getString(2),rset.getString(3),rset.getString(4),rset.getString(5),rset.getString(6));
						return member ;
					}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				if (stmt != null) {
					try {
						stmt.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}	
			return null ;
		}
		
		
}
