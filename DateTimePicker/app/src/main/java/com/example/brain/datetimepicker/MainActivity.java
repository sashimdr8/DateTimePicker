package com.example.brain.datetimepicker;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.brain.datetimepicker.databinding.ActivityMainBinding;
import com.example.brain.datetimepicker.databinding.DialogDateTimeBinding;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private SimpleDateFormat simpleDateFormat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.etDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateTimeDialog();
            }
        });
    }

    private void showDateTimeDialog() {
        simpleDateFormat = new SimpleDateFormat("EEE d MMM yyyy HH:mm", Locale.getDefault());
        final DialogDateTimeBinding dateBinding = DataBindingUtil.inflate(LayoutInflater.from(this),
                R.layout.dialog_date_time, null, false);
        final AlertDialog alertDialog = new AlertDialog.Builder(this, R.style.TransparentDialog)
                .setView(dateBinding.getRoot())
                .create();
        dateBinding.btOk
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        binding.etDateTime.setText(simpleDateFormat.format(dateBinding.tvDateTime.getDate()));
                    }
                });
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.show();
    }
}
