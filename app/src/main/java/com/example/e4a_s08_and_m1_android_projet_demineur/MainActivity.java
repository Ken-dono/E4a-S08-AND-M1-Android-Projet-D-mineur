package com.example.e4a_s08_and_m1_android_projet_demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setContentView(R.layout.activity_main);
    }
}