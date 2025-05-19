/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Weather;

/**
 *
 * @author chris-joyce
 */
public class DataSeries 
{
    private int date;
    private String weather;
    private Temp2m temp2m;
    private int wind10m_max;

    // Getters and setters
    public int getDate() { return date; }
    public void setDate(int date) { this.date = date; }

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    public Temp2m getTemp2m() { return temp2m; }
    public void setTemp2m(Temp2m temp2m) { this.temp2m = temp2m; }

    public int getWind10m_max() { return wind10m_max; }
    public void setWind10m_max(int wind10m_max) { this.wind10m_max = wind10m_max; }
}



