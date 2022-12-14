package com.example.tiktaktoe.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.bot.DifficultBot;
import com.example.tiktaktoe.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainVM extends ViewModel {

    private DifficultBot botLogic;
    private boolean isBotPlay = false;
    Player player1, player2;
    private int firstPlayer = 1;

    private final MutableLiveData<Integer> player1LiveScore = new MutableLiveData<>();
    private final MutableLiveData<Integer> player2LiveScore = new MutableLiveData<>();
    private MutableLiveData<String> turnLiveTxt = new MutableLiveData<>();


    private  List<int[]> winTypeList = new ArrayList<>(); //{012 345...}

    public int[] cellPosArray = {0, 0, 0, 0, 0, 0, 0, 0, 0}; // an array of cell's  position
    public int totalSelectedBoxes = 0; //to check draw
    private int playerTurn = 1; //define who's first
    private int[] winType; //012;

    public MutableLiveData<Integer> getPlayer1LiveScore() {
        return player1LiveScore;
    }

    public MutableLiveData<String> getTurnLiveTxt() {
        return turnLiveTxt;
    }

    public void setTurnLiveTxt(MutableLiveData<String> turnLiveTxt) {
        this.turnLiveTxt = turnLiveTxt;
    }

    public MutableLiveData<Integer> getPlayer2LiveScore() {
        return player2LiveScore;
    }


    public int[] getCellPosArray() {
        return cellPosArray;
    }


    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

    public int[] getWinType() {
        return winType;
    }


    public DifficultBot getBotLogic() {
        return botLogic;
    }


    public boolean isBotPlay() {
        return isBotPlay;
    }

    public void setBotPlay(boolean botPlay) {
        isBotPlay = botPlay;
    }

    public Player getPlayer1() {
        return player1;
    }


    public Player getPlayer2() {
        return player2;
    }


    public void initPlayer() {


        int image1 = 0, image2 = 0;
        if (App.getInstance().getStorage().chessType.equals("C??? ??i???n")) {
            image1 = R.drawable.classicx;
            image2 = R.drawable.classico;
        } else if (App.getInstance().getStorage().chessType.equals("Hi???n ?????i")) {
            image1 = R.drawable.modern_x;
            image2 = R.drawable.modern_o;
        } else if (App.getInstance().getStorage().chessType.equals("C??ch ??i???u")) {
            image1 = R.drawable.newx;
            image2 = R.drawable.newo;
        } else if (App.getInstance().getStorage().chessType.equals("Ho???t h??nh")) {
            image1 = R.drawable.cartoonx;
            image2 = R.drawable.cartoono;
        } else if (App.getInstance().getStorage().chessType.equals("V?? tr???")) {
            image1 = R.drawable.starx;
            image2 = R.drawable.moono;
        } else if (App.getInstance().getStorage().chessType.equals("T??? nhi??n")) {
            image1 = R.drawable.naturex;
            image2 = R.drawable.natureo;
        }

        updateWintypeList();

//        botLogic = new BotLogic();
        botLogic = new DifficultBot();

        botLogic.updateWintypeList();
        //   botLogic.setCellPositionArr(cellPosArray);


        if (App.getInstance().getStorage().playWithBot) {
            player1 = new Player("B???n", image1, 0, "x");
            player2 = new Player("M??y", image2, 0, "o");
            setBotPlay(true);
        } else {
            player1 = new Player(App.getInstance().getStorage().playerName1, image1, 0, "x");
            player2 = new Player(App.getInstance().getStorage().playerName2, image2, 0, "o");
        }

    }

    public void randomFirtPlayer() {

        playerTurn = new Random().nextInt(2) + 1;

    }

    public boolean isCellSelected(int cellPos) {
        return this.cellPosArray[cellPos] == 0;
//&& botLogic.getCellPositionArr()[cellPos] == 0
    }

    public void updateCellPosition(int selectedCellPos, int playerTurn) {
        cellPosArray[selectedCellPos] = playerTurn;
    }

    public boolean checkWin(int playerTurn) {

        for (int i = 0; i < winTypeList.size(); i++) {
             int[] winType = winTypeList.get(i);//012  345 678 ...
            if (cellPosArray[winType[0]] == playerTurn && cellPosArray[winType[1]] == playerTurn && cellPosArray[winType[2]] == playerTurn) {
                this.winType = winType;
               return true;
            }

        }
        return false;
    }

    public void updateLiveText(String txt) {


        turnLiveTxt.postValue(txt);

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


    public void resetBoard() {
        cellPosArray = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (firstPlayer == 1) {
            firstPlayer = 2;
            playerTurn = 2;
        } else if (firstPlayer == 2) {
            firstPlayer = 1;
            playerTurn = 1;
        }
        totalSelectedBoxes = 0;
        botLogic.totalSelectedBoxes = 0;
    }


    public void updatePlayerScore(Player player) {

        if (player == player1) {
            player1.setScore(player1.getScore() + 1);
            player1LiveScore.postValue(player1.getScore());
        } else if (player == player2) {
            player2.setScore(player2.getScore() + 1);
            player2LiveScore.postValue(player2.getScore());
        }

    }

    public int doBotTurn() {
        return botLogic.findBestMove(cellPosArray);
    }

    public void updateTotalSelectedBox() {
        totalSelectedBoxes++;

        if (isBotPlay) {
            getBotLogic().totalSelectedBoxes++;
        }


    }
}
