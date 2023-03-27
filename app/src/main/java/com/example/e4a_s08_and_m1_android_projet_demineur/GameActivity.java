package com.example.e4a_s08_and_m1_android_projet_demineur;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityGameBinding;
import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.ActivityMainBinding;
import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.FragmentBoutonBinding;


//version axel
public class GameActivity extends AppCompatActivity {
    private ActivityGameBindingBinding binding;
    private Bouton[] BpTab;

    private String difficulty;
    private FragmentManager fragmentManager ;
    private FragmentTransaction fragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent = getIntent();

        difficulty = intent.getStringExtra("difficulty");
        fragmentManager = getSupportFragmentManager();


        for(int i =0;i<1;i++) {
            BpTab[i] = new Bouton(Bouton.TypeDeCASE.HIDDEN, 0, 0);
            fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.add(R.id.VerticalLay, BpTab[i]).addToBackStack(null);
            fragmentTransaction.commit();
        }

    }
}