package fr.cpasam.leonardo.resources;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.errors.ChatNotFoundException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.chat.Chat;
import fr.cpasam.leonardo.model.chat.ShopChat;
import fr.cpasam.leonardo.model.chat.ShopChatDAO;
import fr.cpasam.leonardo.model.chat.TextMessage;
import fr.cpasam.leonardo.model.chat.TextMessageDAO;
import fr.cpasam.leonardo.model.shop.Shop;
import fr.cpasam.leonardo.model.shop.ShopDAO;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;
import fr.cpasam.leonardo.utilities.Validator;


/**
 * Traitement de la requete d'ouverture d'un chat
 * @param json [shop_id et user_id]
 * @return le chat créé
 */
@Path("chat/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

	@POST
	public Response openChat(JsonObject json) {

		Long shop_id = json.get("shop_id").getAsLong();
		Long user_id = json.get("user_id").getAsLong();

		
		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();

		
		// Vérifier le jeton CSRF

		//		String token = json.get("token").getAsString();
		//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		System.out.println("Open Chat with : shop n°"+shop_id+" and user n°"+user_id);

		Member m = MemberDAO.get(user_id);

		Shop s = ShopDAO.get(shop_id);

		Chat c;
		try {
			c = m.openChat(s);
		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}



		return Response.ok(c).build();

	}


	/**
	 * Traitement de la requete de récupération d'un chat via son id
	 * @param id du chat
	 * @param json [user_id et token pour vérifier la connexion]
	 * @return le chat correspondant si tout c'est bien passé sinon des message d'erreur [401 : utilisateur non connecté / 406 : mauvais mot de passe / 403 : chat n'appartient pas au membre]
	 */
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") long id,@QueryParam("USER") long user_id,@QueryParam("TOKEN") String token ) {

		
		// Vérifier que l'utilisateur est bien connecté 
		if(MemberDAO.get(user_id) == null || token == "") return Response.status(Response.Status.UNAUTHORIZED).build();

		// Vérifier le jeton CSRF

		//		String token = json.get("token").getAsString();
		//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que le chat appartient au membre

		ShopChat c = null;
		
		try {
			
			c = (ShopChat) ShopChatDAO.get(id);
			System.out.println("Get chat ");
			
			
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
	 * Traitement de la requete pour récupérer les chats correspondant au membre donné
	 * @param json [user_id et token]
	 * @return le chat correspondant si tout c'est bien passé sinon des message d'erreur [401 : utilisateur non connecté / 406 : mauvais mot de passe]
	 */
	@GET
	public Response get(JsonObject json,@QueryParam("USER") long user_id, @QueryParam("TOKEN") String token) {

		// Vérifier que l'utilisateur est bien connecté 
		if(user_id == 0) return Response.status(Response.Status.UNAUTHORIZED).build();

		// Vérifier le jeton CSRF

		//		if(!Validator.checkCSRF(user_id, token)) return Response.status(Response.Status.NOT_ACCEPTABLE).build();

		//Vérifier que le chat appartient au membre

		ArrayList<ShopChat> chats;
		try {
			chats = ShopChatDAO.getByMember(user_id);
		} catch (ChatNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok(chats).build();

	}
	
	/**
	 * Create a new message in the cat
	 * @param chat_id id of the chat where the message has to be send
	 * @param json {user_id,token,content}
	 * @return
	 */
	@POST
	@Path("{id}/message")
	public Response sendMessage(@PathParam("id") long chat_id, JsonObject json) {
		System.out.println("Sending message ... ");

		// Vérifier que l'utilisateur est bien connecté 
		if(!json.has("user_id")) return Response.status(Response.Status.UNAUTHORIZED).build();
		
		System.out.println("Access granted...");

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
		
		if((member.getId() != user_id)) member = chat.getShop().getMember(user_id);  
		
	
		if ( member == null) return Response.status(Response.Status.FORBIDDEN).build();
		
		String content = json.get("content").getAsString();
		
		System.out.println("Member : "+member.getFirstName()+" on chat : "+chat.id()+"\n \""+content+"\"");
		TextMessage<Member> message = TextMessageDAO.create(member,chat,content);
		
		if(message == null ) return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		return Response.ok(message).build();
	}












}
