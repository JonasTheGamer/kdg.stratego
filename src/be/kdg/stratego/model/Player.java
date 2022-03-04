package be.kdg.stratego.model;

import javafx.scene.paint.Color;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class Player {
    private String name;
    private Color color;
    private ArrayList<Piece> pieces = new ArrayList<>();

    public Player(String naam, Color color) {
        this.name = naam;
        this.color = color;
        // Fill pieces

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
