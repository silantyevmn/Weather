package myweather.silantyevmn.ru.weather;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class CityDetailActivity extends AppCompatActivity {
    public static final String EXTRA_POSITION_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_detail);
        CityDetailFragment detailFragment = new CityDetailFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        int id = getIntent().getIntExtra(EXTRA_POSITION_ID, 0);
        detailFragment.setPositionID(id);
        transaction.add(R.id.details_fragment, detailFragment);
        transaction.commit();
    }

}
