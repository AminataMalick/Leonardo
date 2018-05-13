package fr.cpasam.leonardo.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
import fr.cpasam.leonardo.model.user.MemberDAO;
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
	
	/**
	 * Affiche tous les membres de la base
	 * @return retourne une liste avec tous les membres
	 */
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Member> all() {

		System.out.println("member/all");
		
		List<Member> members= MemberDAO.all();
		
		System.out.println("Get Member");
		String test = "";
		for (Member m : members) {
			test+= m.getFirstName()+" "+m.getLastName()+"\n";
		}
		System.out.println("members : "+test);
		
		return members;
	}

	/**
	 * Cherche un membre à partir de son identifiant
	 * @param id identifiant du membre que l'on cherche
	 * @return retourne le membre recherché
	 */
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member get(@PathParam("id") long id) {
		
		Member m = MemberDAO.get(id);
		System.out.println("Member : "+ m.getFirstName() +" "+m.getLastName());
		return m;
	}

	

	/**
	 * Creation d'un membre
	 * @param json
	 * @return membre créé
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(JsonObject json) { 
		
		System.out.println("Member Creation Launched... ");

		

		Member m = MemberDAO.create(
				json.get("firstName").getAsString(), 
				json.get("lastName").getAsString(),   
				json.get("email").getAsString(),
				json.get("pwd").getAsString());

		return Response.ok(m).build();
	}

	/**
	 * Mise à jour d'un membre
	 * @param id identifiant du membre à mettre à jour
	 * @param json 
	 * @return retourne le membre mis à jour
	 */
	@PUT
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") long id,  JsonObject json) { 
		if(MemberDAO.get(id) == null) return Response.status(Response.Status.NOT_FOUND).build();
		
		Member m = MemberDAO.update(id, 	
				json.get("firstName").getAsString(), 
				json.get("lastName").getAsString(),   
				json.get("email").getAsString(),
				json.get("pwd").getAsString());
		
		return Response.ok(m).build();
	}
	
	/**
	 * Affiche un membre 
	 * @param mail
	 * @return
	 */
	@GET
	@Path("email/{mail}")
	@Produces(MediaType.APPLICATION_JSON)
	public Member mailToMember(@PathParam("mail") String mail) {
		Member member = MemberDAO.mailToMember(mail);
		return member;
	}
	
}