package com.example.e4a_s08_and_m1_android_projet_demineur;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class writeToDatabase {

    public static void newScore(String difficulty, String playerName, int secondsElapsed) {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();

        ref.child(difficulty).child(playerName).child("BestScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.getValue() != null){

                    long lastBestScore = (long) snapshot.getValue();
                    System.out.println("lastBestScore : " + lastBestScore + ", newBestScore : " + secondsElapsed);

                    if (secondsElapsed < lastBestScore){

                        ref.child(difficulty).child(playerName).child("Name").setValue(playerName);
                        ref.child(difficulty).child(playerName).child("BestScore").setValue(secondsElapsed);
                    }
                }else{

                    ref.child(difficulty).child(playerName).child("Name").setValue(playerName);
                    ref.child(difficulty).child(playerName).child("BestScore").setValue(secondsElapsed);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
    }
}
