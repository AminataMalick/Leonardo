package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {


	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://167.99.40.140:3306/leonardo?useSSL=false";
		String user = "root";
		String pswd = "9mars45200";
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