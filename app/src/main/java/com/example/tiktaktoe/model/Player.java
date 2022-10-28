package com.example.tiktaktoe.model;

import android.widget.ImageView;

public class Player {
    public static final char X = 'X';
    public static final char O = 'O';

    private String playerName;

    private String playerType;

    private int chessImage;
    private int score;


    public Player(String playerName, int chessImage, int score,String playerType) {
        this.playerName = playerName;
        this.chessImage = chessImage;
        this.score = score;
        this.playerType = playerType;


    }

    public String getPlayerType() {
        return playerType;
    }

    public void setPlayerType(String playerType) {
        this.playerType = playerType;
    }

    public Player() {
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getChessImage() {
        return chessImage;
    }

    public void setChessImage(int chessImage) {
        this.chessImage = chessImage;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "Player{" +
                "playerName='" + playerName + '\'' +
                ", ChessType='" + chessImage + '\'' +
                ", score=" + score +
                ", chessList=" +
                '}';
    }


}
