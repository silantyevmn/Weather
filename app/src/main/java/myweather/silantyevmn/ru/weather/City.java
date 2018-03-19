package myweather.silantyevmn.ru.weather;

import java.util.Locale;

/**
 * Created by silan on 14.03.2018.
 */

public class City {
    private String name;
    private String temperature;
    private String wind;
    private String pressure;

    public City(String name, String temperature, String wind,String pressure) {
        this.name = name;
        this.temperature = temperature;
        this.wind = wind;
        this.pressure=pressure;
    }

    public String getName() {
        return name;
    }
    public String getTemperature(String prefix) {
        return temperature+prefix;
    }
    public String getWind(String prefix){
        return wind+prefix;
    }
    public String getPressure(String prefix){
        return pressure+prefix;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(),"%s %s",name,temperature);
    }
}
