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
        String[] nameList = new String[App.getInstance().getStorage().nameList.size()];
        for (int i = 0; i < App.getInstance().getStorage().nameList.size(); i++) {

            nameList[i] = App.getInstance().getStorage().nameList.get(i).getName();
        }

        if (App.getInstance().getStorage().nameList.size() > 0) {
            Log.e(TAG, "initView: " + App.getInstance().getStorage().nameList.size());
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


        } else {
            Log.e(TAG, "initView: NULLLLLLLL");
        }


        binding.textInputEdtPlayer1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    binding.textInputLayoutPlayer1.setErrorEnabled(true);
                    binding.textInputLayoutPlayer1.setError("T??n kh??ng ???????c ????? tr???ng");
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
                    binding.textInputLayoutPlayer2.setError("T??n kh??ng ???????c ????? tr???ng");
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
                playClickSound();
                if (binding.textInputEdtPlayer1.getText().toString().isEmpty() && binding.textInputEdtPlayer2.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer1.setError("T??n kh??ng ???????c ????? tr???ng");
                    binding.textInputLayoutPlayer2.setError("T??n kh??ng ???????c ????? tr???ng");
                    return;
                }
                if (binding.textInputEdtPlayer1.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer1.setError("T??n kh??ng ???????c ????? tr???ng");

                    return;
                }


                if (binding.textInputEdtPlayer2.getText().toString().isEmpty()) {

                    binding.textInputLayoutPlayer2.setError("T??n kh??ng ???????c ????? tr???ng");
                    return;
                }

                App.getInstance().getStorage().playerName1 = binding.textInputEdtPlayer1.getText().toString();
                App.getInstance().getStorage().playerName2 = binding.textInputEdtPlayer2.getText().toString();

                int count = 0;
                int count2 = 0;
                if (App.getInstance().getStorage().nameList.size()==0){

                    App.getInstance().getAppDB().getDAO().insertAll(new Name(App.getInstance().getStorage().playerName2));
                    App.getInstance().getAppDB().getDAO().insertAll(new Name(App.getInstance().getStorage().playerName1));

                }else{
                    Log.e(TAG, "onClick: "+App.getInstance().getStorage().nameList.size() );
                    for (Name ob : App.getInstance().getStorage().nameList
                    ) {
                        if (!ob.getName().equals(App.getInstance().getStorage().playerName1)) {

                            count++;
                        }

                        if (!ob.getName().equals(App.getInstance().getStorage().playerName2)) {
                            count2++;
                        }


                        if (count2 >= App.getInstance().getStorage().nameList.size()) {
                            App.getInstance().getAppDB().getDAO().insertAll(new Name(App.getInstance().getStorage().playerName2));
                        }

                        if (count >= App.getInstance().getStorage().nameList.size()) {
                            App.getInstance().getAppDB().getDAO().insertAll(new Name(App.getInstance().getStorage().playerName1));
                        }
                    }

                }





                App.getInstance().getStorage().playWithBot = false;
                callBack.showFrg(PlayFrg.TAG, null, false);

            }
        });

    }

    private void playClickSound() {
        App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);
    }

}