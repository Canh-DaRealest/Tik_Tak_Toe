package com.example.tiktaktoe.fragment;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.activity.MainActivity;
import com.example.tiktaktoe.viewmodel.CommonVM;
import com.example.tiktaktoe.databinding.FragmentPlayBinding;

import java.util.ArrayList;
import java.util.List;


public class PlayFragment extends BaseFragment<FragmentPlayBinding, CommonVM> {

    private ImageView c1, c2, c3, c4, c5, c6, c7, c8, c9;


    private final List<int[]> winTypeList = new ArrayList<>();
    private final List<ImageView> cellBttn = new ArrayList<>();
    private int[] cellPos = {0, 0, 0, 0, 0, 0, 0, 0, 0};
    private int totalSelectedBoxes = 0;
    private int playerTurn = 1;
    private int player1Score = 0;
    private int player2Score = 0;
    private int firstplayer = 1;
    Animation animation = new AlphaAnimation(1.0f, 0.0f);

    public static final String TAG = PlayFragment.class.getName();

    @Override
    protected FragmentPlayBinding initViewBinding(LayoutInflater inflater) {
        return FragmentPlayBinding.inflate(inflater);
    }


    @Override
    protected void initView() {

        initBoardCell();
        setClickCell();


        String[] playerName = {App.getInstance().getStorage().playerName1, App.getInstance().getStorage().playerName2};

        binding.turn.setText("Lượt " + playerName[0]);

        //  binding.board.setUpGame(binding.btPlayAgain, binding.btBack, binding.turn, playerName);

        binding.player1Name.setText(App.getInstance().getStorage().playerName1);
        binding.player2Name.setText(App.getInstance().getStorage().playerName2);


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

    private void setClickCell() {
        c1.setOnClickListener(view -> {
            if (isCellSelected(0)) {
                performAction((ImageView) view, 0);
            }
        });

        c2.setOnClickListener(view -> {
            if (isCellSelected(1)) {
                performAction((ImageView) view, 1);
            }

        });
        c3.setOnClickListener(view -> {
            if (isCellSelected(2)) {
                performAction((ImageView) view, 2);
            }
        });
        c4.setOnClickListener(view -> {
            if (isCellSelected(3)) {
                performAction((ImageView) view, 3);
            }
        });
        c5.setOnClickListener(view -> {
            if (isCellSelected(4)) {
                performAction((ImageView) view, 4);
            }
        });
        c6.setOnClickListener(view -> {
            if (isCellSelected(5)) {
                performAction((ImageView) view, 5);
            }
        });
        c7.setOnClickListener(view -> {
            if (isCellSelected(6)) {
                performAction((ImageView) view, 6);
            }
        });
        c8.setOnClickListener(view -> {
            if (isCellSelected(7)) {
                performAction((ImageView) view, 7);
            }
        });
        c9.setOnClickListener(view -> {
            if (isCellSelected(8)) {
                performAction((ImageView) view, 8);
            }
        });
    }

    private void initBoardCell() {
        c1 = binding.c1;
        c2 = binding.c2;
        c3 = binding.c3;
        c4 = binding.c4;
        c5 = binding.c5;
        c6 = binding.c6;
        c7 = binding.c7;
        c8 = binding.c8;
        c9 = binding.c9;

        cellBttn.add(c1);
        cellBttn.add(c2);
        cellBttn.add(c3);
        cellBttn.add(c4);
        cellBttn.add(c5);
        cellBttn.add(c6);
        cellBttn.add(c7);
        cellBttn.add(c8);
        cellBttn.add(c9);


        winTypeList.add(new int[]{0, 1, 2});
        winTypeList.add(new int[]{3, 4, 5});
        winTypeList.add(new int[]{6, 7, 8});
        winTypeList.add(new int[]{0, 3, 6});
        winTypeList.add(new int[]{1, 4, 7});
        winTypeList.add(new int[]{2, 5, 8});
        winTypeList.add(new int[]{0, 4, 8});
        winTypeList.add(new int[]{2, 4, 6});
    }

    private boolean isCellSelected(int cellPos) {
        return this.cellPos[cellPos] == 0;

    }

    private void performAction(ImageView imageView, int selectedCellPos) {

        cellPos[selectedCellPos] = playerTurn;

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

    private void handleWin(int playerTurn) {
        if (playerTurn == 1) {
            binding.turn.setText(binding.player1Name.getText().toString() + " thắng!!!");
            player1Score++;
            binding.player1Score.setText(player1Score + "");

        } else {
            binding.turn.setText(binding.player2Name.getText().toString() + " thắng!!!");
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

    }


    private void setClickedButtn(boolean state) {
        for (ImageView ob : cellBttn
        ) {
            ob.setClickable(state);
        }
    }

    private void changeTurn(int currentPlayer) {

        if (checkDraw()) {
            handleDraw();
            return;

        }
        playerTurn = currentPlayer;
        if (playerTurn == 1) {
            binding.turn.setText("Lượt " + binding.player1Name.getText().toString());
        } else {
            binding.turn.setText("Lượt " + binding.player2Name.getText().toString());
        }
    }

    private void resetGame() {
        cellPos = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};

        if (firstplayer == 1) {
            firstplayer = 2;
            binding.turn.setText("Lượt " + binding.player2Name.getText().toString());
        } else if (firstplayer == 2) {
            firstplayer = 1;
            binding.turn.setText("Lượt " + binding.player1Name.getText().toString());
        }
        playerTurn = firstplayer;
        totalSelectedBoxes = 0;
        for (ImageView ob : cellBttn
        ) {
            ob.setImageDrawable(null);
        }
        setClickedButtn(true);
        setVisibleMenuBttn(false);
        animation.cancel();
    }

    private boolean checkWin() {
        boolean response = false;
        for (int i = 0; i < winTypeList.size(); i++) {
            final int[] winType = winTypeList.get(i);//012
            if (cellPos[winType[0]] == playerTurn && cellPos[winType[1]] == playerTurn && cellPos[winType[2]] == playerTurn) {
                startAnimation(cellBttn.get(winType[0]));
                startAnimation(cellBttn.get(winType[1]));
                startAnimation(cellBttn.get(winType[2]));

                response = true;

            }

        }
        return response;
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

}