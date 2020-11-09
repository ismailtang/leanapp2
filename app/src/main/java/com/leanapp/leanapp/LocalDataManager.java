package com.leanapp.leanapp;

import android.content.Context;
import android.content.SharedPreferences;

public class LocalDataManager {

    public void save(Context context, String key, String value) {
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.putString(key, value);
        edit.commit();
    }

    public String get(Context context, String key, String defaultValue) {
        return context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE).getString(key, defaultValue);
    }

    public void clearAll(Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.clear();
        edit.commit();
    }

    public void remove(Context context, String key){
        SharedPreferences sharedPref = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPref.edit();
        edit.remove(key);
        edit.commit();
    }

}
