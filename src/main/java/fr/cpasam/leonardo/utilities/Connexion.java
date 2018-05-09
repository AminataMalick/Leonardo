package fr.cpasam.leonardo.utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	private  String url = "jdbc:mysql://localhost/leonardo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private  String username = "cel2";   
	private  String password = "";
	private static Connection con;

	private Connexion() { 
		try {
			con = DriverManager.getConnection(url, username, password);

		} catch (SQLException ex) {
			System.out.println("Failed to create the database connection."); 
		}

	}
	public static Connection getInstance() {
		if (con == null) {
			new Connexion() ;
		}
		return con ;			
	}

	public static void CloseConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		con = null ;
	}			

	public  boolean isConnected(Connection con) {
		try {
			if(!con.isClosed() || con!=null){
				return true;
			}
		} catch (SQLException e) {
			return false;
		}
		return false;
	}
}