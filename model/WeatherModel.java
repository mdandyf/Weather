package com.gojek.exercise.weather.model;

import android.location.Location;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class WeatherModel implements Serializable{

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @SerializedName("location")
    Location location;

    @SerializedName("current")
    Current current;

    @SerializedName("forecast")
    Forecast forecast;

    @SerializedName("history")
    Forecast history;

    public Location getLocation()
    {
        return location;
    }
    public void setLocation(Location mLocation)
    {
        this.location = mLocation;
    }

    public Current getCurrent()
    {
        return current;
    }
    public void setCurrent(Current mCurrent)
    {
        this.current = mCurrent;
    }

    public Forecast getForecast()
    {
        return forecast;
    }
    public void setForecast(Forecast mForecast)
    {
        this.forecast = mForecast;
    }

    public Forecast getHistory() {return history;}
    public void setHistory(Forecast mHistory)
    {
        this.forecast = mHistory;
    }

}

