package com.example.tiktaktoe.database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Name {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "_name")
    public String name;


    public Name() {
    }

    public Name(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Name(String name) {
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Name{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
