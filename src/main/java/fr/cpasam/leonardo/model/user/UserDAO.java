package fr.cpasam.leonardo.model.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cpasam.leonardo.utilities.Connexion;
import fr.cpasam.leonardo.utilities.DAOManager;

public class UserDAO extends DAOManager{



	//Retourne un user grace a son email
	public User MailToUser (String email) {
		Statement stmt = null;
		try {
			stmt = con.createStatement();
			ResultSet rset = stmt.executeQuery("SELECT * FROM Member WHERE email");
			if (rset != null) {
				User user = new Member();
				user = (User) rset ;


				return user ;
			}
		}catch (SQLException e) {
				e.printStackTrace();
			}
		
		return null ;

	}
}
