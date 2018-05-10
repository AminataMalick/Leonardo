package fr.cpasam.leonardo.utilities;

import java.sql.Connection;

import bd.Connexionsgbd;

public class DAOManager {
	protected static Connection con = Connexionsgbd.getInstance();
}
