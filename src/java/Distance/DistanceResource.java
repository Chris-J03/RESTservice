/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Distance;

import com.google.gson.Gson;
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
@Path("distance")
public class DistanceResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Distance
     */
    public DistanceResource() {
    }

    /**
     * Retrieves representation of an instance of Distance.Distance
     * @param long1
     * @param long2
     * @param lat1
     * @param lat2
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("long1") double long1, @QueryParam("lat1") double lat1, 
                            @QueryParam("long2") double long2, @QueryParam("lat2") double lat2) {
        
            DistanceService distanceService = new DistanceService();
            double distance = distanceService.calculateDistance(lat1, long1, lat2, long2);
            Gson gson = new Gson();
            return gson.toJson(distance);
    }

    /**
     * PUT method for updating or creating an instance of Distance
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
