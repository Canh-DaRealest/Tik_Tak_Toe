package com.example.tiktaktoe.fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.database.entities.Name;
import com.example.tiktaktoe.viewmodel.CommonVM;
import com.example.tiktaktoe.databinding.FragmentDetailBinding;
import com.example.tiktaktoe.databinding.FragmentMainBinding;

import java.util.ArrayList;
import java.util.List;


public class DetailFragment extends BaseFragment<FragmentDetailBinding, CommonVM> {


    public static final String TAG = DetailFragment.class.getName();

    public DetailFragment() {
        App.getInstance().getStorage().nameList = new ArrayList<>();
    }

    @Override
    protected FragmentDetailBinding initViewBinding(LayoutInflater inflater) {
        return FragmentDetailBinding.inflate(inflater);
    }

    @Override
    protected Class<CommonVM> getClassVM() {
        return CommonVM.class;
    }

    @Override
    protected void initView() {
        App.getInstance().getStorage().nameList = App.getInstance().getAppDB().getDAO().getName();
        String[] nameList =  new String[App.getInstance().getStorage().nameList.size()];
        for (int i = 0; i <  App.getInstance().getStorage().nameList.size(); i++) {

            nameList[i] =  App.getInstance().getStorage().nameList.get(i).getName();
        }

        if (App.getInstance().getStorage().nameList.size() > 0) {
            Log.e(TAG, "initView: "+ App.getInstance().getStorage().nameList.size());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_dropdown_item_1line, nameList);
            adapter.notifyDataSetChanged();

            binding.textInputEdtPlayer1.setThreshold(1);
            binding.textInputEdtPlayer2.setThreshold(1);


            binding.textInputEdtPlayer1.setAdapter(adapter);
            binding.textInputEdtPlayer2.setAdapter(adapter);


            binding.textInputEdtPlayer1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    binding.textInputEdtPlayer1.setText((CharSequence) adapter.getItem(i));
                    binding.textInputEdtPlayer1.setSelection(binding.textInputEdtPlayer1.getText().length());


                }
            });
            binding.textInputEdtPlayer2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    binding.textInputEdtPlayer2.setText((CharSequence) adapter.getItem(i));
                    binding.textInputEdtPlayer2.setSelection(binding.textInputEdtPlayer2.getText().length());
                }
            });


        }else{
            Log.e(TAG, "initView: NULLLLLLLL" );
        }


        binding.textInputEdtPlayer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.textInputLayoutPlayer1.setErrorEnabled(true);
                    binding.textInputLayoutPlayer1.setError("Tên không được để trống");
                } else {
                    binding.textInputLayoutPlayer1.setErrorEnabled(false);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        binding.textInputEdtPlayer2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.textInputLayoutPlayer2.setErrorEnabled(true);
                    binding.textInputLayoutPlayer2.setError("Tên không được để trống");
                } else {
                    binding.textInputLayoutPlayer2.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        binding.playBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.textInputEdtPlayer1.getText().toString().isEmpty() && binding.textInputEdtPlayer2.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer1.setError("Tên không được để trống");
                    binding.textInputLayoutPlayer2.setError("Tên không được để trống");
                    return;
                }
                if (binding.textInputEdtPlayer1.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer1.setError("Tên không được để trống");

                    return;
                }


                if (binding.textInputEdtPlayer2.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer2.setError("Tên không được để trống");
                    return;
                }

                App.getInstance().getStorage().playerName1 = binding.textInputEdtPlayer1.getText().toString();
                App.getInstance().getStorage().playerName2 = binding.textInputEdtPlayer2.getText().toString();
                List<String> item = new ArrayList<>();

                item.add(App.getInstance().getStorage().playerName1);
                item.add(App.getInstance().getStorage().playerName2);

                for (String ob : item
                ) {
                    for (int i = 0; i < App.getInstance().getStorage().nameList.size(); i++) {

                        if (ob.equals(App.getInstance().getStorage().nameList.get(i).getName())) {
                            continue;
                        }
                        if (i == App.getInstance().getStorage().nameList.size() - 1) {
                            App.getInstance().getAppDB().getDAO().insertAll(new Name(ob));
                        }
                    }
                }

                callBack.showFrg(PlayFragment.TAG, null, false);

            }
        });

    }


}