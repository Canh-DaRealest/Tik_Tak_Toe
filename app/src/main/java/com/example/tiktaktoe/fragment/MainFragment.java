package com.example.tiktaktoe.fragment;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;


import com.example.tiktaktoe.App;
import com.example.tiktaktoe.viewmodel.CommonVM;
import com.example.tiktaktoe.databinding.FragmentMainBinding;


public class MainFragment extends BaseFragment<FragmentMainBinding, CommonVM> {


    public static final String TAG = MainFragment.class.getName();

    @Override
    protected FragmentMainBinding initViewBinding(LayoutInflater inflater) {
        return FragmentMainBinding.inflate(inflater);
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {

        binding.playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                callBack.showFrg(DetailFragment.TAG, null, true);
            }
        });
        binding.playWithBot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                App.getInstance().getStorage().playWithBot = true;
                callBack.showFrg(PlayFrg.TAG, null, true);
            }
        });
        binding.setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playClickSound();
                callBack.showFrg(SettingFragment.TAG, null, true);
            }
        }); binding.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playClickSound();
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, "Quả app chất lượng");
                mContext.startActivity(Intent.createChooser(intent, "Chia sẻ qua "));

            }
        }); binding.info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                playClickSound();
                Toast.makeText(mContext, "Vesion 1.0.0", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showSEttingDialog() {
    }

    private void playClickSound() {
        App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);
    }
}