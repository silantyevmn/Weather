package myweather.silantyevmn.ru.weather;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by silan on 19.03.2018.
 */

public class WeatherPreferencer {
    private SharedPreferences sharedPreferences;
    private boolean SWITCH_PRESSURE_DEFAULT = false;
    private boolean SWITCH_WIND_DEFAULT = false;
    private String KEY_PRESSURE = "key_pressure";
    private String KEY_WIND = "key_wind";
    private String KEY_ARRAY_LIST = "key_array_list";
    private Bundle bundle;

    public WeatherPreferencer(Activity activity, Bundle bundle) {
        this.sharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE);
        this.bundle = bundle;
    }

    public void save(boolean wind, boolean pressure) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        //записываем показатели
        editor.putBoolean(KEY_WIND, wind);
        editor.putBoolean(KEY_PRESSURE, pressure);
        editor.apply();
    }

//    public void saveArrayList(City[] cities) {
//        ArrayList<String> tempArray=new ArrayList<>();
//        for (int i = 0; i < cities.length; i++) {
//            tempArray.add(cities[i].getName());
//        }
//        bundle.putStringArrayList(KEY_ARRAY_LIST, tempArray);
//    }
//
//    public ArrayList<String> loadArrayList() {
//        return bundle.getStringArrayList(KEY_ARRAY_LIST);
//    }

    public boolean getIsPressure() {
        return sharedPreferences.getBoolean(KEY_PRESSURE, SWITCH_PRESSURE_DEFAULT);
    }

    public boolean getIsWind() {
        return sharedPreferences.getBoolean(KEY_WIND, SWITCH_WIND_DEFAULT);
    }
}
