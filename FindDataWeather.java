package com.gojek.exercise.weather;

import android.os.Handler;

import java.util.HashMap;
import java.util.Map;

public class FindDataWeather {
    interface OnFinishedListener {
        void onFinished(Map<String, Object> items);
    }

    public void findItems(final OnFinishedListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getData());
            }
        }, 2000);
    }

    private Map<String, Object> getData() {
        Map<String, Object> mapResult = new HashMap<>();

        // get Query Data

        return mapResult;
    }


}
