package fr.cpasam.leonardo.resources;

import java.io.UnsupportedEncodingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;

import com.google.gson.JsonObject;

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
	public Response login(JsonObject json) {
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
		
		String token = generateToken(user);
		if(token == null) return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while generating the token.")).build();
		
		user.setToken(token);
		
		Authentication.saveToken(user);
		
		return Response.ok(token).build();
	}
	
	/**
	 * Génère un token lié à la session de l'utilisateur lors de la connexion de ce-dernier
	 * @param user l'utilisateur qui souhaite se connecter
	 * @return le token généré à partir de l'e-mail et de l'id de l'utilisateur ou null si une erreur est survenue
	 */
	private String generateToken(User user) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		        .withIssuer("leonardo")
		        .withArrayClaim("mail", new String[] {user.GetUserEmail(), Long.toString(user.GetUserId())})
		        .sign(algorithm);
		    return token;
		} catch (UnsupportedEncodingException exception){
		} catch (JWTCreationException exception){
		}
		return null;
	}
	
	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response register(JsonObject json) {
		
		return null;
	}
}
