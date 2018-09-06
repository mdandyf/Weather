package com.gojek.exercise.weather;

import java.util.Map;

public interface MainView {
    void showData(Map<String, Object> items);
    void onRefresh();
    void onError();
    void showProgress();
    void hideProgress();
}