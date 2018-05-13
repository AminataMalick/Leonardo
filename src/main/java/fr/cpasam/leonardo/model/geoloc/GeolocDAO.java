package fr.cpasam.leonardo.model.geoloc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.utilities.DAOManager;

public class GeolocDAO extends DAOManager {
	

	/**
	 * Attribut de la classe GeolocDAO representant un compteur pour générer un identifiant automatiquement
	 */
	private static long cnt = 0;
	/**
	 * Méthode pour incrémenter l'identifiant
	 * @return retourne le compteur incrémenter d'une unité
	 */
	public static long getCnt() {
		return cnt++;
	}
	
	// Bloc static 
	
	  static {	
	  	cnt = getLastId()+1;
	  }
	  
		public static long getLastId() {
			Statement statement = null;
			long id_Geoloc = 0;
			try {
				statement = con.createStatement();
				/* Récupération de l'identifiant max du Produit */
				ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Geoloc) FROM Geoloc");

				/* Récupération des données du résultat de la requête de lecture */
				if ( resultat.next() ) {
					/* Récupération du produit */
					id_Geoloc= resultat.getLong(1);
				}
			}catch (SQLException e) { 
				e.printStackTrace();
			}
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return id_Geoloc;
		}
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
