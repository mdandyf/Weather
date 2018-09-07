package com.gojek.exercise.weather;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ErrorActivity extends AppCompatActivity implements ErrorView {

    private MainPresenter presenter;
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        activity = this;
    }

    @Override
    public void onRetry() {
        presenter.onRetry();
    }
}
