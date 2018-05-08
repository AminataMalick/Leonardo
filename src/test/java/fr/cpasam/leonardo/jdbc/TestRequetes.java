package fr.cpasam.leonardo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.*;
import com.mysql.cj.api.jdbc.Statement;

public class TestRequetes {
	
	/**  Je voudrais renvoyer une List<String> qui correspond au resultat de ma requete select  **/
	public final void AllGeoloc() {
		/* Connexion à la base de données */
		String jdbcUrl = "jdbc:mysql://localhost/leonardo?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		String user = "cel2";
		String pswd = "";
		
		Connection connexion = null;
	    java.sql.Statement statement = null;
	    ResultSet resultat = null;
	   
	    try {
	       connexion = DriverManager.getConnection(jdbcUrl, user, pswd);
	       /* Création de l'objet gérant les requêtes */
	        statement = connexion.createStatement();
	        /* Exécution d'une requête de lecture */
	        resultat = statement.executeQuery( "SELECT * FROM Geoloc;" );
	            
	        	/* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	int idGeoloc= resultat.getInt(1);
	                int latGeoloc = resultat.getInt(2);
	       			int longitGeoloc = resultat.getInt(3);
	            } 
	    }catch (SQLException e) {e.printStackTrace();}       
	}
	
	
	public final void IdGeoloc(int id){
		
	}
	
	public static void main(String[] args) {
		
	}
	
}






	