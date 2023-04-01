package com.example.e4a_s08_and_m1_android_projet_demineur;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class writeToDatabase {

    public static void newScore(String difficulty, String playerName, int secondsElapsed) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child(difficulty).child(playerName).child("Name").setValue(playerName);
        ref.child(difficulty).child(playerName).child("BestScore").setValue(secondsElapsed);

    }


}
