package fr.cpasam.leonardo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.DAO.MemberDAO;
import fr.cpasam.leonardo.DAO.ShopChatDAO;
import fr.cpasam.leonardo.DAO.ShopDAO;
import fr.cpasam.leonardo.DAO.TextMessageDAO;
import fr.cpasam.leonardo.exceptions.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.TextMessage;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.NotificationsMail;




@Path("chat/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

	/**
	 * Open a chat between a shop and a member
	 * @param json { shop_id: Numeric, user_id : Numeric }
	 * @return UNAUTHORIZED, NOT_FOUND or OK with the chat object in json
	 */
	@POST
	public Response openChat(JsonObject json) {

		Long shop_id = json.get("shop_id").getAsLong();
		Long user_id = json.get("user_id").getAsLong();


		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		//		String token = json.get("token").getAsString();
		//	if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();


		Member m;
		try {
			m = MemberDAO.get(user_id);


			Shop s = ShopDAO.get(shop_id);

			Chat c = m.openChat(s);

			return Response.ok(c).build();

		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}




	}


	/**
	 * 
	 * @param id id of the chat that has to be retrieved
	 * @param user_id to check if he can access to the chat
	 * @param token to check CSRF
	 * @return
	 */
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id,@QueryParam("USER") long user_id,@QueryParam("TOKEN") String token ) {


		// Vérifier que l'utilisateur est bien connecté 
		try {
			if(MemberDAO.get(user_id) == null || token == "") return Response.status(Response.Status.UNAUTHORIZED).build();


			// Vérifier le jeton CSRF

			//		String token = json.get("token").getAsString();
			//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

			//Vérifier que le chat appartient au membre

			ShopChat c = (ShopChat) ShopChatDAO.get(id);


			// L'utilisateur est le membre du chat
			long idm = c.getMember().getId();
			boolean isMember = (idm == user_id);

			// L'utilisateur est membre de la boutique qui chat
			Member m = c.getShop().getMember(user_id);
			boolean isShop = (m !=null);

			// Interdir l'accès à un utilisateur qui n'est pas dans le chat
			if( !isMember && !isShop) return Response.status(Response.Status.FORBIDDEN).build();

			return Response.ok(c).build();
		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}

	}



	/**
	 * Return all the chats that belongs to a member
	 * @param user_id 
	 * @param token to check CSRF
	 * @return
	 */
	@GET
	public Response get(@QueryParam("USER") long user_id, @QueryParam("TOKEN") String token) {

		// Vérifier que l'utilisateur est bien connecté 
		if(user_id == 0) return Response.status(Response.Status.UNAUTHORIZED).build();

		// Vérifier le jeton CSRF

		//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que le chat appartient au membre

		List<Chat> chats;
		try {
			chats = ShopChatDAO.getByMember(user_id);
		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(chats).build();

	}

	/**
	 * Create a new message in the chat and send a email the other end of the chat
	 * @param chat_id id of the chat where the message has to be send
	 * @param json {user_id,token,content}
	 * @return the message object in json
	 */
	@POST
	@Path("{id}/message")
	public Response sendMessage(@PathParam("id") long chat_id, JsonObject json) {
		Member sendTo = null;

		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();


		// Vérifier le jeton CSRF

		long user_id = json.get("user_id").getAsLong();
		//		String token = json.get("token").getAsString();
		//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		ShopChat chat;
		try {
			chat = ShopChatDAO.get(chat_id);
		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		if(chat == null ) return Response.status(Response.Status.NOT_FOUND).build();


		Member member = chat.getMember();
		sendTo = chat.getShop().getOwner();
		if((member.getId() != user_id)) {
			sendTo = chat.getMember();
			member = chat.getShop().getMember(user_id);  

		}


		if ( member == null) return Response.status(Response.Status.FORBIDDEN).build();

		String content = json.get("content").getAsString();

		TextMessage<Member> message = TextMessageDAO.create(member,chat,content);

		if(message == null ) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		String econtent = "Bonjour, vous avez recu un nouveau message de "
				+message.getEmiter().getFirstName()
				+" "+message.getEmiter().getLastName()
				+"\n \""+message.getContent()+"\"";

		NotificationsMail.sendMail("Nouveau Message de "+message.getEmiter().getFirstName(), econtent, sendTo.getEmail());

		return Response.ok(message).build();
	}

}