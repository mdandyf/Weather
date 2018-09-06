package com.gojek.exercise.weather;

import android.os.Handler;

import com.gojek.exercise.weather.model.WeatherModel;
import com.gojek.exercise.weather.request.RequestBlocks;
import com.gojek.exercise.weather.request.RequestBuilder;
import com.gojek.exercise.weather.request.SubmitWeatherRequest;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.Map;

import static com.gojek.exercise.weather.request.RequestBlocks.GetBy.CityName;

public class FindDataWeather {

    private static final String CITY = "Bangalore";
    private static final String DAYS = "Six";

    private String APIURL = "http://api.apixu.com/v1";
    private WeatherModel weatherModel;
    private Gson gson=new GsonBuilder().create();

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
        WeatherModel weatherModel;
        // get Query Data

        try {
            String url = APIURL + RequestBuilder.PrepareRequest(RequestBlocks.MethodType.Forecast, "d7597bc705d6407b83155322180609", CityName, CITY);

            System.out.println("url==========>"+url);
            SubmitWeatherRequest weatherRequest = new SubmitWeatherRequest(url);
            weatherRequest.execute();
            weatherModel = weatherRequest.getWeatherModel();

            // Start Request for Specific City
            mapResult.put("dataCity", CITY);
            mapResult.put("dataTemperature", weatherModel.getCurrent().getTempC());

            // Start Request for forecast for Six days
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapResult;
    }




}
