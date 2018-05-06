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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;


@Path("shop/")
public class ShopRessource {
	
	/*
	@GET
	@Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
	public Response resGetAllShops() {
		return Response
	    		.status(Status.OK)
	    		.entity(ShopDAO.getAllShops())
	    		.build();
	}
	*/
	
    
	
	/*
	@GET
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
    public Response resGetShopID(@PathParam("id") long id) {

		 return Response
	    		.status(Status.OK)
	    		.entity(ShopDAO.getShopID())
	    		.build();
    }
    */
    
	/* Version 1 :
	@Path("/{id}/{name}/{description}/{retailPoint}/{member}/{products}")
	
    /* Version 2 :
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resCreateShop(@PathParam("SHOP_ID") long id, @PathParam("SHOP_NAME") String name, 
    		@PathParam("DESCRIPTION") String description, @PathParam("RETAILPOINT_ID") RetailPoint retailPoint, 
    		@PathParam("MEMBER_ID") Member member, @PathParam("PRODUCTS") List<Product> products ) {  
        
    	ShopDAO.createShop(id, name, description, retailPoint, member, products) ;

        return Response
          .status(Status.CREATED)
          .build();
    }
    */
    
    
	/* Version 1 :
	@PUT
    @Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response resUpdateShop(@PathParam("id") long id, Shop shop) {
		ShopDAO.UpdateShop(shop);
		 return Response
                  .status(Status.OK)
                  .entity(shop)
                  .build();
	}
	*/
	
	
	
    /* Version 2 :
    @PUT
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resUpdateShop(Shop s){
     
     Shop shop = ShopDAO.getShopID(id) ;
		if (shop) {
                return Response
                  .status(Status.OK)
                  .entity(shop)
                  .build();
        }

        return Response
          .status(Status.NO_CONTENT)
          .build();
    }  
    */
	
	
	/*
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response resDeleteShop(@PathParam("SHOP_ID") long id) {
    	ShopDAO.deleteShop(id) ;

        return Response
        	.status(Status.ACCEPTED)
        	.build();
    }
    */
    
}
