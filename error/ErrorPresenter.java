package com.gojek.exercise.weather.error;

public class ErrorPresenter {
    private ErrorView errorView;

    ErrorPresenter (ErrorView errorView) {
        this.errorView = errorView;
    }

    void onRetry() {
        if(errorView != null) {
            errorView.onRetry();
        }
    }

}
