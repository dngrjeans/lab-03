package com.example.listycitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements
        AddCityFragment.AddCityDialogueListener, EditCityFragment.EditCityDialogueListener {

    private ArrayList<City> dataList;
    private ListView cityList;
    private ArrayAdapter<City> cityAdapter;
    private int selectedIndex = -1;

    @Override
    public void addCity(City city) {
        cityAdapter.add(city);
        cityAdapter.notifyDataSetChanged();
    }
    @Override
    public void editCity(String name, String province) {
        cityAdapter.getItem(selectedIndex).setName(name);
        cityAdapter.getItem(selectedIndex).setProvince(province);
        cityAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] cities = {
                "Edmonton", "Vancouver", "Moscow",
                "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "New Delhi"
        };
        String[] provinces = {"AB", "BC", "ON"};

        dataList = new ArrayList<>();
        for(int i = 0; (i < cities.length && i < provinces.length); ++i){
            dataList.add(new City(cities[i], provinces[i]));
        }
        
        cityList = findViewById(R.id.city_list);
        cityAdapter = new CityArrayAdapter(this, dataList);
        cityList.setAdapter(cityAdapter);

        FloatingActionButton fab = findViewById(R.id.button_add_city);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AddCityFragment().show(getSupportFragmentManager(), "Add City");
            }
        });
        cityList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedIndex = i;
                new EditCityFragment().show(getSupportFragmentManager(), "Add City");
            }
        });
    }
}