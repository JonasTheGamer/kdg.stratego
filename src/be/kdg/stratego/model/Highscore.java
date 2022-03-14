package be.kdg.stratego.model;

import java.io.File;

public class Highscore implements Comparable<Highscore> {
    private String naam;
    private int score;

    public Highscore(String spelernaam, int score) {
        this.naam = spelernaam;
        this.score = score;
    }

    // Methods
    @Override
    public int compareTo(Highscore o) {
        return Integer.compare(o.getScore(), this.score);
    }

    // Getters
    public String getSpelernaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }
}