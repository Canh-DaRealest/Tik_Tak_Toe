package com.example.tiktaktoe.database.appdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.tiktaktoe.database.dao.NameDAO;
import com.example.tiktaktoe.database.entities.Name;

@Database(entities = {Name.class}, version = 2)
public abstract class AppDB extends RoomDatabase {

    public abstract NameDAO getDAO();
}
