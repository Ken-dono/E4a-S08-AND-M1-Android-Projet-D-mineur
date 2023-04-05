package com.example.e4a_s08_and_m1_android_projet_demineur.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import com.example.e4a_s08_and_m1_android_projet_demineur.R;
import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityStatsBinding;

public class StatsActivity extends AppCompatActivity {

    private int totalGamesPlayed, easyWin, mediumWin, hardWin, winRate;

    private ActivityStatsBinding binding;


    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        preferences = getApplicationContext().getSharedPreferences("stats", Context.MODE_PRIVATE);

        totalGamesPlayed = preferences.getInt("totalGamesPlayed", -1);
        easyWin = preferences.getInt("easyWin", -1);
        mediumWin = preferences.getInt("mediumWin", -1);
        hardWin = preferences.getInt("hardWin", -1);
        winRate = preferences.getInt("WinRate", -1);

        if (totalGamesPlayed != -1 || easyWin != -1 || mediumWin != -1 || hardWin != -1 || winRate != -1){

            binding.totalGamesPlayed.setText(totalGamesPlayed);
            binding.easyWin.setText(easyWin);
            binding.mediumWin.setText(mediumWin);
            binding.hardWin.setText(hardWin);
            binding.winRate.setText(winRate);
        }else{

            Toast.makeText(getApplicationContext(), "Nous n'avons pas pu récupérer toute vos statistiques", Toast.LENGTH_SHORT).show();
        }

    }
}