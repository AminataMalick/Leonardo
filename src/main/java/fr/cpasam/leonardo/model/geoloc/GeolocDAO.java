package fr.cpasam.leonardo.model.geoloc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class GeolocDAO extends DAOManager {

	public static Geoloc get(long geolocId) {
		Statement statement = null;		
		Geoloc geoloc= null;
		try {
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Geoloc WHERE idGeoloc="+ geolocId);
	            /* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	geoloc= new Geoloc(resultat.getInt(1),resultat.getInt(2),resultat.getInt(3),geoloc.getMembers(),null);
	            }
	     }catch (SQLException e) { e.printStackTrace();} 
		finally {
			if (statement != null){
				try {statement.close();}
				catch (SQLException e) {e.printStackTrace();}
			}
		}return geoloc;	
	}
	
	
	public static List<Geoloc> all() {
		List<Geoloc> geolocs = new ArrayList<Geoloc>();	
		Statement statement =null;
		
		try {
			Geoloc geoloc= null;
			statement = con.createStatement();
			/* Exécution d'une requête de lecture */
			ResultSet resultat = statement.executeQuery( "SELECT * FROM Geoloc");
	            /* Récupération des données du résultat de la requête de lecture */
	            while ( resultat.next() ) {
	            	geoloc= new Geoloc(resultat.getInt(1),resultat.getInt(2),resultat.getInt(3),geoloc.getMembers(),null);
	            	geolocs.add(geoloc);
	            }
	     }catch (SQLException e) { e.printStackTrace();} 
		 finally {
			if (statement != null){
				try {statement.close();}
				catch (SQLException e) {e.printStackTrace();}
			}
		}return geolocs;	
	}
	
	
	
}
