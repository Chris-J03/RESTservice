/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Room;

/**
 *
 * @author chris-joyce
 */
import java.util.List;

public class Room {
    private int id;
    private String name;
    private Location location;
    private Details details;
    private boolean live_in_landlord;
    private int shared_with;
    private boolean bills_included;
    private boolean bathroom_shared;
    private int price_per_month_gbp;
    private String availability_date;
    private List<String> spoken_languages;

    // Getters and Setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }
    public void setLocation(Location location) {
        this.location = location;
    }

    public Details getDetails() {
        return details;
    }
    public void setDetails(Details details) {
        this.details = details;
    }

    public boolean isLive_in_landlord() {
        return live_in_landlord;
    }
    public void setLive_in_landlord(boolean live_in_landlord) {
        this.live_in_landlord = live_in_landlord;
    }

    public int getShared_with() {
        return shared_with;
    }
    public void setShared_with(int shared_with) {
        this.shared_with = shared_with;
    }

    public boolean isBills_included() {
        return bills_included;
    }
    public void setBills_included(boolean bills_included) {
        this.bills_included = bills_included;
    }

    public boolean isBathroom_shared() {
        return bathroom_shared;
    }
    public void setBathroom_shared(boolean bathroom_shared) {
        this.bathroom_shared = bathroom_shared;
    }

    public int getPrice_per_month_gbp() {
        return price_per_month_gbp;
    }
    public void setPrice_per_month_gbp(int price_per_month_gbp) {
        this.price_per_month_gbp = price_per_month_gbp;
    }

    public String getAvailability_date() {
        return availability_date;
    }
    public void setAvailability_date(String availability_date) {
        this.availability_date = availability_date;
    }

    public List<String> getSpoken_languages() {
        return spoken_languages;
    }
    public void setSpoken_languages(List<String> spoken_languages) {
        this.spoken_languages = spoken_languages;
    }

@Override
public String toString() {
    //Display Json file data in a readable and user friendly manner
    StringBuilder sb = new StringBuilder();
    sb.append("Room ID: ").append(id).append("\n");
    sb.append("Name: ").append(name).append("\n");
    sb.append("Location: \n");
    sb.append("  - City: ").append(location.getCity()).append("\n");
    sb.append("  - County: ").append(location.getCounty()).append("\n");
    sb.append("  - Postcode: ").append(location.getPostcode()).append("\n");
    sb.append("Details: \n");
    sb.append("  - Furnished: ").append(details.isFurnished() ? "Yes" : "No").append("\n");
    sb.append("  - Amenities: ").append(String.join(", ", details.getAmenities())).append("\n");
    sb.append("Live-in Landlord: ").append(live_in_landlord ? "Yes" : "No").append("\n");
    sb.append("Shared With: ").append(shared_with).append(" people\n");
    sb.append("Bills Included: ").append(bills_included ? "Yes" : "No").append("\n");
    sb.append("Bathroom Shared: ").append(bathroom_shared ? "Yes" : "No").append("\n");
    sb.append("Price Per Month: £").append(price_per_month_gbp).append("\n");
    sb.append("Availability Date: ").append(availability_date).append("\n");
    sb.append("Spoken Languages: ").append(String.join(", ", spoken_languages)).append("\n");
    sb.append("---------------------------------------------------------");
    return sb.toString();
}
}

class Location {
    private String city;
    private String county;
    private String postcode;

    // Getters and Setters
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }
    public void setCounty(String county) {
        this.county = county;
    }

    public String getPostcode() {
        return postcode;
    }
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    @Override
    public String toString() {
        return city + ", " + county + " (" + postcode + ")";
    }
}

class Details {
    private boolean furnished;
    private List<String> amenities;

    // Getters and Setters
    public boolean isFurnished() {
        return furnished;
    }
    public void setFurnished(boolean furnished) {
        this.furnished = furnished;
    }

    public List<String> getAmenities() {
        return amenities;
    }
    public void setAmenities(List<String> amenities) {
        this.amenities = amenities;
    }

    @Override
    public String toString() {
        return "Furnished: " + furnished + ", Amenities: " + amenities;
    }
}

