package fr.cpasam.leonardo.resources;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import fr.cpasam.leonardo.model.shop.Shop;

@Path("shops/")
public class ShopRessource {
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> getAllShops() {
    	
    	List<Shop> shops = new ArrayList<>();
    	
    	EntityManagerFactory factory = Persistence.createEntityManagerFactory();
    	EntityManager em = factory.createEntityManager();

    	Query shopsAll = em.createNamedQuery("Shop.findAllShops");

    	 // exécution de la requête
    	shops = shopsAll.getResultList() ;
    	
    	
        return shops;
    }
    
    
    /*
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shop getShop(@PathParam("id") long id) {

    	Shop shop = new Shop(id);
    	shop.text = "Shop";

        return shop;
    }
    */
}
