package com.oli.matrixtest.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPref {

    private static final String USERID = "USERID";

    private static final String SESIONID = "sesionID";

    private static SharedPreferences getPreferences (Context c) {

        return c.getApplicationContext().getSharedPreferences("MySharedPreffsFile", Activity.MODE_PRIVATE);

    }
    public static void  addLat(String lat, Context c) {
        getPreferences(c).edit().putString("name",lat).apply();
    }
    public static String getLat( Context c) {
        return getPreferences(c).getString( "name","");
    }

    public static void addLng (String Lng, Context c)  {

        getPreferences(c).edit().putString("SessionID", Lng).apply();
    }

    public static  String getLng (Context c) {

        return getPreferences(c).getString("SessionID", "");
    }
    public static void addCity (String Lng, Context c)  {

        getPreferences(c).edit().putString("CitySearch", Lng).apply();
    }

    public static  String getCity (Context c) {

        return getPreferences(c).getString("CitySearch", "");
    }
}
