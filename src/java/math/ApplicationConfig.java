/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package math;
import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author chris-joyce
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(Distance.DistanceResource.class);
        resources.add(Postcode.PostcodeResource.class);
        resources.add(Room.RoomResources.class);
        resources.add(Weather.WeatherCall.class);
        resources.add(Weather.WeatherResource.class);
        resources.add(math.MathOperations.class);
    }
    
}
