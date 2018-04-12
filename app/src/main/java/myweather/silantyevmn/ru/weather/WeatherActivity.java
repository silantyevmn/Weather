package myweather.silantyevmn.ru.weather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

public class WeatherActivity extends AppCompatActivity implements CityListFragment.CityListListener {
    private WeatherPreferencer preferencer;
    private Switch switchPressure, switchWind;
    private MenuItem itemPressure, itemWind;
    //private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (CityEmitter.getCities() == null) {
            CityEmitter.initNewCityParam(WeatherActivity.this);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferencer = new WeatherPreferencer(this,savedInstanceState);
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

        View container = findViewById(R.id.fragment_city_detail);
        CityEmitter.isWind = itemWind.isChecked();
        CityEmitter.isPressure = itemPressure.isChecked();

        if (container == null) {
            Intent intent = new Intent(this, CityDetailActivity.class);
            intent.putExtra(CityDetailActivity.EXTRA_POSITION_ID, positionID);
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
    public void onAddItem(int id, String nameCity) {
        CityEmitter.addNewCity(nameCity);
    }

    @Override
    public void onDeleteItem(int id) {
        CityEmitter.deleteCity(id);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        itemPressure = menu.findItem(R.id.item_pressure);
        itemWind = menu.findItem(R.id.item_wind);
        load();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.item_pressure: {
                itemClickChecked(item, item.isChecked());
                return false;
            }
            case R.id.item_wind: {
                itemClickChecked(item, item.isChecked());
                return false;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void itemClickChecked(MenuItem item, boolean isChecked) {
        item.setChecked(!isChecked);
        save();
    }

    @Override
    protected void onPause() {
        super.onPause();
        save();
    }

    private void save() {
        if (itemWind != null && itemPressure != null) {
            preferencer.save(itemWind.isChecked(), itemPressure.isChecked());
        }
    }

    private void load() {
        if (itemWind != null && itemPressure != null){
            itemWind.setChecked(preferencer.getIsWind());
            itemPressure.setChecked(preferencer.getIsPressure());
        }
    }
}
