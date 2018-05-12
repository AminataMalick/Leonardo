package fr.cpasam.leonardo.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

import fr.cpasam.leonardo.errors.TextError;
import fr.cpasam.leonardo.exceptions.IncompleteDataException;
import fr.cpasam.leonardo.exceptions.MemberDeletionException;
import fr.cpasam.leonardo.exceptions.MemberUpdateException;
import fr.cpasam.leonardo.exceptions.UserNotFoundException;
import fr.cpasam.leonardo.exceptions.WrongTokenException;
import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.utilities.AuthUtil;

@Path("member/")
public class MemberResource {
	
	/**
	 * Traitement de la requête de modification des informations d'un membre
	 * @param json la requête du membre
	 * @return une une requête en json indiquant un message d'erreur si un problème est survenu ou le nouveau membre si la requête a été traitée avec succès
	 */
	@PUT
	@Path("update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response modify(JsonObject json) {
		Member member = new Member();
		String token = json.get("token").getAsString();
		long id = json.get("id").getAsLong();
		String firstName = json.get("firstName").getAsString();
		String lastName = json.get("lastName").getAsString();
		String mail = json.get("email").getAsString();
		String pwd = json.get("password").getAsString();
		
		try {
			member = AuthUtil.modify(id, firstName, lastName, mail, pwd, token);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("One or several fields are missing.").message()).build();
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while recovering the member to update.")).build();
		} catch (WrongTokenException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new TextError("Wrong CSRF token, you must be logged in.")).build();
		} catch (MemberUpdateException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while updating the member.")).build();
		}
		return Response.ok(member).build();
	}
	
	/**
	 * Traitement de la demande de suppression du compte d'un membre
	 * @param json la requête du membre
	 * @return le code http 200 ok si tout s'est bien passé, ou un code d'erreur sinon
	 */
	@POST
	@Path("remove")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAccount(JsonObject json) {
		Long id = json.get("id").getAsLong();
		String token = json.get("token").getAsString();
		
		try {
			AuthUtil.deleteAccount(id, token);
		} catch (IncompleteDataException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("One or several fields are missing.").message()).build();
		} catch (UserNotFoundException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while recovering the member to delete.")).build();
		} catch (WrongTokenException e) {
			return Response.status(Response.Status.UNAUTHORIZED).entity(new TextError("Wrong CSRF token, you must be logged in.")).build();
		} catch (MemberDeletionException e) {
			return Response.status(Response.Status.NOT_ACCEPTABLE).entity(new TextError("Error while deleting the member.")).build();
		}
		return Response.status(Response.Status.ACCEPTED).build();
	}
	
}