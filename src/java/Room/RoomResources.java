/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Room;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
/**
 * REST Web Service
 *
 * @author chris-joyce
 */
@Path("room")
public class RoomResources {
    private static Map<Integer, String> roomApplications = new HashMap<>(); // Tracks applications by room ID and status
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of Room
     */
    public RoomResources() {
    }

    /**
     * Retrieves representation of an instance of Room.Room
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
    try {
        // Read the JSON file
        List<Room> rooms = JsonFileReader.readJsonFile();
        
        // Parse the JSON content into a List of Room objects
        Gson gson = new Gson();

        // Convert the list back to JSON for the response
        return gson.toJson(rooms);
        
    } catch (Exception e) {
        // Log the error for debugging
        e.printStackTrace();
        return "{\"error\": \"Failed to retrieve room list. Details: " + e.getMessage() + "\"}";
    }
    }
    
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public String getRoomList() {
        try {
            // Read the JSON file
            List<Room> rooms = JsonFileReader.readJsonFile();

            // Parse the JSON content into a List of Room objects
            Gson gson = new Gson();

            // Convert the list back to JSON for the response
            return gson.toJson(rooms);
        } catch (Exception e) {
            // Log the error for debugging
            e.printStackTrace();
            return "{\"error\": \"Failed to retrieve room list. Details: " + e.getMessage() + "\"}";
        }
    }

    /**
     * PUT method for updating or creating an instance of Room
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
    
    @GET
    @Path("applications")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getApplicationHistory() {
        try {
            // Load applications from JSON file
            List<RoomApplication> applications = ApplicationHistoryManager.loadApplications();

            // Respond with the applications list as JSON
            Gson gson = new Gson();
            String json = gson.toJson(applications);
            return Response.ok(json).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to retrieve application history: " + e.getMessage())
                           .build();
        }
    }
    
    /**
     * @param requestBody
     * @return an instance of java.lang.String
     */
    @POST
    @Path("apply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response applyForRoom(String requestBody) {
        try {
            // Parse the JSON input
            Gson gson = new Gson();
            RoomApplication application = gson.fromJson(requestBody, RoomApplication.class);

            // Validate input
            if (application.getRoomId() == 0 || application.getUserId() == null) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity("Invalid room application. Room ID and User ID are required.")
                               .build();
            }

            // Generate a unique application ID
            String applicationId = UUID.randomUUID().toString();
            application.setApplicationId(applicationId);

            // Load existing applications
            List<RoomApplication> applications = ApplicationHistoryManager.loadApplications();

            // Add new application with "Pending" status
            application.setStatus("Pending");
            applications.add(application);

            // Save updated applications to JSON file
            ApplicationHistoryManager.saveApplications(applications);

            // Respond with the created application
            String jsonResponse = gson.toJson(application);
            return Response.ok(jsonResponse).build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to process room application: " + e.getMessage())
                           .build();
        }
    }

    /**
     * @param applicationId
     * @return an instance of java.lang.String
     */
    @DELETE
    @Path("cancel")
    @Produces(MediaType.APPLICATION_JSON)
    public Response cancelApplication(@QueryParam("applicationid")String applicationId) {
        try {
            // Load existing applications
            List<RoomApplication> applications = ApplicationHistoryManager.loadApplications();

            // Check if the application exists
            boolean found = applications.removeIf(app -> app.getApplicationId().equals(applicationId));

            if (!found) {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("Application ID not found.")
                               .build();
            }

            // Save the updated list back to JSON file
            ApplicationHistoryManager.saveApplications(applications);

            // Respond with success
            return Response.ok("Application successfully cancelled.").build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("Failed to cancel application: " + e.getMessage())
                           .build();
        }
    }
}
