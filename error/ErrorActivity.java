package com.gojek.exercise.weather.error;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.gojek.exercise.weather.main.MainActivity;
import com.gojek.exercise.weather.R;

public class ErrorActivity extends AppCompatActivity implements ErrorView{

    private ErrorPresenter presenter;
    public static Activity activity;
    public static Activity mainActivity;
    private Button buttonRetry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);
        buttonRetry = (Button) findViewById(R.id.buttonRetry);
        presenter = new ErrorPresenter(this);

        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onRetry();
            }
        });
        activity = this;
    }

    @Override
    public void onRetry() {
        Intent progressIntent = new Intent(ErrorActivity.this,MainActivity.class);
        ErrorActivity.this.startActivity(progressIntent);
        ErrorActivity.this.finish();
    }
}
