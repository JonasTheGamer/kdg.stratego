package be.kdg.stratego.model;

import java.util.Objects;

public class Highscore implements Comparable<Highscore> {
    private String naam;
    private int score;

    public Highscore(String spelernaam, int score) {
        this.naam = spelernaam;
        this.score = score;
    }

    // Methods
    @Override
    public int compareTo(Highscore highscore) {
        return Integer.compare(highscore.getScore(), this.score);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Highscore highscore = (Highscore) o;
        return score == highscore.score && naam.equals(highscore.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, score);
    }

    // Getters
    public String getSpelernaam() {
        return naam;
    }

    public int getScore() {
        return score;
    }
}