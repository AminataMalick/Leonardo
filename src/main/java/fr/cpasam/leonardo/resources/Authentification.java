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

public class Authentification {

	private String secret = "monSecret";
	
	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(JsonObject json) {
		try {
			String mail = json.get("email").getAsString();
			String pwd = json.get("password").getAsString();
			
			if(mail == null || pwd == null)
				return Response.status(Response.Status.NO_CONTENT).build();
			
			connection(mail, pwd);
			
			String token = generateToken(mail);
			if(token != null)
				return Response.ok(token).build();
			return Response.status(Response.Status.NOT_ACCEPTABLE).build();

        } catch (Exception e) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
	}
	private String generateToken(String mail) {
		try {
		    Algorithm algorithm = Algorithm.HMAC256("secret");
		    String token = JWT.create()
		        .withIssuer("leonardo")
		        .withClaim("mail", mail)
		        .sign(algorithm);
		    return token;
		} catch (UnsupportedEncodingException exception){
		    //UTF-8 encoding not supported
			exception.printStackTrace();
		} catch (JWTCreationException exception){
		    //Invalid Signing configuration / Couldn't convert Claims";
			exception.printStackTrace();
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
