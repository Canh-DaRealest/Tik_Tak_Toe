package com.example.tiktaktoe.model;

import android.os.Handler;

import java.util.Random;

public class BotLogic {
    private int[] cellPositionList = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    public boolean botTurn;
    public int cellPos;

    public void setCellPositionList(int[] cellPositionList) {
        this.cellPositionList = cellPositionList;
    }

    public void reset() {
        cellPositionList = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
        botTurn = false;
        cellPos = 0;
    }

    public BotLogic() {

    }

    public void botMove() {
        botTurn = false;

        if (!botTurn) {
            attack();
        }



    }

    private void attack() {


                //horizontal 3
                if (cellPositionList[0] == 2 && cellPositionList[1] == 2 && cellPositionList[2] == 0) {
                    botTurn = true;
                    cellPos = 2;

                } else if (cellPositionList[3] == 2 && cellPositionList[4] == 2 && cellPositionList[5] == 0) {
                    botTurn = true;
                    cellPos = 5;
                } else if (cellPositionList[6] == 2 && cellPositionList[7] == 2 && cellPositionList[8] == 0) {
                    botTurn = true;
                    cellPos = 8;

                    //horizontal 2
                } else if (cellPositionList[0] == 2 && cellPositionList[1] == 0 && cellPositionList[2] == 2) {
                    botTurn = true;
                    cellPos = 1;
                } else if (cellPositionList[3] == 2 && cellPositionList[4] == 0 && cellPositionList[5] == 2) {
                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[6] == 2 && cellPositionList[7] == 0 && cellPositionList[8] == 2) {
                    botTurn = true;
                    cellPos = 7;
                }
                //horizontal 1
                else if (cellPositionList[0] == 0 && cellPositionList[1] == 2 && cellPositionList[2] == 2) {
                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[3] == 0 && cellPositionList[4] == 2 && cellPositionList[5] == 2) {
                    botTurn = true;
                    cellPos = 3;
                } else if (cellPositionList[6] == 0 && cellPositionList[7] == 2 && cellPositionList[8] == 2) {
                    botTurn = true;
                    cellPos = 6;

                }
                //vertical 3
                else if (cellPositionList[0] == 2 && cellPositionList[3] == 2 && cellPositionList[6] == 0) {
                    botTurn = true;
                    cellPos = 6;
                } else if (cellPositionList[1] == 2 && cellPositionList[4] == 2 && cellPositionList[7] == 0) {
                    botTurn = true;
                    cellPos = 7;
                } else if (cellPositionList[2] == 2 && cellPositionList[5] == 2 && cellPositionList[8] == 0) {

                    botTurn = true;
                    cellPos = 8;

                } //vertical 2
                else if (cellPositionList[0] == 2 && cellPositionList[3] == 0 && cellPositionList[6] == 2) {

                    botTurn = true;
                    cellPos = 3;
                } else if (cellPositionList[1] == 2 && cellPositionList[4] == 0 && cellPositionList[7] == 2) {

                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[2] == 2 && cellPositionList[5] == 0 && cellPositionList[8] == 2) {

                    botTurn = true;
                    cellPos = 5;

                }
                //vertical 1
                else if (cellPositionList[0] == 0 && cellPositionList[3] == 2 && cellPositionList[6] == 2) {

                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[1] == 0 && cellPositionList[4] == 2 && cellPositionList[7] == 2) {

                    botTurn = true;
                    cellPos = 1;
                } else if (cellPositionList[2] == 0 && cellPositionList[5] == 2 && cellPositionList[8] == 2) {

                    botTurn = true;
                    cellPos = 2;
                }
//digontal3
                else if (cellPositionList[0] == 2 && cellPositionList[4] == 2 && cellPositionList[8] == 0) {

                    botTurn = true;
                    cellPos = 8;
                } else if (cellPositionList[2] == 2 && cellPositionList[4] == 2 && cellPositionList[6] == 0) {

                    botTurn = true;
                    cellPos = 6;
                }
                //digontal2
                else if (cellPositionList[0] == 2 && cellPositionList[4] == 0 && cellPositionList[8] == 2) {

                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[2] == 2 && cellPositionList[4] == 0 && cellPositionList[6] == 2) {

                    botTurn = true;
                    cellPos = 4;
                }
                //digontal1
                else if (cellPositionList[0] == 0 && cellPositionList[4] == 2 && cellPositionList[8] == 2) {

                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[2] == 0 && cellPositionList[4] == 2 && cellPositionList[6] == 2) {
                    botTurn = true;
                    cellPos = 2;
                }else{
                    defend();
                }

    }

