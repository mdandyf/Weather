package com.gojek.exercise.weather;

import java.util.Map;

public class MainPresenter {
    private MainView mainView;
    private FindDataWeather findDataWeather;

    MainPresenter (MainView mainView, FindDataWeather findDataWeather) {
        this.mainView = mainView;
        this.findDataWeather = findDataWeather;
    }

    void onResume() {
        if (mainView != null) {
            mainView.showProgress();
        }

        findDataWeather.findItems(this::onFinished);
    }

    public void onFinished(Map<String, Object> items) {
        if (mainView != null) {
            mainView.showData(items);
            mainView.hideProgress();
        }
    }

    void onDestroy() {
        mainView = null;
    }

    public MainView getMainView() {
        return mainView;
    }
}
