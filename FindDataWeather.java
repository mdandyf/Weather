package com.gojek.exercise.weather;

import android.os.AsyncTask;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.util.Log;

import com.gojek.exercise.weather.model.Day;
import com.gojek.exercise.weather.model.WeatherModel;
import com.gojek.exercise.weather.request.RequestBlocks;
import com.gojek.exercise.weather.request.RequestBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.gojek.exercise.weather.request.RequestBlocks.Days.Seven;
import static com.gojek.exercise.weather.request.RequestBlocks.GetBy.CityName;
import static com.gojek.exercise.weather.request.RequestBlocks.GetBy.NumDays;

public class FindDataWeather {

    private static final String CITY = "Bangalore";
    private static final String APIURL = "http://api.apixu.com/v1";

    private WeatherModel weatherModelCurrent;
    private WeatherModel weatherModelHistory;

    private Gson gson=new GsonBuilder().create();
    private ConnectInternet connect;
    private boolean statusConnection = false;

    private String responseText = "";
    Map<String, Object> mapResult = new ConcurrentHashMap<>();

    interface OnFinishedListener {
        void onFinished(Map<String, Object> items);
    }
    public interface NetworkListener {
        void onCheckingError(boolean status);
    }

    public void findItems(final OnFinishedListener listener) {

        queryCurrentData();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getDataCurrent());
                queryForecastData();
            }
        }, 3000);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onFinished(getDataForecast());
            }
        }, 6000);
    }

    public void checkConnection(final NetworkListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                listener.onCheckingError(statusConnection);
            }
        }, 3000);
    }

    private void queryCurrentData() {
        try {
            String url = "";
            // Query Data Current
            url = APIURL + RequestBuilder.PrepareRequest(RequestBlocks.MethodType.Current, "d7597bc705d6407b83155322180609", CityName, CITY);
            Log.d("Query1", url);
            connect = new ConnectInternet();
            connect.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void queryForecastData() {
        try {
            // Query Data Forecast for 7 days
            String url = "";
            String numDays = Integer.toString(7);
            url = APIURL + RequestBuilder.PrepareRequest(RequestBlocks.MethodType.Forecast, "d7597bc705d6407b83155322180609", CityName, CITY, NumDays, numDays);
            Log.d("Query2", url);
            connect = new ConnectInternet();
            connect.execute(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private synchronized Map<String, Object> getDataCurrent() {

        JSONObject jObj;

        try {
            synchronized (responseText) {
                if(!TextUtils.isEmpty(responseText))
                {
                    try {
                        jObj=new JSONObject(responseText);
                        weatherModelCurrent = gson.fromJson(jObj.toString(), WeatherModel.class);
                        System.out.println("weatherModelCurrent==============>"+weatherModelCurrent);
                        mapResult.put("dataTemperature", weatherModelCurrent.getCurrent().getTempC());
                        mapResult.put("dataCity", CITY);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapResult;
    }

    private synchronized Map<String, Object> getDataForecast() {
        JSONObject jObj;

        try {
            synchronized (responseText) {
                if(!TextUtils.isEmpty(responseText))
                {
                    try {
                        jObj=new JSONObject(responseText);
                        weatherModelHistory = gson.fromJson(jObj.toString(), WeatherModel.class);
                        System.out.println("weatherModelHistory==============>"+weatherModelHistory);
                        for(int i = 0; i < weatherModelHistory.getForecast().getForecastday().size(); i++) {
                            String dataDayName = "dataDay" + Integer.toString(i);
                            mapResult.put(dataDayName, convertDateToDay(weatherModelHistory.getForecast().getForecastday().get(i).getDate()));
                            String dataTemperatureForecast = "dataTemperatureForecast" + Integer.toString(i);
                            mapResult.put(dataTemperatureForecast, weatherModelHistory.getForecast().getForecastday().get(i).getDay().getAvgtempC());
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mapResult;
    }

    private String convertDateToDay(String date) {
        try {
            SimpleDateFormat format1=new SimpleDateFormat("yyyy-MM-dd");
            Date dt1= format1.parse(date);
            SimpleDateFormat format2=new SimpleDateFormat("EEEE");
            String finalDay=format2.format(dt1);
            return finalDay;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private class ConnectInternet extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... url) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(url[0])
                    .build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                    Log.d("Request", request.toString());
                    e.printStackTrace();
                    statusConnection = false;
                }

                @Override
                public void onResponse(Response response) throws IOException {
                    if (!response.isSuccessful()) {
                        throw new IOException("Unexpected code " + response);
                    } else {
                        synchronized (responseText) {
                            responseText = response.body().string();
                        }
                        statusConnection = true;
                    }
                }
            });
            return null;
        }

    }


}
