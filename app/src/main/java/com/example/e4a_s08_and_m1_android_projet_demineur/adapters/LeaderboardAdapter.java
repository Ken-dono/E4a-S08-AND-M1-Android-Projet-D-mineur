package com.example.e4a_s08_and_m1_android_projet_demineur.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.e4a_s08_and_m1_android_projet_demineur.R;
import com.example.e4a_s08_and_m1_android_projet_demineur.models.LeaderboardModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    Context context;
    ArrayList<LeaderboardModel> list;
    String difficulty;

    public LeaderboardAdapter(Context context, ArrayList<LeaderboardModel> list) {

        this.context = context;
        this.list = list;
        this.difficulty = difficulty;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.leaderboard_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LeaderboardAdapter.ViewHolder holder, int position) {

        LeaderboardModel leaderboardModel =list.get(position);
        holder.ranking.setText(Integer.toString(position + 1));
        holder.Name.setText(leaderboardModel.getName());
        holder.BestScore.setText(Integer.toString(leaderboardModel.getBestScore()) + "s");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView Name, ranking, BestScore;
        LinearLayout cardView;
        View view;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our variables.
            Name = itemView.findViewById(R.id.Name);
            ranking = itemView.findViewById(R.id.ranking);
            BestScore = itemView.findViewById(R.id.BestScore);

            cardView = itemView.findViewById(R.id.cardview);
            view = itemView.findViewById(R.id.view);
        }

    }

}
