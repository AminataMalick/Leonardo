package fr.cpasam.leonardo.resources;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.DAO.RecommandationDAO;
import fr.cpasam.leonardo.DAO.ShopDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.utilities.Validator;



@Path("shop/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ShopRessource {

	/**
	 * Affiche tous les shops 
	 * @return retourne la liste de tous les shops
	 */
	@GET
	@Path("all")
	public Response all() {
		
		return Response
				.ok(ShopDAO.all())
				.build();
	}

	/**
	 * Affiche un shop à partir de son identifiant
	 * @param id identifiant du shop à afficher
	 * @return retourne le shop dont l'identifiant est passé en paramètre
	 */
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id) {
		
		return Response.ok(ShopDAO.get(id)).build();
	}

	

	/**
	 * Create a shop
	 * @param json {user_id:Numeric, name:String, description : String }
	 * @return
	 */
	@POST
	public Response create(JsonObject json) { 


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		//String token = json.get("token").getAsString();
		//if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		Shop s = ShopDAO.createShop(
				json.get("name").getAsString(), 
				json.get("description").getAsString(),
				user_id) ;

		return Response.ok(s).build();
	}



	
	/**
	 * Update of a shop
	 * @param id
	 * @param json {user_id:Numeric, name:String, description : String }
	 * @return
	 */
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") long id,  JsonObject json) { 


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		//String token = json.get("token").getAsString();
		//if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		//Vérifier que le shop appartient bien au user

		long shop_id = json.get("shop_id").getAsLong();

		if(ShopDAO.getOwner(shop_id).getId() != user_id ) return Response.status(Response.Status.FORBIDDEN).build();

		Shop s = ShopDAO.updateShop(
				shop_id,
				json.get("name").getAsString(), 
				json.get("description").getAsString(),
				user_id) ;

		return Response.ok(s).build();
	}


	/**
	 * Delete a shop
	 * @param id
	 * @param json { user_id : Numeric, token : String }
	 * @return
	 */
	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id, JsonObject json) {
		
		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();

		// Vérifier le jeton CSRF
		long user_id = json.get("user_id").getAsLong();
		//String token = json.get("token").getAsString();
		//if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		//Vérifier que le shop appartient bien au user
		if(ShopDAO.getOwner(id).getId() != user_id ) return Response.status(Response.Status.FORBIDDEN).build();

		//Suppression shop
		ShopDAO.delete(id) ;

		return Response
				.status(Status.ACCEPTED)
				.build();
	}
	
	
	/**
	 * Return a recommandation
	 * @param id shop id 
	 * @return
	 */
	@GET
	@Path("{id}/recommandation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommandations(@PathParam("id") long id) {
		
		return Response
				.ok(RecommandationDAO.all(id))
				.build();
	}


	
	
	
}