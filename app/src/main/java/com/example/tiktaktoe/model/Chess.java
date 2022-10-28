package com.example.tiktaktoe.model;

public class Chess {
    private int index;
    private String type;

    public Chess(int index, String type) {
        this.index = index;
        this.type = type;
    }

    public Chess() {
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
