package com.example.e4a_s08_and_m1_android_projet_demineur.models;

public class LeaderboardModel {

    String Name;
    int ranking, BestScore;


    public LeaderboardModel(){}

    public LeaderboardModel(String Name, int ranking, int BestScore) {
        this.Name = Name;
        this.ranking = ranking;
        this.BestScore = BestScore;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public int getBestScore() {
        return BestScore;
    }

    public void setBestScore(int BestScore) {
        this.BestScore = BestScore;
    }

}
