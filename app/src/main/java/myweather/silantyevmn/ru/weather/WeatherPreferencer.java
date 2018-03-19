package myweather.silantyevmn.ru.weather;

import android.app.Activity;
import android.content.SharedPreferences;


/**
 * Created by silan on 19.03.2018.
 */

public class WeatherPreferencer {
    private SharedPreferences sharedPreferences;
    private boolean SWITCH_PRESSURE_DEFAULT = false;
    private boolean SWITCH_WIND_DEFAULT = false;
    private String KEY_PRESSURE="key_pressure";
    private String KEY_WIND="key_wind";

    public WeatherPreferencer(Activity activity) {
        this.sharedPreferences=activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public void save(boolean wind,boolean pressure){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //записываем показатели
        editor.putBoolean(KEY_WIND, wind);
        editor.putBoolean(KEY_PRESSURE, pressure);
        editor.apply();
    }

    public boolean getSwitchPressure() {
        return sharedPreferences.getBoolean(KEY_PRESSURE,SWITCH_PRESSURE_DEFAULT);
    }

    public boolean getSwitchWind() {
        return sharedPreferences.getBoolean(KEY_WIND,SWITCH_WIND_DEFAULT);
    }
}
