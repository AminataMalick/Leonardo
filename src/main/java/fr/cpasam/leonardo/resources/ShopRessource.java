package fr.cpasam.leonardo.resources;

import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.cpasam.leonardo.model.shop.Shop;

@Path("shop/")
public class ShopRessource {
    /*
	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Shop> resGetAllShops() {

    	List<Shop> shops = new ArrayList<>() ;
    	shops = getAllShops();
        return shops;
    }
    */
    
/*
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Shop resGetShopID(@PathParam("id") long id) {

    	Shop shop = new getShopID(id);
        return shop;
    }
    */
    
    
    /*
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addShop(Shop s){
      cList.add(s);
      return Response.status(201).build();
    }  
    
    
    @PUT
    @Path("{id}/update")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShop(Shop s){
      int matchIdx = 0;
      Optional<Shop> match = cList.stream()
          .filter(c -> c.getId() == customer.getId())
          .findFirst();
      if (match.isPresent()) {
        matchIdx = cList.indexOf(match.get());
        cList.set(matchIdx, s);
        return Response.status(Response.Status.OK).build();
      } else {
        return Response.status(Response.Status.NOT_FOUND).build();      
      }
    }  
    
    
    @DELETE
    @Path("/remove/{id}")
    public void deleteShop(@PathParam("id") long id){
      Predicate <Shop> shop = c -> c.getId() == id;
      if (!cList.removeIf(shop)) {
       throw new NotFoundException(new JsonError("Error", "Customer " + id + " not found"));
      }
    }  
    */

}
