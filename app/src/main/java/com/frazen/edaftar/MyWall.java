package com.frazen.edaftar;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MyWall  extends Application {

    private static MyWall mInstance;
   // public static final String URL = AppConstant.BASEURL;
   public static SharedPreferences preference;
   public static SharedPreferences.Editor editor;
    private static String ACCESSTOKEN = "accessToken";
    private static String REFRESHTOKEN = "refreshToken";
    private static String EXPIREDATE = "ExpiteDate";
    private static Gson gson = new Gson();
    private static String USERLOGINID = "USERLOGINID";
    public static  String ROLES = "roles";
    private static String ID="Id";
    private static String ACTIVE="active";
    private static String USERNAME="userName";



    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        preference = this.getSharedPreferences("MyWall", 0);
        editor = preference.edit();
    }//onCreate()

    public static void setAccessToken(String accessToken, String refreshToken, int expires_in) {
        editor.putString(ACCESSTOKEN, accessToken);
        editor.putString(REFRESHTOKEN, refreshToken);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, expires_in);
        editor.putLong(EXPIREDATE, calendar.getTime().getTime());
        editor.commit();
    }

    @Override
    public void onTerminate() {

        super.onTerminate();
    }

    public static Context getApplication() {
        return mInstance.getApplicationContext();
    }

    public static void clearCache() {
        SharedPreferences.Editor editor = preference.edit();
        editor.clear();
        editor.apply();
    }

    public static boolean validateAccessToken() {
        Log.e("veera", String.valueOf(preference.getLong(EXPIREDATE, 0)));
        long l = preference.getLong(EXPIREDATE, 0);
        Date expireDate = new Date(l);
        Log.e("veera",expireDate.toString());
        Calendar expireDateOnCal = Calendar.getInstance();
        expireDateOnCal.setTime(expireDate);
        Calendar currentDateOnCal = Calendar.getInstance();
        return expireDate.after(currentDateOnCal.getTime());
    }

    public static String getAccessToken() {
        return preference.getString(ACCESSTOKEN, "NA");
    }

    public static String getREFRESHTOKEN() {
        return preference.getString(REFRESHTOKEN, "NA");
    }

    public static void setLoginId(String username) {
        editor.putString(USERLOGINID, username);
        editor.commit();
    }
    public static String getLoginId() {
        return preference.getString(USERLOGINID, "NA");
    }


    public static void setUserInfo(String id, Boolean active, String username) {
        editor.putString(ID, id);
        editor.putBoolean(ACTIVE, active);
        editor.putString(USERNAME, username);
        editor.commit();
    }

    public static String getID() {
        return preference.getString(ID, "");
    }

    public static boolean getACTIVE() {
        return preference.getBoolean(ACTIVE, false) ;
    }

    public static String getUSERNAME() {
        return preference.getString(USERNAME, "");
    }

    public static void setRoles(List<String> roles) {
        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(roles);
        editor.putString(ROLES, jsonFavorites);
        editor.commit();
    }
    public static List<String> getROLES() {

        List<String> roles;

        if (preference.contains(ROLES)) {
            String jsonFavorites = preference.getString(ROLES, null);
            Gson gson = new Gson();
            Type type = new TypeToken<List<String>>() {
            }.getType();
            roles = gson.fromJson(jsonFavorites, type);
        } else
        {
            return null;
        }
        return roles;    }

}