    private void defend() {

                if (cellPositionList[0] == 1 && cellPositionList[1] == 1 && cellPositionList[2] == 0) {
                    botTurn = true;
                    cellPos = 2;

                } else if (cellPositionList[3] == 1 && cellPositionList[4] == 1 && cellPositionList[5] == 0) {
                    botTurn = true;
                    cellPos = 5;
                } else if (cellPositionList[6] == 1 && cellPositionList[7] == 1 && cellPositionList[8] == 0) {

                    botTurn = true;
                    cellPos = 8;
                    //horizontal 2
                } else if (cellPositionList[0] == 1 && cellPositionList[1] == 0 && cellPositionList[2] == 1) {
                    botTurn = true;
                    cellPos = 1;
                } else if (cellPositionList[3] == 1 && cellPositionList[4] == 0 && cellPositionList[5] == 1) {
                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[6] == 1 && cellPositionList[7] == 0 && cellPositionList[8] == 1) {

                    botTurn = true;
                    cellPos = 7;
                }
                //horizontal 1
                else if (cellPositionList[0] == 0 && cellPositionList[1] == 1 && cellPositionList[2] == 1) {

                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[3] == 0 && cellPositionList[4] == 1 && cellPositionList[5] == 1) {

                    botTurn = true;
                    cellPos = 3;
                } else if (cellPositionList[6] == 0 && cellPositionList[7] == 1 && cellPositionList[8] == 1) {

                    botTurn = true;
                    cellPos = 6;
                }
                //vertical 3
                else if (cellPositionList[0] == 1 && cellPositionList[3] == 1 && cellPositionList[6] == 0) {

                    botTurn = true;
                    cellPos = 6;
                } else if (cellPositionList[1] == 1 && cellPositionList[4] == 1 && cellPositionList[7] == 0) {

                    botTurn = true;
                    cellPos = 7;
                } else if (cellPositionList[2] == 1 && cellPositionList[5] == 1 && cellPositionList[8] == 0) {

                    botTurn = true;
                    cellPos = 8;

                } //vertical 2
                else if (cellPositionList[0] == 1 && cellPositionList[3] == 0 && cellPositionList[6] == 1) {

                    botTurn = true;
                    cellPos = 3;
                } else if (cellPositionList[1] == 1 && cellPositionList[4] == 0 && cellPositionList[7] == 1) {

                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[2] == 1 && cellPositionList[5] == 0 && cellPositionList[8] == 1) {


                    botTurn = true;
                    cellPos = 5;
                }
                //vertical 1
                else if (cellPositionList[0] == 0 && cellPositionList[3] == 1 && cellPositionList[6] == 1) {

                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[1] == 0 && cellPositionList[4] == 1 && cellPositionList[7] == 1) {

                    botTurn = true;
                    cellPos = 1;
                } else if (cellPositionList[2] == 0 && cellPositionList[5] == 1 && cellPositionList[8] == 1) {

                    botTurn = true;
                    cellPos = 2;
                }
//digontal3
                else if (cellPositionList[0] == 1 && cellPositionList[4] == 1 && cellPositionList[8] == 0) {

                    botTurn = true;
                    cellPos = 8;
                } else if (cellPositionList[2] == 1 && cellPositionList[4] == 1 && cellPositionList[6] == 0) {

                    botTurn = true;
                    cellPos = 6;
                }
                //digontal2
                else if (cellPositionList[0] == 1 && cellPositionList[4] == 0 && cellPositionList[8] == 1) {

                    botTurn = true;
                    cellPos = 4;
                } else if (cellPositionList[2] == 1 && cellPositionList[4] == 0 && cellPositionList[6] == 1) {

                    botTurn = true;
                    cellPos = 4;
                }
                //digontal1
                else if (cellPositionList[0] == 0 && cellPositionList[4] == 1 && cellPositionList[8] == 1) {

                    botTurn = true;
                    cellPos = 0;
                } else if (cellPositionList[2] == 0 && cellPositionList[4] == 1 && cellPositionList[6] == 1) {

                    botTurn = true;
                    cellPos = 2;
                }else{
                    randomMove();
                }

    }

    private void randomMove() {

        botTurn = false;
        int num = random();
        if (isCellSelected(num)) {

            if (num == 0) {
                cellPos = 0;
                botTurn = true;
            }
            if (num == 1) {

                cellPos = 1;
                botTurn = true;
            }
            if (num == 2) {
                cellPos = 2;
                botTurn = true;
            }
            if (num == 3) {
                cellPos = 3;
                botTurn = true;
            }
            if (num == 4) {
                cellPos = 4;
                botTurn = true;
            }
            if (num == 5) {
                cellPos = 5;
                botTurn = true;

            }
            if (num == 6) {
                cellPos = 6;
                botTurn = true;
            }
            if (num == 7) {
                cellPos = 7;
                botTurn = true;
            }
            if (num == 8) {
                cellPos = 8;
                botTurn = true;
            }

        } else {
            randomMove();
        }

    }


    private int random() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private boolean isCellSelected(int cellPos) {
        return this.cellPositionList[cellPos] == 0;

    }
}
