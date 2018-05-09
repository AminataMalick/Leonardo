package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.errors.MemberCreationError;
import fr.cpasam.leonardo.errors.TextError;
import fr.cpasam.leonardo.exceptions.BadPasswordException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.model.user.User;
import fr.cpasam.leonardo.utilities.Authentication;

public class Authentification {
	
	/**
	 * Effectue la connexion d'un utilisateur
	 * @param json la requête envoyée par le client, demandant la connexion d'un utilisateur
	 * @return une requête en json indiquant un message d'erreur si un problème est survenu ou le token généré si la requête a été traitée avec succès
	 */
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	private Response login(JsonObject json) {
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		User user = null;
		
		try {
			user = Authentication.connection(mail, pwd);
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("User not found in database.").message()).build();
		} catch (BadPasswordException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Bad password.").message()).build();
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Email and/or password missing.").message()).build();
		}
		
		String token = Authentication.generateToken(user);
		if(token == null) return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while generating the token.")).build();
		
		user.setToken(token);
		
		Authentication.saveToken(user);
		
		return Response.ok(token).build();
	}	
	
	/**
	 * Enregistre un nouveau membre dans la base de données et effectue sa connexion
	 * @param json la requête du client demandant une inscription
	 * @return une requête en json indiquant un message d'erreur si un problème est survenu ou le token généré si la requête a été traitée avec succès
	 */
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	private Response register(JsonObject json) {
		String firstName = json.get("firstName").getAsString();
		String lastName = json.get("lastName").getAsString();
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		
		try {
			Authentication.registration(firstName, lastName, mail, pwd);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("One or several fields are missing.")).build();
		} catch (MemberCreationError e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while creating the member in database.")).build();
		}
		JsonObject jsonConnection = new JsonObject();
		jsonConnection.addProperty("email", mail);
		jsonConnection.addProperty("password", pwd);
		
		return login(jsonConnection);
	}
}
