package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC {


	public static void main(String[] args) {
		String jdbcUrl = "jdbc:mysql://localhost/leonardo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
<<<<<<< HEAD
		String user = "antoine";
=======
		String user = "cel2";
>>>>>>> 25782af5774f33497f61a4b1e7e350987f5da7b8
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
