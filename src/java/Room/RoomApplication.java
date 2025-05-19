/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Room;

/**
 *
 * @author chris-joyce
 */
public class RoomApplication {
    private String applicationId; // Unique identifier for the application
    private int roomId;
    private String userId;
    private String status; // Example: "Pending", "Accepted", "Rejected"

    // Constructors, Getters, and Setters
    public RoomApplication() { }

    public RoomApplication(String applicationId, int roomId, String userId, String status) {
        this.applicationId = applicationId;
        this.roomId = roomId;
        this.userId = userId;
        this.status = status;
    }
    
    // Getters and Setters for applicationId
    public String getApplicationId() {
        return applicationId;
    }

    // Setter for applicationId (if needed)
    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}


