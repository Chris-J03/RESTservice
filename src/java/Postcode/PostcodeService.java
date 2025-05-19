/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Postcode;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author chris-joyce
 */
public class PostcodeService {
    //User inputs postcode and the input is put into the api and returned automatically to be used to get other data
    Coordinate postCodeAPI(String postCode) {
        // Replace placeholder with actual API URL and inputted postcode
        String apiUrl = "http://api.getthedata.com/postcode/" + postCode;

        try {
            // Make HTTP GET request
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            // Check HTTP response code
            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error Code: " + conn.getResponseCode());
            }

            // Read response
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String output;
            while ((output = br.readLine()) != null) {
                response.append(output);
            }
            conn.disconnect();

            // Parse JSON response using Gson
            Gson gson = new Gson();
            JsonObject jsonResponse = gson.fromJson(response.toString(), JsonObject.class);

            // Extract coordinates from the "data" object
            JsonObject data = jsonResponse.getAsJsonObject("data");
            double latitude = data.get("latitude").getAsDouble();
            double longitude = data.get("longitude").getAsDouble();

            // Return a Coordinate object with latitude and longitude
            return new Coordinate(latitude, longitude);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
