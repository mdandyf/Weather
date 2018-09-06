package com.gojek.exercise.weather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ErrorActivity extends AppCompatActivity implements ErrorView {

    private MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
    }

    @Override
    public void onClick() {
        presenter.onError();
    }
}
