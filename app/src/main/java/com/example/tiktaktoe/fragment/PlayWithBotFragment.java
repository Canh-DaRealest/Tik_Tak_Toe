package com.example.tiktaktoe.fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.tiktaktoe.R;
import com.example.tiktaktoe.activity.MainActivity;
import com.example.tiktaktoe.databinding.FragmentPlayBinding;
import com.example.tiktaktoe.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class PlayWithBotFragment extends BaseFragment<FragmentPlayBinding, CommonVM> {

    private ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8;


    private final List<int[]> winTypeList = new ArrayList<>();
    private final List<ImageView> listBttn = new ArrayList<>();
    private int[] cellPositionList = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalSelectedBoxes = 0;
    private int playerTurn = 1;
    private int player1Score = 0;
    private int player2Score = 0;
    private int firstPlayer = 1;
    private boolean isBotTurn = false;
    Animation animation = new AlphaAnimation(1.0f, 0.0f);

    public static final String TAG = PlayWithBotFragment.class.getName();

    @Override
    protected FragmentPlayBinding initViewBinding(LayoutInflater inflater) {
        return FragmentPlayBinding.inflate(inflater);
    }


    @Override
    protected void initView() {
        binding.player1Name.setText("Bạn");
        binding.player2Name.setText("Máy");

        initBoardCell();
        playGame();


    }

    private void playGame() {

//playerturn
        if (!isBotTurn) {
            playerTurn = 1;
            binding.turn.setText("Lượt bạn");
            playerTurn();
            //comturn
        } else {
            playerTurn = 2;
            binding.turn.setText("Lượt máy");
            defend();
        }
    }

    private void playerTurn() {
        c0.setOnClickListener(view -> {
            if (isCellSelected(0)) {
                performAction((ImageView) view, 0);
            }
        });

        c1.setOnClickListener(view -> {
            if (isCellSelected(1)) {
                performAction((ImageView) view, 1);
            }

        });
        c2.setOnClickListener(view -> {
            if (isCellSelected(2)) {
                performAction((ImageView) view, 2);
            }
        });
        c3.setOnClickListener(view -> {
            if (isCellSelected(3)) {
                performAction((ImageView) view, 3);
            }
        });
        c4.setOnClickListener(view -> {
            if (isCellSelected(4)) {
                performAction((ImageView) view, 4);
            }
        });
        c5.setOnClickListener(view -> {
            if (isCellSelected(5)) {
                performAction((ImageView) view, 5);
            }
        });
        c6.setOnClickListener(view -> {
            if (isCellSelected(6)) {
                performAction((ImageView) view, 6);
            }
        });
        c7.setOnClickListener(view -> {
            if (isCellSelected(7)) {
                performAction((ImageView) view, 7);
            }
        });
        c8.setOnClickListener(view -> {
            if (isCellSelected(8)) {
                performAction((ImageView) view, 8);
            }
        });
    }


    private boolean isCellSelected(int cellPos) {
        return this.cellPositionList[cellPos] == 0;

    }

    private void performAction(ImageView imageView, int selectedCellPos) {

        cellPositionList[selectedCellPos] = playerTurn;

        if (playerTurn == 1) {
            imageView.setImageResource(R.drawable.x);

            if (checkWin()) {
                handleWin(playerTurn);
            } else {
                totalSelectedBoxes++;
                changeTurn(2);
                Log.e(TAG, "performAction_1: " + totalSelectedBoxes);
            }
        } else {
            imageView.setImageResource(R.drawable.o);
            if (checkWin()) {
                handleWin(playerTurn);

            } else {
                totalSelectedBoxes++;
                changeTurn(1);
                Log.e(TAG, "performAction_2: " + totalSelectedBoxes);
            }
        }
    }

    private void changeTurn(int currentPlayer) {

        if (checkDraw()) {
            handleDraw();
            return;

        }
        playerTurn = currentPlayer;
        if (playerTurn == 1) {
            binding.turn.setText("Lượt bạn");

            playerTurn();
        } else {
            binding.turn.setText("Lượt máy");
            defend();
        }
    }

    private int handleRandom() {
        Random random = new Random();
        return random.nextInt(9);
    }

    private void computerTurn() {


        int num = handleRandom();
        if (!isCellSelected(num)) {
            Log.e(TAG, "computerTurn: it had been selected " + num);
            computerTurn();
        } else {

            if (num == 0) {
                performAction(c0, 0);

            }
            if (num == 1) {

                performAction(c1, 1);

            }
            if (num == 2) {
                performAction(c2, 2);
            }
            if (num == 3) {
                performAction(c3, 3);
            }
            if (num == 4) {
                performAction(c4, 4);
            }
            if (num == 5) {
                performAction(c5, 5);

            }
            if (num == 6) {
                performAction(c6, 6);

            }
            if (num == 7) {
                performAction(c7, 7);
            }
            if (num == 8) {
                performAction(c8, 8);
            }

        }

    }


    private void handleWin(int playerTurn) {
        if (playerTurn == 1) {
            binding.turn.setText("Bạn thắng!!!");
            player1Score++;
            binding.player1Score.setText(player1Score + "");

        } else {
            binding.turn.setText("Máy thắng!!!");
            player2Score++;
            binding.player2Score.setText(player2Score + "");
        }
        startAnimation(binding.turn);
        setClickedButtn(false);
        setVisibleMenuBttn(true);
    }

    private boolean checkDraw() {
        if (totalSelectedBoxes == 9) {
            return true;
        }
        return false;
    }

    private void handleDraw() {
        binding.turn.setText("Hòa!!!");
        startAnimation(binding.turn);
        setClickedButtn(false);
        setVisibleMenuBttn(true);
    }

    private void setVisibleMenuBttn(boolean state) {
        if (state) {
            binding.trBttn.setVisibility(View.VISIBLE);

        } else {
            binding.trBttn.setVisibility(View.GONE);

        }
        binding.btPlayAgain.setClickable(state);
        binding.btBack.setClickable(state);
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.onBackPressed();
            }
        });
        binding.btPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
            }
        });

    }


    private void setClickedButtn(boolean state) {
        for (ImageView ob : listBttn
        ) {
            ob.setClickable(state);
        }
    }


    private void resetGame() {
        cellPositionList = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (firstPlayer == 1) {
            firstPlayer = 2;
            binding.turn.setText("Lượt máy");
        } else if (firstPlayer == 2) {
            firstPlayer = 1;
            binding.turn.setText("Lượt bạn");
        }

        if (isBotTurn) {
            isBotTurn = false;
        } else {
            isBotTurn = true;
        }
        playerTurn = firstPlayer;
        totalSelectedBoxes = 0;
        for (ImageView ob : listBttn
        ) {
            ob.setImageDrawable(null);
        }
        binding.stokeLine.setBackground(null);
        setClickedButtn(true);
        setVisibleMenuBttn(false);
        animation.cancel();

        playGame();
    }

    private boolean checkWin() {
        boolean response = false;
        for (int i = 0; i < winTypeList.size(); i++) {
            final int[] winType = winTypeList.get(i);//012  345 678 ...
            if (cellPositionList[winType[0]] == playerTurn && cellPositionList[winType[1]] == playerTurn && cellPositionList[winType[2]] == playerTurn) {
                showStrokeLine(winType);
                startAnimation(listBttn.get(winType[0]));
                startAnimation(listBttn.get(winType[1]));
                startAnimation(listBttn.get(winType[2]));

                response = true;

            }

        }
        return response;
    }

    private void showStrokeLine(int[] winType) {

        if (winType[0] == 0 && winType[1] == 1 && winType[2] == 2) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke012);
        }  if (winType[0] == 3 && winType[1] == 4 && winType[2] == 5) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke345);
        }  if (winType[0] == 6 && winType[1] == 7 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke678);
        }  if (winType[0] == 0 && winType[1] == 4 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke048);
        }  if (winType[0] == 2 && winType[1] == 4 && winType[2] == 6) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke246);
        }  if (winType[0] == 0 && winType[1] == 3 && winType[2] == 6) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke036);
        }  if (winType[0] == 1 && winType[1] == 4 && winType[2] == 7) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke147);
        }  if (winType[0] == 2 && winType[1] == 5 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke258);
        }
        startAnimation(binding.stokeLine);

    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    private void playAgain() {
        //    binding.board.resetGame();
        resetGame();

    }

    private void startAnimation(View v) {

        animation.setRepeatMode(Animation.REVERSE);
        animation.setDuration(100);
        animation.setRepeatCount(21);
        v.startAnimation(animation);

    }

    private void initBoardCell() {
        c0 = binding.c1;
        c1 = binding.c2;
        c2 = binding.c3;
        c3 = binding.c4;
        c4 = binding.c5;
        c5 = binding.c6;
        c6 = binding.c7;
        c7 = binding.c8;
        c8 = binding.c9;


        listBttn.add(c0);
        listBttn.add(c1);
        listBttn.add(c2);
        listBttn.add(c3);
        listBttn.add(c4);
        listBttn.add(c5);
        listBttn.add(c6);
        listBttn.add(c7);
        listBttn.add(c8);

        winTypeList.add(new int[]{0, 1, 2});
        winTypeList.add(new int[]{3, 4, 5});
        winTypeList.add(new int[]{6, 7, 8});
        winTypeList.add(new int[]{0, 3, 6});
        winTypeList.add(new int[]{1, 4, 7});
        winTypeList.add(new int[]{2, 5, 8});
        winTypeList.add(new int[]{0, 4, 8});
        winTypeList.add(new int[]{2, 4, 6});
    }


    private void defend() {

        //horizontal 3
        if (cellPositionList[0] == 1 && cellPositionList[1] == 1 && cellPositionList[2] == 0) {
            performAction(c2, 2);

        } else if (cellPositionList[3] == 1 && cellPositionList[4] == 1 && cellPositionList[5] == 0) {
            performAction(c5, 5);
        } else if (cellPositionList[6] == 1 && cellPositionList[7] == 1 && cellPositionList[8] == 0) {
            performAction(c8, 8);

            //horizontal 2
        } else if (cellPositionList[0] == 1 && cellPositionList[1] == 0 && cellPositionList[2] == 1) {
            performAction(c1, 1);
        } else if (cellPositionList[3] == 1 && cellPositionList[4] == 0 && cellPositionList[5] == 1) {
            performAction(c4, 4);
        } else if (cellPositionList[6] == 1 && cellPositionList[7] == 0 && cellPositionList[8] == 1) {
            performAction(c7, 7);
        }
        //horizontal 1
        else if (cellPositionList[0] == 0 && cellPositionList[1] == 1 && cellPositionList[2] == 1) {
            performAction(c0, 0);
        } else if (cellPositionList[3] == 0 && cellPositionList[4] == 1 && cellPositionList[5] == 1) {
            performAction(c3, 3);
        } else if (cellPositionList[6] == 0 && cellPositionList[7] == 1 && cellPositionList[8] == 1) {
            performAction(c6, 6);


        }
        //vertical 3
        else if (cellPositionList[0] == 1 && cellPositionList[3] == 1 && cellPositionList[6] == 0) {
            performAction(c6, 6);
        } else if (cellPositionList[1] == 1 && cellPositionList[4] == 1 && cellPositionList[7] == 0) {
            performAction(c7, 7);
        } else if (cellPositionList[2] == 1 && cellPositionList[5] == 1 && cellPositionList[8] == 0) {
            performAction(c8, 8);


        } //vertical 2
        else if (cellPositionList[0] == 1 && cellPositionList[3] == 0 && cellPositionList[6] == 1) {
            performAction(c3, 3);
        } else if (cellPositionList[1] == 1 && cellPositionList[4] == 0 && cellPositionList[7] == 1) {
            performAction(c4, 4);
        } else if (cellPositionList[2] == 1 && cellPositionList[5] == 0 && cellPositionList[8] == 1) {
            performAction(c5, 5);


        }
        //vertical 1
        else if (cellPositionList[0] == 0 && cellPositionList[3] == 1 && cellPositionList[6] == 1) {
            performAction(c0, 0);
        } else if (cellPositionList[1] == 0 && cellPositionList[4] == 1 && cellPositionList[7] == 1) {
            performAction(c1, 1);
        } else if (cellPositionList[2] == 0 && cellPositionList[5] == 1 && cellPositionList[8] == 1) {
            performAction(c2, 2);
        }
//digontal3
        else if (cellPositionList[0] == 1 && cellPositionList[4] == 1 && cellPositionList[8] == 0) {
            performAction(c8, 8);
        } else if (cellPositionList[2] == 1 && cellPositionList[4] == 1 && cellPositionList[6] == 0) {
            performAction(c6, 6);
        }
        //digontal2
        else if (cellPositionList[0] == 1 && cellPositionList[4] == 0 && cellPositionList[8] == 1) {
            performAction(c4, 4);
        } else if (cellPositionList[2] == 1 && cellPositionList[4] == 0 && cellPositionList[6] == 1) {
            performAction(c4, 4);
        }
        //digontal1
        else if (cellPositionList[0] == 0 && cellPositionList[4] == 1 && cellPositionList[8] == 1) {
            performAction(c0, 0);
        } else if (cellPositionList[2] == 0 && cellPositionList[4] == 1 && cellPositionList[6] == 1) {
            performAction(c2, 2);
        } else {
            computerTurn();
        }

    }
}