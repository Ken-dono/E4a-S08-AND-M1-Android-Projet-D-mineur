package com.example.e4a_s08_and_m1_android_projet_demineur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private String selectedDifficulty;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.easyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDifficulty = "easy";

                binding.easyButton.setBackgroundResource(R.drawable.easy_checked);
                binding.mediumButton.setBackgroundResource(R.drawable.medium_unchecked);
                binding.hardButton.setBackgroundResource(R.drawable.hard_unchecked);

                binding.validateButton.setVisibility(View.VISIBLE);
            }
        });

        binding.mediumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDifficulty = "medium";

                binding.easyButton.setBackgroundResource(R.drawable.easy_unchecked);
                binding.mediumButton.setBackgroundResource(R.drawable.medium_checked);
                binding.hardButton.setBackgroundResource(R.drawable.hard_unchecked);

                binding.validateButton.setVisibility(View.VISIBLE);
            }
        });

        binding.hardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedDifficulty = "hard";

                binding.easyButton.setBackgroundResource(R.drawable.easy_unchecked);
                binding.mediumButton.setBackgroundResource(R.drawable.medium_unchecked);
                binding.hardButton.setBackgroundResource(R.drawable.hard_checked);

                binding.validateButton.setVisibility(View.VISIBLE);
            }
        });

        binding.validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the game activity and pass the selected difficulty as an extra
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("difficulty", selectedDifficulty);
                startActivity(intent);
            }
        });
    }
}
