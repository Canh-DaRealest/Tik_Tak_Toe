package com.example.tiktaktoe;

import android.app.Application;

import androidx.room.Room;

import com.example.tiktaktoe.database.appdb.AppDB;

public class App extends Application {
    private AppDB appDB;
    private MediaManager mediaManager;

    private static App instance;
    private Storage storage;

    public Storage getStorage() {
        return storage;
    }

    public MediaManager getMediaManager() {
        return mediaManager;
    }

    public AppDB getAppDB() {
        return appDB;
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        mediaManager = new MediaManager(this);
        storage = new Storage();
        appDB = Room.databaseBuilder(this, AppDB.class, "NameDB").allowMainThreadQueries().build();
    }
}
