/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chris-joyce
 */

public class WeatherAPI 
{
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public static String getParamsString(HashMap<String, String> params) throws UnsupportedEncodingException 
    {
        return params.entrySet()
                .stream()
                .map(entry -> {
                    try {
                        return URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.joining("&"));
    }
    
    
    public List<DataSeries> fetchWeatherData(double longitude, double latitude) 
    {
        List<DataSeries> weatherDataList = new ArrayList<>();

        try 
        {
            String longit = Double.toString(longitude);
            String latit = Double.toString(latitude);
            Map<String, String> parameters = new HashMap<>();
            parameters.put("lon", longit);
            parameters.put("lat", latit);
            parameters.put("lang", "en"); //Change these parameters to be inputted by the user
            parameters.put("unit", "metric");
            parameters.put("output", "json");
            
            // Convert parameters to String
            String convertedParamsToString = getParamsString((HashMap<String, String>) parameters);
            URL url = new URL(" https://www.7timer.info/bin/civillight.php?" + convertedParamsToString);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            
            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) 
            { 
                // Create BufferedReader to read the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                
                // StringBuilder to accumulate the JSON data
                StringBuilder jsonResponse = new StringBuilder();
                String line;

                // Read each line of the response and append it to the StringBuilder
                while ((line = reader.readLine()) != null) {
                    jsonResponse.append(line);
                }

                // Close the reader
                reader.close();
                //System.out.println(jsonResponse);
                
                Gson gson = new Gson();

                WeatherResponse weather = gson.fromJson(jsonResponse.toString(), WeatherResponse.class);

                weatherDataList = weather.getDataseries();  // Directly retrieve the list of DataSeries
            }

        } catch (MalformedURLException ex) {
            Logger.getLogger(WeatherAPI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(WeatherAPI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return weatherDataList;
    }
}
