/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package math;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;

/**
 * REST Web Service
 *
 * @author chris-joyce
 */
@Path("math")
public class MathOperations {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of MathOperations
     */
    public MathOperations() {
    }

    /**
     * Retrieves representation of an instance of math.MathOperations
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        return "Hello, I am a service performing math operations when called by a client!!";
    }

    /**
     * PUT method for updating or creating an instance of MathOperations
     * @param content representation for the resource
     */
    @PUT
    @Consumes(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
