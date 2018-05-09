package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import fr.cpasam.leonardo.model.user.Admin;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.Connexion;
import fr.cpasam.leonardo.utilities.DAOManager;

public class TestJDBC extends DAOManager {


	public static void main(String[] args) throws Exception {
		Statement stmt = null;
		Connection myConn = Connexion.getInstance() ;
		try {
			stmt = myConn.createStatement();
			
			int deleted =stmt.executeUpdate("DELETE FROM ShopMember WHERE id_Member=2");
			stmt.executeUpdate("DELETE FROM Message WHERE id_Member=2");
			stmt.executeUpdate("DELETE FROM Shop WHERE id_Member=2");
			stmt.executeUpdate("DELETE FROM Admin WHERE id_User=2");
			stmt.executeUpdate("DELETE FROM Member WHERE id_Member=2");
			stmt.executeUpdate("DELETE FROM User WHERE id_User=2");
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
}