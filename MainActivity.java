package com.gojek.exercise.weather;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainView {

    private TextView textTemperature;
    private TextView textCity;
    private ListView listView;
    private MainPresenter presenter;
    private MainAdapter mainAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTemperature = (TextView) findViewById(R.id.textTemperature);
        textCity = (TextView) findViewById(R.id.textCity);
        listView = (ListView) findViewById(R.id.listView);
        mainAdapter = new MainAdapter(this);
        mainAdapter.setData(new ArrayList<>(), new ArrayList<>());
        listView.setAdapter(mainAdapter);

        presenter = new MainPresenter(this, new FindDataWeather());
    }

    @Override
    protected void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showData(Map<String, Object> items) {
        List<String> listDay = new ArrayList<>();
        List<String> listTemperature = new ArrayList<>();

        for (Map.Entry entry : items.entrySet()) {
            String key = (String) entry.getKey();
            if(key.equalsIgnoreCase("dataTemperature")) {
                double temperature = (double) entry.getValue();
                textTemperature.setText(Double.toString(temperature) + "\u00B0");
            } else if(key.equalsIgnoreCase("dataCity")) {
                textCity.setText((String) entry.getValue());
            } else if(key.contains("dataDay")) {
                listDay.add((String) entry.getValue());
            } else if(key.contains("dataTemperatureForecast")) {
                double temperatureDetail = (double) entry.getValue();
                listTemperature.add(Double.toString(temperatureDetail) + "\u00B0");
            }
        }

        if(listDay.size() > 0) {
            mainAdapter.setData(listDay, listTemperature);
            mainAdapter.notifyDataSetChanged();
        }


    }

    @Override
    public void showError() {
        Intent progressIntent = new Intent(MainActivity.this,ErrorActivity.class);
        MainActivity.this.startActivity(progressIntent);
    }

    @Override
    public void showProgress() {
        Intent progressIntent = new Intent(MainActivity.this,ProgressActivity.class);
        MainActivity.this.startActivity(progressIntent);
    }

    @Override
    public void hideProgress() {
        ProgressActivity.activity.finish();
    }

}
