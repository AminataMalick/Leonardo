package fr.cpasam.leonardo.resources;

import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.Consumes;
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

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.model.recommandation.RecommandationDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.utilities.Validator;


@Path("shop/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class ShopRessource {


	@GET
	@Path("all")
	public Response all() {
		
		return Response
				.ok(ShopDAO.all())
				.build();
	}


	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id) {
		
		return Response.ok(ShopDAO.get(id)).build();
	}


	@GET
	@Path("?USER")
	public Response getByMember(JsonObject json) {

		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		// Récupérer les shop de l'utilisateur
		
		ArrayList<Shop> shops = ShopDAO.getByMember(user_id);


		return Response.ok(shops).build();
	}

	@POST
	public Response create(JsonObject json) { 


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		Shop s = ShopDAO.createShop(
				json.get("name").getAsString(), 
				json.get("description").getAsString(),
				user_id) ;

		return Response.ok(s).build();
	}



	

	@PUT
	@Path("/{id}")
	public Response update(@PathParam("id") long id,  JsonObject json) { 


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


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


	@DELETE
	@Path("{id}")
	public Response delete(@PathParam("id") long id, JsonObject json) {
		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		//Vérifier que le shop appartient bien au user

		long shop_id = json.get("shop_id").getAsLong();

		if(ShopDAO.getOwner(shop_id).getId() != user_id ) return Response.status(Response.Status.FORBIDDEN).build();

		//Suppression shop

		ShopDAO.delete(id) ;

		return Response
				.status(Status.ACCEPTED)
				.build();
	}
	
	
	@GET
	@Path("{id}/recommandation")
	@Produces(MediaType.APPLICATION_JSON)
	public Response recommandations(@PathParam("id") long id) {
		
		return Response
				.ok(RecommandationDAO.all(id))
				.build();
	}


	
	
	
}