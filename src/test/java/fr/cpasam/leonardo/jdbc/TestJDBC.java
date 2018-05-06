package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {


	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost/leonardo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "antoine";
		String pswd = "";
		System.out.println("Connecting to database ...");

		try {
			Connection myConn = DriverManager.getConnection(jdbcUrl, user, pswd);
			
			System.out.println("Connection succesful");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
