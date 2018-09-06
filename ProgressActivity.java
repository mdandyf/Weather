package com.gojek.exercise.weather;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class ProgressActivity extends AppCompatActivity {

    public static Activity activity;
    Animation rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);
        rotation = AnimationUtils.loadAnimation(this, R.anim.animation);
        findViewById(R.id.imageView).startAnimation(rotation);
        activity = this;
    }
}

