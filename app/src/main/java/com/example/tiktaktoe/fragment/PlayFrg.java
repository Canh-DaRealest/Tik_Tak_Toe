package com.example.tiktaktoe.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import androidx.lifecycle.Observer;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.activity.MainActivity;
import com.example.tiktaktoe.databinding.FragmentPlayBinding;
import com.example.tiktaktoe.viewmodel.MainVM;

import java.util.ArrayList;
import java.util.List;


public class PlayFrg extends BaseFragment<FragmentPlayBinding, MainVM> {

    private ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8;

    private final List<ImageView> cellBttn = new ArrayList<>();
    Animation animation = new AlphaAnimation(1.0f, 0.0f);
    public static final String TAG = PlayFrg.class.getName();


    @Override
    protected FragmentPlayBinding initViewBinding(LayoutInflater inflater) {
        return FragmentPlayBinding.inflate(inflater);
    }


    @Override
    protected void initView() {

        viewModel.initPlayer();

        binding.player1Name.setText(viewModel.getPlayer1().getPlayerName());
        binding.player2Name.setText(viewModel.getPlayer2().getPlayerName());

        updateScore();

        updateTurn();

        initBoardCell();

        playGame();


    }

    private void updateTurn() {
        viewModel.getTurnLiveTxt().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.turn.setText(s);
            }
        });
    }

    private void playClickSound() {
        if (viewModel.getPlayerTurn() == 1) {
            App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().X_SOUND);
        } else {
            App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().O_SOUND);
        }

    }

    private void playGame() {
        //check who's turn
        //if  is not first game
        //if player1's turn
        if (viewModel.getPlayerTurn() == 1) {
            viewModel.updateLiveText(new StringBuilder().append("Lượt ").append(viewModel.getPlayer1().getPlayerName()).toString());
            setClickCell();

            //if player2's turn
        } else if (viewModel.getPlayerTurn() == 2) {
            viewModel.updateLiveText(new StringBuilder().append("Lượt ").append(viewModel.getPlayer2().getPlayerName()).toString());

            if (viewModel.isBotPlay()) {
                setClickedButtn(false);
                //   botLogic.botMove();
                viewModel.getBotLogic().findBestMove();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().O_SOUND);
                        performAction(checkViewClick(viewModel.getBotLogic().cellPos), viewModel.getBotLogic().cellPos);
                        setClickedButtn(true);
                    }
                }, 700);


            } else {
                setClickCell();
            }


        }
    }


    private ImageView checkViewClick(int cellPos) {

        if (cellPos == 0) {
            return c0;
        } else if (cellPos == 1) {
            return c1;
        } else if (cellPos == 2) {
            return c2;
        } else if (cellPos == 3) {
            return c3;
        } else if (cellPos == 4) {
            return c4;
        } else if (cellPos == 5) {
            return c5;
        } else if (cellPos == 6) {
            return c6;
        } else if (cellPos == 7) {
            return c7;
        } else if (cellPos == 8) {
            return c8;
        }
        return null;
    }


    private void updateScore() {


        viewModel.getPlayer1LiveScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.player1Score.setText(integer + "");
            }
        });


        viewModel.getPlayer2LiveScore().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.player2Score.setText(integer + "");
            }
        });


    }

    private void setClickCell() {
        c0.setOnClickListener(view -> {
            if (viewModel.isCellSelected(0)) {
                playClickSound();
                performAction((ImageView) view, 0);
            }
        });

        c1.setOnClickListener(view -> {
            if (viewModel.isCellSelected(1)) {
                playClickSound();
                performAction((ImageView) view, 1);
            }

        });
        c2.setOnClickListener(view -> {
            if (viewModel.isCellSelected(2)) {
                playClickSound();
                performAction((ImageView) view, 2);
            }
        });
        c3.setOnClickListener(view -> {
            if (viewModel.isCellSelected(3)) {
                playClickSound();
                performAction((ImageView) view, 3);
            }
        });
        c4.setOnClickListener(view -> {
            if (viewModel.isCellSelected(4)) {
                playClickSound();
                performAction((ImageView) view, 4);
            }
        });
        c5.setOnClickListener(view -> {
            if (viewModel.isCellSelected(5)) {
                playClickSound();
                performAction((ImageView) view, 5);
            }
        });
        c6.setOnClickListener(view -> {
            if (viewModel.isCellSelected(6)) {
                playClickSound();
                performAction((ImageView) view, 6);
            }
        });
        c7.setOnClickListener(view -> {
            if (viewModel.isCellSelected(7)) {
                playClickSound();
                performAction((ImageView) view, 7);
            }
        });
        c8.setOnClickListener(view -> {
            if (viewModel.isCellSelected(8)) {
                playClickSound();
                performAction((ImageView) view, 8);
            }
        });
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

        cellBttn.add(c0);
        cellBttn.add(c1);
        cellBttn.add(c2);
        cellBttn.add(c3);
        cellBttn.add(c4);
        cellBttn.add(c5);
        cellBttn.add(c6);
        cellBttn.add(c7);
        cellBttn.add(c8);


    }


    private void performAction(ImageView imageView, int selectedCellPos) {

        viewModel.updateCellPosition(selectedCellPos, viewModel.getPlayerTurn());

        if (viewModel.isBotPlay()) {
            viewModel.getBotLogic().updateCellPosition(selectedCellPos, viewModel.getPlayerTurn());
        }


        if (viewModel.getPlayerTurn() == 1) {

            imageView.setImageResource(viewModel.getPlayer1().getChessImage());
            viewModel.totalSelectedBoxes++;
            viewModel.getBotLogic().totalSelectedBoxes++;

            if (viewModel.checkWin(viewModel.getPlayerTurn())) {
                handleWin(viewModel.getPlayerTurn());

            } else if (viewModel.totalSelectedBoxes == 9) {
                handleDraw();

            } else {

                viewModel.setPlayerTurn(2);
                playGame();

            }
        } else {
            imageView.setImageResource(viewModel.getPlayer2().getChessImage());
            viewModel.totalSelectedBoxes++;
            viewModel.getBotLogic().totalSelectedBoxes++;
            if (viewModel.checkWin(viewModel.getPlayerTurn())) {
                handleWin(viewModel.getPlayerTurn());

            } else if (viewModel.totalSelectedBoxes == 9) {
                handleDraw();

            } else {

                viewModel.setPlayerTurn(1);

                playGame();

            }
        }

    }

    private void handleWin(int playerTurn) {
        showStrokeLine(viewModel.getWinType());
        if (playerTurn == 1) {
            viewModel.updatePlayerScore(viewModel.getPlayer1().getScore(), viewModel.getPlayer1LiveScore());
            viewModel.updateLiveText(viewModel.getPlayer1().getPlayerName() + " thắng");

        } else {
            viewModel.updatePlayerScore(viewModel.getPlayer2().getScore(), viewModel.getPlayer2LiveScore());
            viewModel.updateLiveText(viewModel.getPlayer2().getPlayerName() + " thắng");
        }
        startAnimation(binding.turn);
        setClickedButtn(false);
        setVisibleMenuBttn(true);
    }


    private void handleDraw() {
      viewModel.updateLiveText("Hòa!!!!");
        startAnimation(binding.turn);
        setClickedButtn(false);
        setVisibleMenuBttn(true);
    }

    private void setVisibleMenuBttn(boolean state) {
        if (state) {
            binding.btPlayAgain.setVisibility(View.VISIBLE);
            binding.btBack.setVisibility(View.VISIBLE);

        } else {
            binding.btPlayAgain.setVisibility(View.GONE);
            binding.btBack.setVisibility(View.GONE);

        }
        binding.btPlayAgain.setClickable(state);
        binding.btBack.setClickable(state);
        binding.btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);
                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.onBackPressed();

            }
        });
        binding.btPlayAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);
                playAgain();
            }
        });

    }


    private void setClickedButtn(boolean state) {
        for (ImageView ob : cellBttn
        ) {
            ob.setClickable(state);
        }
    }


    private void resetGame() {
        viewModel.resetBoard();

        if (viewModel.isBotPlay()) {
            viewModel.getBotLogic().reset();
        }

        for (ImageView ob : cellBttn
        ) {
            ob.setImageDrawable(null);
        }
        binding.stokeLine.setBackground(null);
        setClickedButtn(true);
        setVisibleMenuBttn(false);
        animation.cancel();
        playGame();
    }


    private void showStrokeLine(int[] winType) {

        if (winType[0] == 0 && winType[1] == 1 && winType[2] == 2) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke012);
        } else if (winType[0] == 3 && winType[1] == 4 && winType[2] == 5) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke345);
        } else if (winType[0] == 6 && winType[1] == 7 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke678);
        } else if (winType[0] == 0 && winType[1] == 4 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke048);
        } else if (winType[0] == 2 && winType[1] == 4 && winType[2] == 6) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke246);
        } else if (winType[0] == 0 && winType[1] == 3 && winType[2] == 6) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke036);
        } else if (winType[0] == 1 && winType[1] == 4 && winType[2] == 7) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke147);
        } else if (winType[0] == 2 && winType[1] == 5 && winType[2] == 8) {
            binding.stokeLine.setBackgroundResource(R.drawable.stroke258);
        }
        startAnimation(binding.stokeLine);

    }

    @Override
    protected Class<MainVM> getClassVM() {
        return MainVM.class;
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