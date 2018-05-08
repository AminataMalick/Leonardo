package fr.cpasam.leonardo.utilities;

import java.sql.Connection;

public class DAOManager {
	protected static Connection con = Connexion.getInstance();
}
