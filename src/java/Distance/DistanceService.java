/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Distance;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;

/**
 *
 * @author chris-joyce
 */
public class DistanceService {
    public double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
try {
            // Build the OSRM API URL, make sure longitude comes first
            String urlStr = String.format(
                Locale.US,  // Force US locale
                "http://router.project-osrm.org/route/v1/driving/%.6f,%.6f;%.6f,%.6f",
                lon1, lat1, lon2, lat2
            );



            // Create HTTP connection
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the API response
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Parse the JSON response
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

            // Check if "routes" array exists and has data
            JsonArray routes = jsonResponse.getAsJsonArray("routes");
            if (routes != null && routes.size() > 0) {
                // Extract distance from the first route in the response
                double distance = routes.get(0)
                        .getAsJsonObject()
                        .get("distance")
                        .getAsDouble();
                
                // Return distance in kilometers
                return distance / 1000;
            } else {
                System.err.println("No routes found in the API response.");
                return -1; // Return -1 if no routes are found
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Return -1 if an error occurs
        }
    }
}
