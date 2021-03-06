package com.gojek.exercise.weather.main;

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
        findDataWeather.checkConnection(this::onCheckingConnection);
    }

    void onCheckingConnection(boolean status) {
        if (mainView != null) {
            if(!status) {
                mainView.showError();
            }
        }
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
