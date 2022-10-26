package com.example.tiktaktoe.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.tiktaktoe.callback.ICallBack;

public abstract class BaseFragment<T extends ViewBinding, M extends ViewModel> extends Fragment implements OnClickListener {
    protected M viewModel;
    protected T binding;
    protected  Object mData;
    protected Context mContext;
    protected ICallBack callBack;

    public void setCallBack(ICallBack callBack) {
        this.callBack = callBack;
    }

    public void setmData(Object mData) {
        this.mData = mData;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.mContext = context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = initViewBinding(inflater);
        viewModel = new ViewModelProvider(this).get(getClassVM());
        return binding.getRoot();

    }

    protected abstract T initViewBinding(LayoutInflater inflater);

    protected abstract Class<M> getClassVM();

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    protected abstract void initView();

    @Override
    public void onClick(View view) {
        view.setAnimation(AnimationUtils.loadAnimation(mContext, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);
    }

    private void clickView(View view) {
    }
}
