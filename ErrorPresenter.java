package com.gojek.exercise.weather;

import android.content.Intent;

import java.util.Map;

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
