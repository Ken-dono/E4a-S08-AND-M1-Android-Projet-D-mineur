package com.example.e4a_s08_and_m1_android_projet_demineur.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e4a_s08_and_m1_android_projet_demineur.R;
import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding;
import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityStatsBinding;

public class StatsActivity extends AppCompatActivity {

    private int totalGamesPlayed, easyWin, mediumWin, hardWin;

    float winRate;

    private ActivityStatsBinding binding;


    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityStatsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        preferences = getApplicationContext().getSharedPreferences("stats", Context.MODE_PRIVATE);

        totalGamesPlayed = preferences.getInt("totalGamesPlayed", 0);
        easyWin = preferences.getInt("easyWin", 0);
        mediumWin = preferences.getInt("mediumWin", 0);
        hardWin = preferences.getInt("hardWin", 0);

        binding.totalGamesPlayed.setText(Integer.toString(totalGamesPlayed));
        binding.easyWin.setText(Integer.toString(easyWin));
        binding.mediumWin.setText(Integer.toString(mediumWin));
        binding.hardWin.setText(Integer.toString(hardWin));

    }
}