/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Room;

/**
 *
 * @author chris-joyce
 */
import java.io.FileReader;
import java.io.Reader;
import java.util.List;

import com.google.gson.Gson;
import java.nio.file.Paths;
import java.util.Scanner;

/*
File r
*/
public class JsonFileReader {
    // Function to read the JSON file and return a list of Room objects
    public static List<Room> readJsonFile() {
        try {
            String filePath = "/home/chris-joyce/Cloud_Computing/RESTservice/Resources/rooms.json";
            Reader reader = new FileReader(filePath);
            Gson gson = new Gson();

            // Parse JSON into a RoomsData object
            RoomsData roomsData = gson.fromJson(reader, RoomsData.class);

            reader.close();
            return roomsData.getRooms(); // Return the list of rooms
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

class RoomsData {
    private List<Room> rooms;

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
