package fr.cpasam.leonardo.resources;


import java.util.ArrayList;

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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.product.ProductDAO;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.tag.ProductTag;
import fr.cpasam.leonardo.model.tag.ProductTagDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.Validator;


@Path("product/")
public class ProductResource {


	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response all() {

		System.out.println("product/all");
		
		ArrayList<Product> products = ProductDAO.all();
		
		
		String test = "";
		for (Product p : products) {
			test+= p.getName()+"\n";
		}
		System.out.println("products : "+test);
		
		return Response.ok(products).build();
	}


	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") long id) {
		
		// Vérifier si le produit existe

		if(ProductDAO.get(id) == null) return Response.status(Response.Status.NOT_FOUND).build();

		return Response.ok(ProductDAO.get(id)).build();
	}




	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(JsonObject json) { 


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que l'utilisateur est bien modérateur sur la boutique

		Member m = ShopDAO.getMember(user_id, json.get("shop_id").getAsLong());

		if(m == null) return Response.status(Response.Status.FORBIDDEN).build();

		JsonArray ja = json.get("tag").getAsJsonArray();

		ArrayList<ProductTag> tags = new ArrayList<ProductTag>();
		for(JsonElement e : ja) {
			// Récupérer le tag

			String keyword = e.getAsJsonObject().get("keyword").getAsString();
			ProductTag t = ProductTagDAO.getTagByName(keyword);
			//Si le tag n'existe pas, le créer

			if(t == null) t = ProductTagDAO.create(keyword);

			//ajouter le tag a la liste

			tags.add(t);
		}

		long shop_id = json.get("shop_id").getAsLong();

		Product p = ProductDAO.create(
				json.get("name").getAsString(), 
				shop_id, 
				json.get("price").getAsFloat());

		return Response.ok(p).build();
	}




	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id,  JsonObject json) { 

		// Vérifier si le produit existe

		if(ProductDAO.get(id) == null) return Response.status(Response.Status.NOT_FOUND).build();


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que l'utilisateur est bien modérateur sur la boutique

		Member m = ShopDAO.getMember(user_id, json.get("shop_id").getAsLong());

		if(m == null) return Response.status(Response.Status.FORBIDDEN).build();


		JsonArray ja = json.get("tag").getAsJsonArray();

		ArrayList<ProductTag> tags = new ArrayList<ProductTag>();
		for(JsonElement e : ja) {
			// Récupérer le tag

			String keyword = e.getAsJsonObject().get("keyword").getAsString();
			ProductTag t = ProductTagDAO.getTagByName(keyword);
			//Si le tag n'existe pas, le créer

			if(t == null) t = ProductTagDAO.create(keyword);

			//ajouter le tag a la liste

			tags.add(t);
		}

		long shop_id = json.get("shop_id").getAsLong();

		Product p = ProductDAO.update(id, 
									  json.get("name").getAsString(), 
									  shop_id, 
									  json.get("price").getAsFloat());

		return Response.ok(p).build();
	}


	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") long id, JsonObject json) {

		// Vérifier si le produit existe
		if(ProductDAO.get(id) == null) return Response.status(Response.Status.NOT_FOUND).build();

		
		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		//Vérifier que le shop appartient bien au user

		long shop_id = json.get("shop_id").getAsLong();

		if(ShopDAO.getOwner(shop_id).getId() != user_id ) return Response.status(Response.Status.FORBIDDEN).build();

		//Suppression product

		ProductDAO.delete(id) ;

		return Response
				.status(Status.ACCEPTED)
				.build();
	}


}
