package fr.cpasam.leonardo.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import fr.cpasam.leonardo.model.product.Product;
import fr.cpasam.leonardo.model.product.ProductDAO;

@Path("/test")
public class TestResource {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Product getAllCustomers() {
		return ProductDAO.get(1);
	}    
}
