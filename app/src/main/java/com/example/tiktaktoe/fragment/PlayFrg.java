package com.example.tiktaktoe.fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.activity.MainActivity;
import com.example.tiktaktoe.databinding.FragmentPlayBinding;
import com.example.tiktaktoe.model.Board;
import com.example.tiktaktoe.model.BotLogic;
import com.example.tiktaktoe.model.Player;
import com.example.tiktaktoe.viewmodel.CommonVM;

import java.util.ArrayList;
import java.util.List;


public class PlayFrg extends BaseFragment<FragmentPlayBinding, CommonVM> {

    private ImageView c0, c1, c2, c3, c4, c5, c6, c7, c8;
    private BotLogic botLogic;
    private boolean isBotPlay = false;
    Player player1, player2;
    Board board;


    private final List<ImageView> cellBttn = new ArrayList<>();
    Animation animation = new AlphaAnimation(1.0f, 0.0f);
    public static final String TAG = PlayFrg.class.getName();

    @Override
    protected FragmentPlayBinding initViewBinding(LayoutInflater inflater) {
        return FragmentPlayBinding.inflate(inflater);
    }


    @Override
    protected void initView() {


        initPlayer();

        initBoardCell();

        playGame();


    }

    private void playClickSound() {
        if (board.getPlayerTurn()==1){
            App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().X_SOUND);
        }else{
            App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().O_SOUND);
        }

    }

    private void playGame() {

        if (board.getPlayerTurn() == 0) {
            board.setPlayerTurn(1);
            binding.turn.setText(new StringBuilder().append("Lượt ").append(player1.getPlayerName()).toString());
            setClickCell();
        } else {
            if (board.getPlayerTurn() == 1) {
                binding.turn.setText(new StringBuilder().append("Lượt ").append(player1.getPlayerName()).toString());
                setClickCell();
            } else if (board.getPlayerTurn() == 2) {
                binding.turn.setText(new StringBuilder().append("Lượt ").append(player2.getPlayerName()).toString());

                if (isBotPlay) {
                    setClickedButtn(false);
                    botLogic.botMove();
                    if (botLogic.botTurn) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().O_SOUND);
                                performAction(checkViewClick(botLogic.cellPos), botLogic.cellPos);
                                setClickedButtn(true);
                            }
                        }, 700);

                    }

                } else {
                    setClickCell();
                }


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
        binding.player1Score.setText(new StringBuilder().append(player1.getScore()).append("").toString());
        binding.player2Score.setText(new StringBuilder().append(player2.getScore()).append("").toString());
    }

    private void setClickCell() {
        c0.setOnClickListener(view -> {
            if (board.isCellSelected(0)) {
                playClickSound();
                performAction((ImageView) view, 0);
            }
        });

        c1.setOnClickListener(view -> {
            if (board.isCellSelected(1)) {
                playClickSound();
                performAction((ImageView) view, 1);
            }

        });
        c2.setOnClickListener(view -> {
            if (board.isCellSelected(2)) {
                playClickSound();
                performAction((ImageView) view, 2);
            }
        });
        c3.setOnClickListener(view -> {
            if (board.isCellSelected(3)) {
                playClickSound();
                performAction((ImageView) view, 3);
            }
        });
        c4.setOnClickListener(view -> {
            if (board.isCellSelected(4)) {
                playClickSound();
                performAction((ImageView) view, 4);
            }
        });
        c5.setOnClickListener(view -> {
            if (board.isCellSelected(5)) {
                playClickSound();
                performAction((ImageView) view, 5);
            }
        });
        c6.setOnClickListener(view -> {
            if (board.isCellSelected(6)) {
                playClickSound();
                performAction((ImageView) view, 6);
            }
        });
        c7.setOnClickListener(view -> {
            if (board.isCellSelected(7)) {
                playClickSound();
                performAction((ImageView) view, 7);
            }
        });
        c8.setOnClickListener(view -> {
            if (board.isCellSelected(8)) {
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

        board.updateCellPosition(selectedCellPos);

        if (isBotPlay) {
            botLogic.setCellPositionList(board.getCellPosArray());
        }


        if (board.getPlayerTurn() == 1) {
            imageView.setImageResource(player1.getChessImage());
            board.totalSelectedBoxes++;

            if (board.checkWin(board.getPlayerTurn())) {
                handleWin(board.getPlayerTurn());

            } else if (board.totalSelectedBoxes == 9) {
                handleDraw();

            } else {

                board.setPlayerTurn(2);
                playGame();

            }
        } else {
            imageView.setImageResource(player2.getChessImage());
            board.totalSelectedBoxes++;
            if (board.checkWin(board.getPlayerTurn())) {
                handleWin(board.getPlayerTurn());

            } else if (board.totalSelectedBoxes == 9) {
                handleDraw();

            } else {

                board.setPlayerTurn(1);

                playGame();

            }
        }

    }

    private void handleWin(int playerTurn) {
        showStrokeLine(board.getWinType());
        if (playerTurn == 1) {
            binding.turn.setText(new StringBuilder().append(player1.getPlayerName()).append(" thắng!!!").toString());
            player1.setScore(player1.getScore() + 1);
            binding.player1Score.setText(new StringBuilder().append(player1.getScore()).append("").toString());

        } else {
            binding.turn.setText(new StringBuilder().append(player2.getPlayerName()).append(" thắng!!!").toString());
            player2.setScore(player2.getScore() + 1);
            binding.player2Score.setText(new StringBuilder().append(player2.getScore()).append("").toString());
        }
        startAnimation(binding.turn);
        setClickedButtn(false);
        setVisibleMenuBttn(true);
    }


    private void handleDraw() {
        binding.turn.setText("Hòa!!!");
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
        board.resetBoard();

        if (isBotPlay) {
            botLogic.reset();
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

    private void initPlayer() {
        int image1 = 0, image2 = 0;
        if (App.getInstance().getStorage().chessType.equals("Cổ điển")) {
            image1 = R.drawable.classicx;
            image2 =R.drawable.classico;
        }else if (App.getInstance().getStorage().chessType.equals("Hiện đại")){
            image1 = R.drawable.modern_x;
            image2 =R.drawable.modern_o;
        }else if (App.getInstance().getStorage().chessType.equals("Cách điệu")){
            image1 = R.drawable.newx;
            image2 =R.drawable.newo;
        }else if (App.getInstance().getStorage().chessType.equals("Hoạt hình")){
            image1 = R.drawable.cartoonx;
            image2 =R.drawable.cartoono;
        }else if (App.getInstance().getStorage().chessType.equals("Vũ trụ")){
            image1 = R.drawable.starx;
            image2 =R.drawable.moono;
        }else if (App.getInstance().getStorage().chessType.equals("Tự nhiên")){
            image1 = R.drawable.naturex;
            image2 =R.drawable.natureo;
        }
        board = new Board();
        board.updateWintypeList();

        botLogic = new BotLogic();
        botLogic.setCellPositionList(board.cellPosArray);


        if (App.getInstance().getStorage().playWithBot) {
            player1 = new Player("Bạn", image1, 0, "x");
            player2 = new Player("Máy",image2, 0, "o");
            isBotPlay = true;
        } else {
            player1 = new Player(App.getInstance().getStorage().playerName1,image1, 0, "x");
            player2 = new Player(App.getInstance().getStorage().playerName2, image2, 0, "o");
        }

        binding.player1Name.setText(player1.getPlayerName());
        binding.player2Name.setText(player2.getPlayerName());

        updateScore();
    }

}