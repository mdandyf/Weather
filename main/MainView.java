package com.gojek.exercise.weather.main;

import java.util.Map;

public interface MainView {
    void showData(Map<String, Object> items);
    void showError();
    void showProgress();
    void hideProgress();
}
