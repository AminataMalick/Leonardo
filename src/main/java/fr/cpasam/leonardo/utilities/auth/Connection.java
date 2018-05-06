package fr.cpasam.leonardo.utilities.auth;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

public class Connection {
	@Context private HttpServletRequest request;

	@POST
	@Path("/authenticate")
	public void authenticate(@FormParam("email") String mail, 
	        @FormParam("password") String password) {

	    // Implementation of your authentication logic
	    /*if (authenticate(username, password)) {
	        request.getSession(true);
	        // Set the session attributes as you wish
	    }*/
	}
}
