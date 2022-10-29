package com.example.tiktaktoe.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.tiktaktoe.database.entities.Name;

import java.util.List;

@Dao
public interface NameDAO {

    @Query("SELECT * FROM Name")
    List<Name> getName();


    @Insert
    void insertAll(Name name);

}
