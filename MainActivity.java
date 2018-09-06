package com.gojek.exercise.weather;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainView  {

    private TextView textTemperature;
    private TextView textCity;
    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTemperature = (TextView) findViewById(R.id.textTemperature);
        textCity = (TextView) findViewById(R.id.textCity);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showData(Map<String, Object> items) {

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onError() {

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
