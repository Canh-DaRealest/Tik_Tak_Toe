package com.example.tiktaktoe.fragment;

import static com.example.tiktaktoe.MySharePreference.SAVE_CHESS_STATE;
import static com.example.tiktaktoe.MySharePreference.SAVE_SOUND;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.MySharePreference;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.activity.MainActivity;
import com.example.tiktaktoe.databinding.DialogChessTypeBinding;
import com.example.tiktaktoe.databinding.FragmentSettingBinding;
import com.example.tiktaktoe.dialog.ChessTypeDialog;
import com.example.tiktaktoe.viewmodel.CommonVM;

public class SettingFragment extends BaseFragment<FragmentSettingBinding, CommonVM> {
    public static final String TAG = SettingFragment.class.getName();

    @Override
    protected FragmentSettingBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSettingBinding.inflate(inflater);
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
        setUI("ivSound", App.getInstance().getMediaManager().getSoundState());

        setClick();
    }


    private void setUI(String key, boolean state) {
        if (key.equals("ivSound")) {

            binding.btnSound.setChecked(state);
        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_sound) {
            playClickSound();

            if (!App.getInstance().getMediaManager().getSoundState()) {

                binding.btnSound.setChecked(true);
                App.getInstance().getMediaManager().setSoundState(true);
                MySharePreference.getInstance().setBooleanValue(SAVE_SOUND, App.getInstance().getMediaManager().getSoundState());

            } else {
                binding.btnSound.setChecked(false);
                App.getInstance().getMediaManager().setSoundState(false);
                MySharePreference.getInstance().setBooleanValue(SAVE_SOUND, App.getInstance().getMediaManager().getSoundState());
            }
        } else if (view.getId() == R.id.bt_setting_back) {
            playClickSound();
            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.onBackPressed();

        } else if (view.getId() == R.id.chestType) {
            playClickSound();
            showDialog();
        }

    }

    private void playClickSound() {
        App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);

    }

    private void showDialog() {
        ChessTypeDialog chessTypeDialog = new ChessTypeDialog(mContext);
        chessTypeDialog.show();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    private void setClick() {
        binding.btSettingBack.setOnClickListener(this);
        binding.btnSound.setOnClickListener(this);
        binding.chestType.setOnClickListener(this);

    }


}
