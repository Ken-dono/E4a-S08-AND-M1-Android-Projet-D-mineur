package com.example.e4a_s08_and_m1_android_projet_demineur.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.e4a_s08_and_m1_android_projet_demineur.activities.MainActivity;
import com.example.e4a_s08_and_m1_android_projet_demineur.models.LeaderboardModel;
import com.example.e4a_s08_and_m1_android_projet_demineur.R;
import com.example.e4a_s08_and_m1_android_projet_demineur.adapters.LeaderboardAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class HardLeaderboardFragment extends Fragment {

    RecyclerView recyclerView;
    LeaderboardAdapter adapter;
    ArrayList<LeaderboardModel> list;
    String difficulty = "hard";
    FirebaseDatabase firebaseDatabase =  FirebaseDatabase.getInstance();
    DatabaseReference ref = firebaseDatabase.getReference().child(difficulty);

    ImageView backArrow;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_hard_leaderboard, container, false);

        backArrow = view.findViewById(R.id.backArrow);

        recyclerView = view.findViewById(R.id.leaderboardRecycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        adapter = new LeaderboardAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);

        ref.orderByChild("BestScore").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    LeaderboardModel leaderboardModel = dataSnapshot.getValue(LeaderboardModel.class);
                    list.add(leaderboardModel);
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}