package be.kdg.stratego.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Highscore implements Comparable<Highscore> {
    private String naam;
    private int score;

    public Highscore(String spelernaam, int score) {
        this.naam = spelernaam;
        this.score = score;
    }

    public String getSpelernaam() {
        return naam;
    }

    public void setSpelernaam(String spelernaam) {
        this.naam = spelernaam;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public int compareTo(Highscore o) {
        return Integer.compare(o.getScore(), this.score);
    }
}