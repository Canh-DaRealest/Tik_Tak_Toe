package com.example.tiktaktoe.bot;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class DifficultBot {


    private static final int INFINITY = Integer.MAX_VALUE;
    //   private int[] cellPositionArr = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private  List<int[]> winTypeList = new ArrayList<>();
    public int totalSelectedBoxes = 0;

//    public int[] getCellPositionArr() {
//        return cellPositionArr;
//    }


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


    public int findBestMove(int[] cellPositionArr) {

        //bot go first
        if (countTotalChessInBoard(cellPositionArr)==0) {

            int bestMove = new Random().nextInt(9);
            Log.e("ddddddd", "\nbestmove: " + bestMove);
            return bestMove;
        }
        //bot second turn
        if (countTotalChessInBoard(cellPositionArr)==1) {
            List<Integer> newList = new ArrayList<>();
            for (int i = 0; i < cellPositionArr.length; i++
            ) {
                if (cellPositionArr[i] == 0) {
                    newList.add(i);
                }
            }

            Collections.shuffle(newList);
            int bestMove = newList.get(0);
            return bestMove;
        }
        int bestMove = 0;
        int bestScore = -INFINITY;
        for (int i = 0; i < cellPositionArr.length; i++) {

            if (cellPositionArr[i] == 0) {
                cellPositionArr[i] = 2;
                //   Log.e("ddddddd", "findBestMove: " + i);

                int score = minimax(cellPositionArr, 0, false);

                cellPositionArr[i] = 0;

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = i;
                }
            }

        }
        Log.e("ddddddd", "\nbestmove: " + bestMove);
        return bestMove;
    }

    private int countTotalChessInBoard(int[] cellPositionArr) {
        int count = 0;
        for (int j : cellPositionArr) {
            if (j != 0) {
                count++;
            }
        }
        return count ;
    }


    private int minimax(int[] arr, int deptm, boolean isMaximize) {

        if (checkWin(arr)) {
            return 10;
        }

        if (checkLose(arr)) {
            return -10;
        }

        if (countTotalChessInBoard(arr)==9) {
            return 0;
        }

        int bestScore;
        if (isMaximize) {
            bestScore = -INFINITY;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 0) {
                    arr[j] = 2;
                    int score = minimax(arr, deptm + 1, false);
                    arr[j] = 0;

                    bestScore = Math.max(score, bestScore);
                }
            }

        } else {
            bestScore = INFINITY;
            for (int j = 0; j < arr.length; j++) {
                if (arr[j] == 0) {
                    arr[j] = 1;
                    int score = minimax(arr, deptm + 1, true);
                    arr[j] = 0;

                    bestScore = Math.min(score, bestScore);

                }

            }
        }
        return bestScore;


    }

//    public void updateCellPosition(int selectedCellPos, int playerTurn) {
//        cellPositionArr[selectedCellPos] = playerTurn;
//    }

    private boolean checkDraw(int[] arr) {
        int count = 0;
        for (int j : arr) {
            if (j != 0) {
                count++;
            }
        }

        return count == 9;
    }


    private boolean checkWin(int[] arr) {

        for (int i = 0; i < winTypeList.size(); i++) {
             int[] winType = winTypeList.get(i);//012  345 678 ...
            if (arr[winType[0]] == 2 && arr[winType[1]] == 2 && arr[winType[2]] == 2) {
                return true;
            }
        }
        return false;

    }

    private boolean checkLose(int[] arr) {

        for (int i = 0; i < winTypeList.size(); i++) {
             int[] winType = winTypeList.get(i);//012  345 678 ...
            if (arr[winType[0]] == 1 && arr[winType[1]] == 1 && arr[winType[2]] == 1) {
                return true;
            }
        }
        return false;

    }


//    public void reset() {
//        cellPositionArr = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
//        cellPos = 0;
//    }
//
//    public void setCellPositionArr(int[] cellPositionArr) {
//        this.cellPositionArr = cellPositionArr;
//    }


}
