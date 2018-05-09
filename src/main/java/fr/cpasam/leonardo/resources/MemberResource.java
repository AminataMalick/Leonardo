package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberRecoveryException;
import fr.cpasam.leonardo.exceptions.MemberUpdateException;
import fr.cpasam.leonardo.utilities.Authentication;

public class MemberResource {
	
	@PUT
	@Path("/modify")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(JsonObject json) {
		String token = json.get("token").getAsString();
		long id = json.get("id").getAsLong();
		String firstName = json.get("firstName").getAsString();
		String lastName = json.get("lastName").getAsString();
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		
		try {
			Authentication.modify(id, firstName, lastName, mail, pwd, token);
		} catch (IncompleteDataException e) {
		} catch (MemberRecoveryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WrongTokenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MemberUpdateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
}




