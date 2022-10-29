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
import com.example.tiktaktoe.databinding.FragmentSettingBinding;
import com.example.tiktaktoe.viewmodel.CommonVM;

public class SettingFragment extends BaseFragment<FragmentSettingBinding, CommonVM> {
    public static final String TAG = FragmentSettingBinding.class.getName();

    @Override
    protected void initView() {
        setUI("ivSound",MySharePreference.getInstance().getBooleanValue(SAVE_SOUND));

        setClick();
    }


    private void setUI(String key, boolean state) {
        if (key.equals("ivSound")) {

            if (state) {
                binding.btnSound.setChecked(true);


            } else {
                binding.btnSound.setChecked(false);


            }

        }
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.btn_sound) {

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

            MainActivity mainActivity = (MainActivity) mContext;
            mainActivity.onBackPressed();
        } else if (view.getId() == R.id.chestType) {
            showDialog();
        }

    }

    private void showDialog() {
        String[] listSize = {"Cổ điển", "Hoạt hình"};
        String[] value = new String[]{App.getInstance().getStorage().chessType};
        if (value[0] == null) {
            value[0] = "Cổ điển";
        }
        int index = 0;

        if (value[0].equals("Hoạt hình")) {
            index = 1;
        }
        AlertDialog.Builder alBuilder = new AlertDialog.Builder(getContext());

        alBuilder.setSingleChoiceItems(listSize, index, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                value[0] = listSize[which];
            }
        });

        alBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (value[0].equals("Cổ điển")) {
                    App.getInstance().getStorage().chessType = "Cổ điển";
                } else if (value[0].equals("Hoạt hình")) {
                    App.getInstance().getStorage().chessType = "Hoạt hình";
                }

                MySharePreference.getInstance().putStringValue(SAVE_CHESS_STATE, App.getInstance().getStorage().chessType);
            }
        });
        alBuilder.show();
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


    @Override
    protected FragmentSettingBinding initViewBinding(LayoutInflater inflater) {
        return FragmentSettingBinding.inflate(inflater);
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }


}
