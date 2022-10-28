package com.example.tiktaktoe.model;

import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private Player player1;
    private Player player2;
    private final List<int[]> winTypeList = new ArrayList<>();
    private final List<ImageView> cellList = new ArrayList<>();
    public int[] cellPosArray = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public int totalSelectedBoxes = 0;
    private int playerTurn;
    private int[] winType;

    public int[] getWinType() {
        return winType;
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public List<int[]> getWinTypeList() {
        return winTypeList;
    }

    public List<ImageView> getCellList() {
        return cellList;
    }

    public int[] getCellPosArray() {
        return cellPosArray;
    }

    public void setCellPosArray(int[] cellPosArray) {
        this.cellPosArray = cellPosArray;
    }

    public int getTotalSelectedBoxes() {
        return totalSelectedBoxes;
    }

    public void setTotalSelectedBoxes(int totalSelectedBoxes) {
        this.totalSelectedBoxes = totalSelectedBoxes;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public Board(Player player1, Player player2, int[] cellPos, int totalSelectedBoxes, int playerTurn) {
        this.player1 = player1;
        this.player2 = player2;
        this.cellPosArray = cellPos;
        this.totalSelectedBoxes = totalSelectedBoxes;
        this.playerTurn = playerTurn;
    }

    public Board() {
    }

    public void updateWintypeList() {

        winTypeList.add(new int[]{0, 1, 2});
        winTypeList.add(new int[]{3, 4, 5});
        winTypeList.add(new int[]{6, 7, 8});
        winTypeList.add(new int[]{0, 3, 6});
        winTypeList.add(new int[]{1, 4, 7});
        winTypeList.add(new int[]{2, 5, 8});
        winTypeList.add(new int[]{0, 4, 8});
        winTypeList.add(new int[]{2, 4, 6});
    }

    public boolean isCellSelected(int cellPos) {
        return this.cellPosArray[cellPos] == 0;

    }


    public boolean checkWin(int playerTurn) {
        boolean result = false;
        for (int i = 0; i < winTypeList.size(); i++) {
            final int[] winType = winTypeList.get(i);//012  345 678 ...
            if (cellPosArray[winType[0]] == playerTurn && cellPosArray[winType[1]] == playerTurn && cellPosArray[winType[2]] == playerTurn) {

                result = true;
                this.winType = winType;
            }

        }
        return result;
    }

    public void resetBoard() {
        cellPosArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};


        if (playerTurn == 1) {
            playerTurn = 2;

        } else {
            playerTurn = 1;

        }
        totalSelectedBoxes = 0;

    }

    public void updateCellPosition(int selectedCellPos) {
        cellPosArray[selectedCellPos] = playerTurn;
    }


}
