/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package Weather;

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

/**
 * REST Web Service
 *
 * @author chris-joyce
 */
@Path("weather")
public class WeatherResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WeatherResource
     */
    public WeatherResource() {
    }

    /**
     * Retrieves representation of an instance of Weather.WeatherResource
     * @param longitude
     * @param latitude
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson(@QueryParam("longitude") double longitude, @QueryParam("latitude") double latitude) {
        WeatherAPI weatherAPI = new WeatherAPI();
        List<DataSeries> weatherDataList = weatherAPI.fetchWeatherData(longitude, latitude);       

        Gson gson = new Gson();
        return gson.toJson(weatherDataList);  // Convert the list of DataSeries to JSON
    }

    /**
     * PUT method for updating or creating an instance of WeatherResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
