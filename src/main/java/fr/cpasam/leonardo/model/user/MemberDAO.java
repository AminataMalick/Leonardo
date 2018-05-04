package fr.cpasam.leonardo.model.user;


import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import fr.cpasam.leonardo.utilities.CRUD_Service;



public abstract class MemberDAO <T> implements CRUD_Service <T> {
	private Class<T> persistentClass;
    private Session session;
    private static SessionFactory sessionFactory;
    private static Logger LOGGER = Logger.getLogger("InfoLogging");

}

