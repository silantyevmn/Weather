package myweather.silantyevmn.ru.weather;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CityDetailPressureAndWindFragment extends Fragment {
    private int positionID=0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        City city = CityEmitter.getCities().get(positionID);
        View view=inflater.inflate(R.layout.fragment_city_detail_pressure_and_wind, container, false);
        //покажем ветер
        TextView textViewWind = (TextView) view.findViewById(R.id.textView_wind);
        textViewWind.setText(city.getWind(getString(R.string.prefix_wind)));
        //покажем давление
        TextView textViewPressure = (TextView) view.findViewById(R.id.textView_pressure);
        textViewPressure.setText(city.getPressure(getString(R.string.prefix_pressure)));

        //проверим показатели чекбоксов
        LinearLayout windContainer=(LinearLayout) view.findViewById(R.id.wind_container);
        windContainer.setVisibility(CityEmitter.isWind?View.VISIBLE:View.GONE);
        LinearLayout pressureContainer=(LinearLayout) view.findViewById(R.id.pressure_container);
        pressureContainer.setVisibility(CityEmitter.isPressure?View.VISIBLE:View.GONE);
        return view;
    }

    public void setPositionID(int positionID){
        this.positionID=positionID;
    }

}
