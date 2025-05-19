/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Room;

/**
 *
 * @author chris-joyce
 */
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ApplicationHistoryManager {
    private static final String FILE_PATH = "/home/chris-joyce/Cloud_Computing/RESTservice/Resources/applications.json";
    private static final Gson gson = new Gson();

    // Save applications to JSON file
    public static void saveApplications(List<RoomApplication> applications) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(applications, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Load applications from JSON file
    public static List<RoomApplication> loadApplications() {
        try (Reader reader = new FileReader(FILE_PATH)) {
            Type listType = new TypeToken<List<RoomApplication>>() {}.getType();
            return gson.fromJson(reader, listType);
        } catch (FileNotFoundException e) {
            // If the file does not exist, return an empty list
            return new ArrayList<>();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
