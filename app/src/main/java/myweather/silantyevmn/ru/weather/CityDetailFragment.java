package myweather.silantyevmn.ru.weather;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CityDetailFragment extends Fragment {
    public static final String DETAIL_FRAGMENT_TAG = "CityDetailPressureAndWindFragment";
    private final String POSITION_ID = "position_id";
    private int positionID = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Надуваем Фрагмент
        View view = inflater.inflate(R.layout.fragment_city_detail, container, false);
        // Проверяем, есть ли что-то в savedInstanceState
        if (savedInstanceState != null) {
            positionID = savedInstanceState.getInt(POSITION_ID);
        }
        // Создаем все наши View и возвращаем их
        view = setInitView(view);

        //добавляем 3-й фрагмент
        FragmentManager childFragmentManager = getChildFragmentManager();
        CityDetailPressureAndWindFragment detailFragment= (CityDetailPressureAndWindFragment) childFragmentManager.findFragmentByTag(DETAIL_FRAGMENT_TAG);
        if(detailFragment==null){
            FragmentTransaction transaction=childFragmentManager.beginTransaction();
            detailFragment=new CityDetailPressureAndWindFragment();
            detailFragment.setPositionID(positionID);
            transaction.add(R.id.details_fragment2,detailFragment, DETAIL_FRAGMENT_TAG);
            transaction.commit();
        }
        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        //super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(POSITION_ID, positionID);
    }

    private View setInitView(View view) {
        City city = CityEmitter.getCities()[positionID];
        //покажем город
        TextView textViewCity = (TextView) view.findViewById(R.id.textView_city);
        textViewCity.setText(city.getName());
        //покажем температуру
        TextView textViewTemperature = (TextView) view.findViewById(R.id.textView_temperature);
        textViewTemperature.setText(city.getTemperature(getString(R.string.prefix_temperature)));
        return view;
    }

    public void setPositionID(int positionID) {
        this.positionID = positionID;
    }

}
