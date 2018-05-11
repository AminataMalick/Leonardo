package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.ShopChatDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.utilities.Validator;

@Path("/chat")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {
	
	@POST
	public Response openChat(JsonObject json) {
		
		Long shop_id = json.get("shop_id").getAsLong();
		Long user_id = json.get("user_id").getAsLong();
		
		Member m = MemberDAO.get(user_id);
		
		Shop s = ShopDAO.get(shop_id);
		
		Chat c = m.openChat(s);
		
		
		
		return Response.ok(c).build();
		
	}
	
	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") long id, JsonObject json) {
		
		long user_id = json.get("user_id").getAsLong();
		
		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();
		
		// Vérifier le jeton CSRF
		
		String token = json.get("token").getAsString();
		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		
		//Vérifier que le chat appartient au membre
		
		ShopChat c = (ShopChat) ShopChatDAO.get(id);
		if((c.getMember().getId()) != user_id && c.getShop().getMember(user_id)==null) return Response.status(Response.Status.FORBIDDEN).build();
		
		
		return Response.ok(c).build();
	}
	
	@GET
	@Path("?USER={id}")
	public Response get(@PathParam("id") long user_id) {
		
		// Vérifier que l'utilisateur est bien connecté
		
		// Vérifier le jeton CSRF
		
		return null;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
