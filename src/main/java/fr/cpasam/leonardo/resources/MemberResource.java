package fr.cpasam.leonardo.resources;

import java.util.function.Predicate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import fr.cpasam.leonardo.model.user.Member;
import fr.cpasam.leonardo.model.user.MemberDAO;


@Path("member/")
public class MemberResource {

	@GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Member> resGetAllShops() {

    	List<Member> members = new ArrayList<>() ;
    	members = MemberDAO.getAllMembers();
        return members;
    }
}




