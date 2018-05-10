package fr.cpasam.leonardo.model.recommandation;

import java.sql.Statement;
import java.util.List;

import fr.cpasam.leonardo.utilities.DAOManager;

public class RecommandationDAO extends DAOManager  {
	
	public static Recommandation get(long recommandationid) {
		Statement statement = null;		
		Recommandation recommandation = null ;
		return recommandation;
		
	}
		
	public static List<Recommandation> all() {
		List<Recommandation> recommandations = null ;
		return recommandations;
	}
		
	/*public static Recommandation create(XXXXXX) {
		Recommandation recommandation = null ;
		return recommandation;
	}*/
	
	//public static Recommandation update(XXXXXX) {}
	
	//public static void Recommandation delete(XXXX) {}
}
