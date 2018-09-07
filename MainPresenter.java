package com.gojek.exercise.weather;

import java.util.Map;

public class MainPresenter {
    private MainView mainView;
    private FindDataWeather findDataWeather;
    private boolean checkConnection = false;

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

    void onRetry() {
        if (mainView != null) {
            mainView.hideError();
            mainView.showProgress();
        }

        findDataWeather.findItems(this::onFinished);
    }

    void onCheckingConnection(boolean status) {
        if (mainView != null) {
            checkConnection = status;
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
