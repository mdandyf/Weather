package com.gojek.exercise.weather.request;

import android.app.ProgressDialog;
import android.location.Location;
import android.os.AsyncTask;
import android.text.TextUtils;

import com.gojek.exercise.weather.model.Current;
import com.gojek.exercise.weather.model.Forecast;
import com.gojek.exercise.weather.model.WeatherModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SubmitWeatherRequest extends AsyncTask<String, Void, String>
{
    String responseText="";
    String url = "";
    private WeatherModel weatherModel;
    private Gson gson=new GsonBuilder().create();

    public SubmitWeatherRequest(String url) {
        this.url = url;
    }

    public WeatherModel getWeatherModel() {
        return weatherModel;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... arg0) {

        StringBuilder stringBuilder =new StringBuilder();
        HttpParams httpParameters =new BasicHttpParams();

        DefaultHttpClient httpClient=new DefaultHttpClient();
        httpClient.setParams(httpParameters);
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeader("Cache-Control","no-cache");

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            StatusLine statusLine=response.getStatusLine();
            int statusCode=statusLine.getStatusCode();
            System.out.println("Status Code is=======>"+statusCode);

            if(statusCode==200)
            {
                HttpEntity entity=response.getEntity();
                InputStream inputStream =entity.getContent();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line=reader.readLine())!=null)
                {
                    stringBuilder.append(line);
                }
                inputStream.close();
                responseText=stringBuilder.toString();

            }

            System.out.println("Response==============>"+responseText);


        } catch (IOException e) {
            e.printStackTrace();
        }


        return responseText;
    }

    @Override
    protected void onPostExecute(String result) {

        super.onPostExecute(result);

        if(!TextUtils.isEmpty(responseText))
        {
            JSONObject jObj=null;

            try {
                jObj=new JSONObject(responseText);

                weatherModel = gson.fromJson(jObj.toString(), WeatherModel.class);
                System.out.println("weatherModel==============>"+weatherModel);

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

}

