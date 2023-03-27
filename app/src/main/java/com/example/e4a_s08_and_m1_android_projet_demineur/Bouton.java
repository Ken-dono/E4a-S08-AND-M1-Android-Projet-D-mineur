package com.example.e4a_s08_and_m1_android_projet_demineur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.FragmentBoutonBinding;

import java.lang.reflect.Type;

public class Bouton extends Fragment {
    private FragmentBoutonBinding binding;
    enum TypeDeCASE {BOMB, FLAG, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, HEIGHT, HIDDEN, VISIBLE};
    public int posX;
    public int posY;

    public TypeDeCASE type;
    public Bouton() {

        // Required empty public constructor
    }

    public Bouton(TypeDeCASE t,int X,int Y) {
        posX = X;
        posY = Y;
        type = t;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoutonBinding.inflate(inflater,container,false);
        return binding.getRoot();
        //inflater.inflate(R.layout.fragment_question, container, false);
    }
    public void onResume() {
        super.onResume();

        binding.BP1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}