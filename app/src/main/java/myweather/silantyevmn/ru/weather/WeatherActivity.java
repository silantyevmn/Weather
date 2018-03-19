package myweather.silantyevmn.ru.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements CityListFragment.CityListListener {
    private WeatherPreferencer preferencer;
    private Switch switchPressure,switchWind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if(CityEmitter.getCities()==null){
            CityEmitter.initNewCityParam(MainActivity.this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencer=new WeatherPreferencer(this);
        switchWind=findViewById(R.id.switchWind);
        switchPressure=findViewById(R.id.switchPressure);
        if (savedInstanceState == null) {
            //загрузка
            load();
        }
    }

    @Override
    public void onListItemClick(int positionID) {
      /*  CityDetailFragment detailFragment = new CityDetailFragment();
        detailFragment.setPositionID(positionID);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.addToBackStack(null);
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.commit();*/

        View container=findViewById(R.id.fragment_city_detail);
        CityEmitter.isWind=switchWind.isChecked();
        CityEmitter.isPressure=switchPressure.isChecked();

        if(container==null){
            Intent intent=new Intent(this,CityDetailActivity.class);
            intent.putExtra(CityDetailActivity.EXTRA_POSITION_ID,positionID);
            startActivity(intent);
        } else {
            // Инстантиируем Fragment
            CityDetailFragment detailFragment = new CityDetailFragment();
            detailFragment.setPositionID(positionID);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.addToBackStack(null);
            transaction.replace(R.id.fragment_city_detail, detailFragment);
            transaction.commit();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private void save() {
        preferencer.save(switchWind.isChecked(),switchPressure.isChecked());
    }

    private void load() {
        switchWind.setChecked(preferencer.getSwitchWind());
        switchPressure.setChecked(preferencer.getSwitchPressure());
    }
}
