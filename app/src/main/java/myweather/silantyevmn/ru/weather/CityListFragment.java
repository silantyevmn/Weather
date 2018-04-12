package myweather.silantyevmn.ru.weather;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;


public class CityListFragment extends Fragment {
    private final int ORIENTATION = LinearLayout.VERTICAL; //1
    private CityListListener mainActivity;
    private MyAdapter adapter;

    // Создадим интерфейс, через который мы будем передавать номер строки списка, нажатой пользователем
    public interface CityListListener {
        void onListItemClick(int id);
        void onAddItem(int id, String name);
        void onDeleteItem(int id);
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
        adapter=new MyAdapter();
        workoutRecyclerView.setAdapter(adapter);
        //registerForContextMenu(rootView);
        return rootView;// Вернем View фрагмента нашей Activity
    }

    // Класс, который содержит в себе все элементы списка
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener {
        private TextView cityNameTextView;
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int position = getAdapterPosition();
                switch (item.getItemId()) {
                    case R.id.item_menu_info: {
                        showCityScreen(position);
                        break;
                    }
                    case R.id.item_menu_add: {
                        addCity(position,"NewCity");
                        break;
                    }
                    case R.id.item_menu_delete: {
                        deleteCity(position);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
                return false;
            }
        };

        MyViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.fragment_city_list_item, parent, false));
            itemView.setOnClickListener(this);
            cityNameTextView = (TextView) itemView.findViewById(R.id.city_name_text_view);

            registerForContextMenu(itemView);
            itemView.setOnCreateContextMenuListener(this);
        }

        void bind(String value) {
            cityNameTextView.setText(value);
        }

        @Override
        public void onClick(View view) {
            showCityScreen(this.getLayoutPosition());
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.context_menu, contextMenu);
            contextMenu.findItem(R.id.item_menu_info).setOnMenuItemClickListener(onEditMenu);
            contextMenu.findItem(R.id.item_menu_delete).setOnMenuItemClickListener(onEditMenu);
            contextMenu.findItem(R.id.item_menu_add).setOnMenuItemClickListener(onEditMenu);
//            contextMenu.findItem(R.id.item_menu_info).setOnMenuItemClickListener(onEditMenu);
        }
    }

    // Адаптер для RecyclerView
    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            // Обратите внимание, как мы теперь получаем контекст
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new MyViewHolder(inflater, parent);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.bind(CityEmitter.getCities().get(position).getName());
        }

        @Override
        public int getItemCount() {
            return CityEmitter.getCities().size();
        }

    }
    
    private void deleteCity(int positionID){
        mainActivity.onDeleteItem(positionID);
    }

    private void addCity(int positionID,String name) {
        mainActivity.onAddItem(positionID,name);
    }

    private void showCityScreen(int positionID) {
        mainActivity.onListItemClick(positionID);
    }

}
