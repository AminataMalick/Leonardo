package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.errors.TextError;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberRecoveryException;
import fr.cpasam.leonardo.exceptions.MemberUpdateException;
import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.Authentication;

public class MemberResource {
	
	@PUT
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(JsonObject json) {
		Member member = null;
		String token = json.get("token").getAsString();
		long id = json.get("id").getAsLong();
		String firstName = json.get("firstName").getAsString();
		String lastName = json.get("lastName").getAsString();
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		
		try {
			member = Authentication.modify(id, firstName, lastName, mail, pwd, token);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("One or several fields are missing.").message()).build();
		} catch (MemberRecoveryException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while recovering the member to update.")).build();
		} catch (WrongTokenException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new TextError("Wrong CSRF token, you must be logged in.")).build();
		} catch (MemberUpdateException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while updating the member.")).build();
		}
		return Response.ok(member).build();
	}
	
}




