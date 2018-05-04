package fr.cpasam.leonardo.resources;

import fr.cpasam.leonardo.model.Prout;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.HibernateUtil;
import org.hibernate.Session;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("users/")
public class UserResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Prout index() {

        Session session = HibernateUtil.getSessionFactory().openSession();

        session.beginTransaction();

        Prout prout = new Prout();
        prout.text = "WOW!";
        session.save(prout);
        session.getTransaction().commit();

        return prout;
    }
}
