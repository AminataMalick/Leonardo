package fr.cpasam.leonardo.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

public class Authentification {
	@Context private HttpServletRequest request;

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	public void login(Json json) {

	    // Implementation of your authentication logic
	    /*if (authenticate(username, password)) {
	        request.getSession(true);
	        // Set the session attributes as you wish
	    }*/
	}
}
