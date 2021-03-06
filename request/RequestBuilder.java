package com.gojek.exercise.weather.request;

import android.R.string;

import  com.gojek.exercise.weather.request.RequestBlocks.MethodType;
import  com.gojek.exercise.weather.request.RequestBlocks.MethodTypeParemeters;
import  com.gojek.exercise.weather.request.RequestBlocks.ReqestFor;


public class RequestBuilder {

    public  static String PrepareRequest( MethodType methodType,String key, RequestBlocks.GetBy getBy, String value, RequestBlocks.Days ofDays) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+"?key="+key+"&"+ ReqestFor.PrepareQueryParameter(getBy, value)+"&"+ ReqestFor.PrepareDays(ofDays);
    }

    public  static String PrepareRequest( MethodType methodType,String key, RequestBlocks.GetBy getBy, String param, RequestBlocks.GetBy getBy2, String fromDate, RequestBlocks.GetBy getBy3, String toDate) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+"?key="+key+"&"+ ReqestFor.PrepareQueryParameter(getBy, param)+"&"+ ReqestFor.PrepareQueryParameter(getBy2, fromDate)+"&"+ ReqestFor.PrepareQueryParameter(getBy3, toDate);
    }

    public  static String PrepareRequest( MethodType methodType,String key, RequestBlocks.GetBy getBy, String param, RequestBlocks.GetBy getBy2, String param2) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+"?key="+key+"&"+ ReqestFor.PrepareQueryParameter(getBy, param)+"&"+ ReqestFor.PrepareQueryParameter(getBy2, param2);
    }

    public static String PrepareRequest(MethodType methodType, String key, RequestBlocks.GetBy getBy, String value) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+ "?key="+ key+ "&"+ ReqestFor.PrepareQueryParameter(getBy, value);
    }

    public static String PrepareRequestByLatLong(MethodType methodType, String key, String latitude, String longitude, RequestBlocks.Days ofDays) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+ "?key="+ key+ "&"+ ReqestFor.LatLong(latitude,longitude)+ "&"+ ReqestFor.PrepareDays(ofDays);
    }

    public static String PrepareRequestByLatLong(MethodType methodType, String key, String latitude, String longitude) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+ "?key="+ key+ "&"+ ReqestFor.LatLong(latitude, longitude);
    }

    public static String PrepareRequestByAutoIP(MethodType methodType, String key, RequestBlocks.Days ofDays ) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+ "?key="+ key+ "&"+ ReqestFor.AutoIP()+ "&"+ ReqestFor.PrepareDays(ofDays);
    }

    public static String PrepareRequestByAutoIP(MethodType methodType, String key) throws Exception
    {
        return MethodTypeParemeters.GetParameters(methodType)+ "?key="+ key+ "&"+ ReqestFor.AutoIP();
    }
}


