package myweather.silantyevmn.ru.weather;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by silan on 14.03.2018.
 */

public class CityEmitter {
    //private static City[] cities;
    private static ArrayList<City> cities;
    public static boolean isWind=false;
    public static boolean isPressure=false;

    public static ArrayList<City> getCities(){
        return cities;
    }
//    public static City[] getCities() {
//        return cities;
//    }

    public static void initNewCityParam(Context context) {
        String[] arrCity=context.getResources().getStringArray(R.array.city_selection);
        cities=new ArrayList<>();
        for (int i = 0; i < arrCity.length; i++) {
            String name=arrCity[i].toString();
            String temperature=getRandomMinMax(-15,-10);
            String wind=getRandomMinMax(1,12);
            String pressure=getRandomMinMax(750,800);
            cities.add(i,new City(name,temperature,wind,pressure));
        }
//        String[] arrCity=context.getResources().getStringArray(R.array.city_selection);
//        cities=new City[arrCity.length];
//        for (int i = 0; i < arrCity.length; i++) {
//            String name=arrCity[i].toString();
//            String temperature=getRandomMinMax(-15,-10);
//            String wind=getRandomMinMax(1,12);
//            String pressure=getRandomMinMax(750,800);
//            cities[i]=new City(name,temperature,wind,pressure);
//        }
    }
    public static void addNewCity(String nameCity){
        String name=nameCity;
        String temperature=getRandomMinMax(-15,-10);
        String wind=getRandomMinMax(1,12);
        String pressure=getRandomMinMax(750,800);
        cities.add(new City(name,temperature,wind,pressure));
    }

    private static String getRandomMinMax(int min,int max){
        return String.valueOf((int)(Math.random()*(max-min))+min);
    }

    public static void deleteCity(int id) {
        cities.remove(id);
    }
}
