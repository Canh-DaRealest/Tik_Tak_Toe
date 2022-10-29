package com.example.tiktaktoe;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import java.util.Random;

public class MediaManager {
    public int X_SOUND = R.raw.xclick;
    public int O_SOUND = R.raw.oclick;
    public int CLICK_SOUND = R.raw.clicksound;
    private boolean isSoundOn = true;
    public MediaPlayer mediaPlayer;
    private MySharePreference sharedPreferences;
    public MediaManager(Context context) {
        //getSettingData();

    }
    public boolean getSoundState() {
        return isSoundOn;
    }

    public void setSoundState(boolean BGOn) {
        isSoundOn = BGOn;
    }


    public void playSound(int idSound){


        if (mediaPlayer != null) {
            mediaPlayer.reset();
        }
        mediaPlayer = MediaPlayer.create(App.getInstance(), idSound);


        if (!isSoundOn) {
            mediaPlayer.setVolume(0, 0);
        } else {
            mediaPlayer.setVolume(1, 1);
        }
        mediaPlayer.start();
    }

//    //lay du lieu setting  trong app/data
//    public void getSettingData() {
//        //dung sharepreference de lay du lieu trong file data
//        sharedPreferences = App.getInstance().getSharedPreferences(SAVE_SETTING, Context.MODE_PRIVATE);
//        //lay tung du lieu trong SAVE_SETTING
//        boolean musicSett = sharedPreferences.getBoolean(SAVE_STATE_MUSIC, true);
//        boolean soundSett = sharedPreferences.getBoolean(SAVE_STATE_SOUND, true);
//
//        //trang thai am luong cua game
//        setGameOn(soundSett);
//        setBGOn(musicSett);
//    }

    //luu du lieu setting  vao  app/data
    //thuc hien phương thuc tai onbackpress
//    public void doSaveSetting(boolean musicState, boolean soundState) {
//        isBGOn = musicState;
//        isGameOn = soundState;
//        sharedPreferences = App.getInstance().getSharedPreferences(SAVE_SETTING, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.clear();
//        editor.putBoolean(SAVE_STATE_SOUND, soundState);
//        editor.putBoolean(SAVE_STATE_MUSIC, musicState);
//        editor.apply();
//
//    }


}
