package fr.cpasam.leonardo.DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.Statement;

import fr.cpasam.leonardo.model.geoloc.Geoloc;
import fr.cpasam.leonardo.utilities.DAOManager;

public class GeolocDAO extends DAOManager{


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

	/**
	 * Cherche l'identifiant maximum dans la table afin d'incrémenter celui-ci d'une unité et de  générer un nouvel identifiant automatiquement  
	 * @return retourne l'identifiant maximum de Geoloc
	 */
	public static long getLastId() {
		Statement statement = null;
		long id_Geoloc = 0;
		try {
			statement = con.createStatement();
			/* Récupération de l'identifiant max de la geolocalisation */
			ResultSet resultat = statement.executeQuery( "SELECT MAX(id_Geoloc) FROM Geoloc");

			/* Récupération des données du résultat de la requête de lecture */
			if ( resultat.next() ) {
				/* Récupération de la geolocalisation */
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

	/**
	 * Affiche une géolocalisation à partir de son identidiant
	 * @param geolocId identifiant de la géolocalisation à chercher
	 * @return retourne la géolocalisation selon l'identifiant passé en paramètre 
	 */
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

	/**
	 * Affiche toutes les géolocalisations
	 * @return retourne une liste contenant toutes les géolocalisations
	 */
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
