package com.example.e4a_s08_and_m1_android_projet_demineur;

import android.content.Context;

import java.io.Serializable;

public class Cellule extends androidx.appcompat.widget.AppCompatButton implements Serializable {

    private Coordinate coordinate = new Coordinate();

    enum type {BOMB, FLAG, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, HEIGHT, HIDDEN, VISIBLE};

    private type t = type.HIDDEN;

    public Cellule(Context a,Coordinate c) {
        super(a);
        System.out.println("coo x : " + coordinate.getX());
        coordinate.setX(c.getX());
        coordinate.setY(c.getY());
        t = type.HIDDEN;
    }

    public type getT() {
        return t;
    }

    public void setT(type t) {
        this.t = t;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
