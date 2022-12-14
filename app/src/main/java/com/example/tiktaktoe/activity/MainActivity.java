package com.example.tiktaktoe.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.MySharePreference;
import com.example.tiktaktoe.callback.ICallBack;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.fragment.BaseFragment;
import com.example.tiktaktoe.fragment.MainFragment;

public class MainActivity extends AppCompatActivity implements ICallBack {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        checkFirstInstall();
        showFrg(MainFragment.TAG, null, false);
    }


    @Override
    public void showFrg(String tag, Object data, boolean isBacked) {

        try {
            Class<?> instance = Class.forName(tag);
            BaseFragment<?, ?> fragment = (BaseFragment<?, ?>) instance.newInstance();

            fragment.setCallBack(this);
            fragment.setmData(data);
            FragmentManager fragmentManager = getSupportFragmentManager();

            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.fr_act_main, fragment);

            if (isBacked) {
                fragmentTransaction.addToBackStack(tag);
            }

            fragmentTransaction.commit();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkFirstInstall() {
        boolean value = MySharePreference.getInstance().getBooleanValue(MySharePreference.FIRST_INSTALL);
        if (!value) {
            App.getInstance().getStorage().chessType = "Hiện đại";

            App.getInstance().getMediaManager().setSoundState(true);
            MySharePreference.getInstance().putStringValue(MySharePreference.SAVE_CHESS_STATE, App.getInstance().getStorage().chessType);
            MySharePreference.getInstance().setBooleanValue(MySharePreference.SAVE_SOUND, true);
            MySharePreference.getInstance().setBooleanValue(MySharePreference.FIRST_INSTALL, true);


        } else {

            App.getInstance().getStorage().chessType = MySharePreference.getInstance().getStringValue(MySharePreference.SAVE_CHESS_STATE);
            if (App.getInstance().getStorage().chessType == null) {
                App.getInstance().getStorage().chessType = "Hiện đại";

            }

            App.getInstance().getMediaManager().setSoundState(MySharePreference.getInstance().getBooleanValue(MySharePreference.SAVE_SOUND));


        }
    }

    @Override
    public void onBackPressed() {

        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count > 0) {
            fragmentManager.popBackStack();
        } else {
            finish();
        }

    }
}