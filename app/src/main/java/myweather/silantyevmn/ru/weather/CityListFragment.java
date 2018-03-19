package myweather.silantyevmn.ru.weather;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CityListFragment extends Fragment {
    private final int ORIENTATION = LinearLayout.VERTICAL; //1
    private CityListListener mainActivity;

    // Создадим интерфейс, через который мы будем передавать номер строки списка, нажатой пользователем
    public interface CityListListener {
        void onListItemClick(int id);
    }

    // Инстантиируем наш интерфейс
    @Override
    public void onAttach(Context context) {
        mainActivity = (CityListListener) context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_city_list, container, false);
        // Найдем наш RecyclerView в frament_list
        RecyclerView workoutRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        // Создадим LinearLayoutManager. Обратите внимание, как мы теперь получаем контекст
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        // Обозначим ориентацию
        layoutManager.setOrientation(ORIENTATION);
        // Назначим нашему RecyclerView созданный ранее layoutManager
        workoutRecyclerView.setLayoutManager(layoutManager);
        // Назначим нашему RecyclerView адаптер
        workoutRecyclerView.setAdapter(new MyAdapter(CityEmitter.getCities()));
        // Вернем View фрагмента нашей Activity
        return rootView;// Вернем View фрагмента нашей Activity
    }

    // Класс, который содержит в себе все элементы списка
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView cityNameTextView;

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_city_list_item, parent, false));
            itemView.setOnClickListener(this);
            cityNameTextView = (TextView) itemView.findViewById(R.id.city_name_text_view);
        }

        void bind(String value) {
            cityNameTextView.setText(value);
        }

        @Override
        public void onClick(View view) {
            showCityScreen(this.getLayoutPosition());
        }
    }

    private void showCityScreen(int positionID) {
        mainActivity.onListItemClick(positionID);
    }

    // Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
        private City[] cities;

        MyAdapter(City[] cities){
            this.cities=cities;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Обратите внимание, как мы теперь получаем контекст
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(cities[position].getName());
        }

        @Override
        public int getItemCount() {
            return cities.length;
        }
    }

}
