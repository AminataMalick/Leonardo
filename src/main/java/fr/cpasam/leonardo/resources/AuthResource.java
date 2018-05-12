package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.errors.TextError;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberCreationException;
import fr.cpasam.leonardo.exceptions.TokenCreationException;
import fr.cpasam.leonardo.exceptions.TokenDeletionException;
import fr.cpasam.leonardo.exceptions.TokenStorageException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.exceptions.WrongPasswordException;
import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.AuthUtil;

@Path("auth/")
public class AuthResource {
	
	/**
	 * Effectue la connexion d'un utilisateur
	 * @param json la requête envoyée par le client, demandant la connexion d'un utilisateur
	 * @return une requête en json indiquant un message d'erreur si un problème est survenu ou le token généré si la requête a été traitée avec succès
	 */
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(JsonObject json) {
		System.out.println("********* cc twé ************");
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		User user = null;
		
		try {
			user = AuthUtil.connection(mail, pwd);
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("User not found in database.").JsonMessage()).build();
//			return Response.status(Response.Status.NOT_FOUND).build();
		} catch (WrongPasswordException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Wrong password.").JsonMessage()).build();
//			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		} catch (IncompleteDataException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Email and/or password missing.").JsonMessage()).build();
//			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		} catch (TokenCreationException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while generating the CSRF token.").JsonMessage()).build();
//			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		} catch (TokenStorageException e) {
			e.printStackTrace();
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while storing the CSRF token in database.").JsonMessage()).build();
//			return Response.status(Response.Status.NOT_ACCEPTABLE).build();
		}
		
		//System.out.println(user.toString());
		
//		JsonObject jsonUser = new JsonObject();
//		jsonUser.addProperty("id", user.getId());
//		jsonUser.addProperty("firstName", user.getFirstName());
//		jsonUser.addProperty("lastName", user.getLastName());
//		jsonUser.addProperty("email", user.getEmail());
//		jsonUser.addProperty("password", user.getPwd());
//		jsonUser.addProperty("token", user.getToken());
//		
//		JsonObject jsonToReturn = new JsonObject();
//		jsonToReturn.add("user", jsonUser);
		
		return Response.ok(user.toString()).build();
	}	
	
	/**
	 * Enregistre un nouveau membre dans la base de données et effectue sa connexion
	 * @param json la requête du client demandant une inscription
	 * @return une requête en json indiquant un message d'erreur si un problème est survenu ou le token généré si la requête a été traitée avec succès
	 */
	@POST
	@Path("/member")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(JsonObject json) {
		String firstName = json.get("firstName").getAsString();
		String lastName = json.get("lastName").getAsString();
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		
		try {
			AuthUtil.registration(firstName, lastName, mail, pwd);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("One or several fields are missing.")).build();
		} catch (MemberCreationException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while creating the member in database.")).build();
		}
		JsonObject jsonConnection = new JsonObject();
		jsonConnection.addProperty("email", mail);
		jsonConnection.addProperty("password", pwd);
		
		return login(jsonConnection);
	}
	
	/**
	 * Déconnecte un utilisateur de l'application
	 * @param json la requête envoyée par le client demandant sa déconnexion
	 * @return le code http 200 ok si tout s'est bien passé, ou un code d'erreur sinon
	 */
	@POST
	@Path("/logout")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response logout(JsonObject json) {
		long id = json.get("id").getAsLong();
		String token = json.get("token").getAsString();

		try {
			AuthUtil.logout(id, token);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("id or token data is missing.")).build();
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("User not found in database.").message()).build();
		} catch (WrongTokenException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new TextError("Wrong CSRF token, you must be logged in.")).build();
		} catch (TokenDeletionException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while deleting the CSRF token in database.")).build();
		}
		
		return Response.status(Response.Status.ACCEPTED).build();
	}
}
