package com.gojek.exercise.weather;

import java.util.Map;

public class MainPresenter {
    private MainView mainView;
    private FindDataWeather findDataWeather;

    MainPresenter (MainView mainView, FindDataWeather findDataWeather) {
        this.mainView = mainView;
        this.findDataWeather = findDataWeather;
    }

    void onStart() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findDataWeather.findItems(this::onFinished);
    }

    void onError() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findDataWeather.findItems(this::onFinished);
    }

    public void onFinished(Map<String, Object> items) {
        if (mainView != null) {
            mainView.hideProgress();
            mainView.showData(items);
        }
    }

    void onDestroy() {
        mainView = null;
    }

    public MainView getMainView() {
        return mainView;
    }
}
