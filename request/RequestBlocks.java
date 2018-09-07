package com.gojek.exercise.weather.request;

import android.text.TextUtils;

public class RequestBlocks {

    public static class MethodTypeParemeters
    {

        public static String getCurrent()
        {
            return "/current.json";
        }

        public static String getForecast()
        {
            return "/forecast.json";
        }

        public static String getHistory()
        {
            return "/history.json";
        }

        public static String getSearch()
        {
            return "/search.json";
        }

        public static String GetParameters(MethodType methodType) throws Exception
        {
            String methodPara = "";
            switch (methodType)
            {
                case Current:
                    methodPara = MethodTypeParemeters.getCurrent();
                    break;
                case Forecast:
                    methodPara = MethodTypeParemeters.getForecast();
                    break;
                case History:
                    methodPara = MethodTypeParemeters.getHistory();
                    break;
                case Search:
                    methodPara = MethodTypeParemeters.getSearch();
                    break;

            }
            if (TextUtils.isEmpty(methodPara) || methodPara == null)
            {
                throw new Exception("Invalid Method Type");
            }
            else
            {
                return methodPara;

            }
        }


    }

    public enum MethodType
    {
        Current,
        Forecast,
        History,
        Search
    }

    public enum Days
    {
        One,
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten
    }

    public enum GetBy
    {
        CityName,
        Zip,
        PostCode,
        PostalCode,
        Metar,
        iata,
        IPAddress,
        DateFrom,
        DateEnd,
        NumDays
    }

    public static class ReqestFor
    {
        public static String City(String cityName)
        {
            return "q=" + cityName;
        }

        public static String Zip(String zip)
        {
            return "q=" + zip;
        }

        public static String PostCode(String postcode)
        {
            return "q=" + postcode;
        }

        public static String PostalCode(String postalCode)
        {
            return "q=" + postalCode;
        }

        public static String LatLong(String latitude, String longitude)
        {
            return "q=" + latitude + "," + longitude;
        }

        public static String Metar(String metar)
        {
            return "q=metar:" + metar;
        }

        public static String iata(String iata)
        {
            return "q=iata:" + iata;
        }

        public static String AutoIP()
        {
            return "q=auto:ip";
        }

        public static String IPAddress(String IP)
        {
            return "q=" + IP;
        }

        public static String DateFrom(String date) { return "dt=" + date; }

        public static String DateEnd(String date) { return "end_dt=" + date; }

        public static String NumDays(String date) { return "days=" + date; }

        public static String PrepareQueryParameter(GetBy getby, String value)
        {
            String queryParameter = "";
            switch (getby)
            {
                case CityName:
                    queryParameter = ReqestFor.City(value);
                    break;
                case Zip:
                    queryParameter = ReqestFor.Zip(value);
                    break;
                case PostCode:
                    queryParameter = ReqestFor.PostCode(value);
                    break;
                case PostalCode:
                    queryParameter = ReqestFor.PostalCode(value);
                    break;
                case Metar:
                    queryParameter = ReqestFor.Metar(value);
                    break;
                case iata:
                    queryParameter = ReqestFor.iata(value);
                    break;
                case IPAddress:
                    queryParameter = ReqestFor.IPAddress(value);
                    break;
                case DateFrom:
                    queryParameter = ReqestFor.DateFrom(value);
                    break;
                case DateEnd:
                    queryParameter = ReqestFor.DateEnd(value);
                    break;
                case NumDays:
                    queryParameter = ReqestFor.NumDays(value);
                    break;
            }
            return queryParameter;
        }
        public static String PrepareDays(Days days)
        {
            return "days=" + days;
        }


    }


}
