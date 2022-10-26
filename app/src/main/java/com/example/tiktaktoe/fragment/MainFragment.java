package com.example.tiktaktoe.fragment;

import android.view.LayoutInflater;
import android.view.View;


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
                callBack.showFrg(DetailFragment.TAG, null, true);
            }
        });
    }
}