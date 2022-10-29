package com.example.tiktaktoe.dialog;

import static com.example.tiktaktoe.MySharePreference.SAVE_CHESS_STATE;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.tiktaktoe.App;
import com.example.tiktaktoe.MySharePreference;
import com.example.tiktaktoe.R;
import com.example.tiktaktoe.databinding.DialogChessTypeBinding;
import com.example.tiktaktoe.model.ChessType;

import java.util.ArrayList;
import java.util.List;

public class ChessTypeDialog extends Dialog {
    private static final String TYPE_CLASSIC = "Cổ điển";
    private static final String TYPE_MODERN = "Hiện đại";
    private static final String TYPE_STYLIZED = "Cách điệu";
    private static final String TYPE_CARTOON = "Hoạt hình";
    private static final String TYPE_SPACE = "Vũ trụ";
    private static final String TYPE_NATURE= "Tự nhiên";
    private DialogChessTypeBinding binding;
    private ImageView currentCheckBox;
    private List<ChessType> chessTypeList;

    private Context context;

    public ChessTypeDialog(@NonNull Context context) {
        super(context);
        this.context = context;
        binding = DialogChessTypeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView();
    }

    private void initView() {
        updateUI();
        setClickView();

    }

    private void updateCurrentCheckBox(ImageView checkbox) {
        currentCheckBox.setImageDrawable(null);
        currentCheckBox = checkbox;
        currentCheckBox.setImageResource(R.drawable.ic_check);
    }

    private void setClickView() {
        binding.trClassic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                if (isChecking(binding.checkboxClassic)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxClassic);
                saveStyle(TYPE_CLASSIC);
                dismiss();
            }
        });
        binding.trModern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                if (isChecking(binding.checkboxModern)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxModern);
                saveStyle(TYPE_MODERN);

                dismiss();

            }
        });
        binding.trStylize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                if (isChecking(binding.checkboxStylize)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxStylize);
                saveStyle(TYPE_STYLIZED);
                dismiss();
            }
        });
        binding.trViewCartoon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();
                if (isChecking(binding.checkboxCartoon)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxCartoon);
                saveStyle(TYPE_CARTOON);
                dismiss();
            }
        });
        binding.trSpace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();

                if (isChecking(binding.checkboxSpace)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxSpace);
                saveStyle(TYPE_SPACE);
                dismiss();
            }
        });
        binding.trNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playClickSound();

                if (isChecking(binding.checkboxNature)) {
                    dismiss();
                    return;
                }
                updateCurrentCheckBox(binding.checkboxNature);
                saveStyle(TYPE_NATURE);
                dismiss();
            }
        });

    }

    private void playClickSound() {
        App.getInstance().getMediaManager().playSound(App.getInstance().getMediaManager().CLICK_SOUND);

    }

    private void saveStyle(String style) {
        App.getInstance().getStorage().chessType = style;
        MySharePreference.getInstance().putStringValue(SAVE_CHESS_STATE, App.getInstance().getStorage().chessType);
    }

    private boolean isChecking(ImageView checkbox) {
        if (checkbox.equals(currentCheckBox)) {
            return true;
        }
        return false;
    }

    private void updateUI() {

        String value = App.getInstance().getStorage().chessType;
        if (value.equals(TYPE_CLASSIC)) {
            binding.checkboxClassic.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxClassic;
        } else if (value.equals(TYPE_MODERN)) {
            binding.checkboxModern.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxModern;
        } else if (value.equals(TYPE_STYLIZED)) {
            binding.checkboxStylize.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxStylize;
        } else if (value.equals(TYPE_CARTOON)) {
            binding.checkboxCartoon.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxCartoon;
        } else if (value.equals(TYPE_SPACE)) {
            binding.checkboxSpace.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxSpace;
        }else if (value.equals(TYPE_NATURE)) {
            binding.checkboxNature.setImageResource(R.drawable.ic_check);
            currentCheckBox = binding.checkboxNature;
        }
    }


    public ChessTypeDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected ChessTypeDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }


}
