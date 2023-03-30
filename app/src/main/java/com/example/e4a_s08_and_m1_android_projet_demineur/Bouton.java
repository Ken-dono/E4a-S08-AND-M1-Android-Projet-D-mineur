package com.example.e4a_s08_and_m1_android_projet_demineur;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.e4a_s08_and_m1_android_projet_demineur.databinding.FragmentBoutonBinding;

import java.lang.reflect.Type;
import java.util.Random;

public class Bouton extends Fragment {
    private FragmentBoutonBinding binding;
    enum TypeDeCASE {BOMB, FLAG, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, HEIGHT, HIDDEN, VISIBLE};
    public int posX;
    public int posY;
    private String test;
    public TypeDeCASE type;
    public boolean status;
    public Bouton() {

        // Required empty public constructor
    }

    public Bouton(TypeDeCASE t,int X,int Y,String text) {
        posX = X;
        posY = Y;
        type = t;
        status = false;
        test = text;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status = true;
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBoutonBinding.inflate(inflater,container,false);
        binding.textView2.setText(test);
        status = true;
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
    public boolean getStatus(){
        return status;
    }

}