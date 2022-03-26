package be.kdg.stratego.model;

import java.util.Objects;

public class Lowturn implements Comparable<Lowturn> {
    private String naam;
    private int turns;

    public Lowturn(String spelernaam, int turns) {
        this.naam = spelernaam;
        this.turns = turns;
    }

    // Methods
    @Override
    public int compareTo(Lowturn lowturn) {
        return Integer.compare(lowturn.getTurns(), this.turns);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lowturn lowturn = (Lowturn) o;
        return turns == lowturn.turns && naam.equals(lowturn.naam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naam, turns);
    }

    // Getters
    public String getSpelernaam() {
        return naam;
    }

    public int getTurns() {
        return turns;
    }
}