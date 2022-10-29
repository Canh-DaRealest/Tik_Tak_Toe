package com.example.tiktaktoe;

import android.content.Context;
import android.content.SharedPreferences;

public class MySharePreference {

    public static final String MY_SHARE_PRE = "MY_SHARE_PRE";

    public static final String SAVE_SOUND = "SAVE_TEXT_SIZE";
    public static final String SAVE_CHESS_STATE = "SAVE_CHESS_STATE";
    public static final String FIRST_INSTALL = "FIRST_INSTALL";

    private static MySharePreference instance;

    public MySharePreference() {
    }

    public static MySharePreference getInstance() {
        if (instance == null) {
            instance = new MySharePreference();
        }
        return instance;
    }

    //kiem tra day co phai lan dau cai ap ko
    public void setBooleanValue(String key, boolean value) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public Boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putStringValue(String key, String value) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, null);
    }

    public void putIntValue(String key, int value) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public int getIntValue(String key) {
        SharedPreferences sharedPreferences = App.getInstance().getSharedPreferences(MY_SHARE_PRE, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key, 0);
    }
}
