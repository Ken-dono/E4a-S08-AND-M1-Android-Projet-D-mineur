package com.example.e4a_s08_and_m1_android_projet_demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String selectedDifficulty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void onDifficultySelected(View view) {
        switch (view.getId()) {
            case R.id.easy_button:
                selectedDifficulty = "easy";
                break;
            case R.id.medium_button:
                selectedDifficulty = "medium";
                break;
            case R.id.hard_button:
                selectedDifficulty = "hard";
                break;
            default:
                selectedDifficulty = "easy"; // Default to easy difficulty if no button is selected
                break;
        }

        // Start the game activity and pass the selected difficulty as an extra
        Intent intent = new Intent(this, GameActivity.class);
        intent.putExtra("difficulty", selectedDifficulty);
        startActivity(intent);
    }
}