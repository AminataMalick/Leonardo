package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;

public class ChatResource {
	
	@POST
	@Path("/chat")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response openChat(JsonObject json) {
		
		Long shop_id = json.get("shop_id").getAsLong();
		Long user_id = json.get("user_id").getAsLong();
		
		Member m = MemberDAO.get(user_id);
		
		Shop s = ShopDAO.get(shop_id);
		
		Chat c = m.openChat(s);
		
		
		
		return Response.ok(c).build();
		
	}
}
