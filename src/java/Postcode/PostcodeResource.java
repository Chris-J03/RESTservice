/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Postcode;

import com.google.gson.Gson;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author chris-joyce
 */
@Path("postcode")
public class PostcodeResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostcodeResource
     */
    public PostcodeResource() {
    }

    /**
     * Retrieves representation of an instance of Postcode.PostcodeResource
     * @param postcode
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@QueryParam("postcode") String postcode) {
        try {
            System.out.println("Received postcode: " + postcode);

            // Validate input
            if (postcode == null || postcode.trim().isEmpty()) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"error\": \"Postcode cannot be null or empty\"}")
                        .build();
            }

            // Simulate retrieving data (in reality, you'd call an external API or database)
            PostcodeService postcodeService = new PostcodeService(); // Initialize the object
            Coordinate coordinate = postcodeService.postCodeAPI(postcode);

            // Handle case where no data is found
            if (coordinate == null) {
                return Response.status(Response.Status.NOT_FOUND)
                        .entity("{\"error\": \"Coordinates not found for the given postcode\"}")
                        .build();
            }

            // Convert Coordinate to JSON using Gson
            Gson gson = new Gson();
            String json = gson.toJson(coordinate);

            // Return a successful response
            return Response.ok(json).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"error\": \"An error occurred while processing the request\"}")
                    .build();
        }
    }

    /**
     * PUT method for updating or creating an instance of PostcodeResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
