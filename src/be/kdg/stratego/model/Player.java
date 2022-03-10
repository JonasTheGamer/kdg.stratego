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

    public void givePieces(String flag) {
        // 6 bombs
        for (int i = 0; i < 6; i++) {
            pieces.add(new Bomb(this));
        }

        // 1 marshal
        pieces.add(new Marshal(this));

        // 1 general
        pieces.add(new General(this));

        // 2 colonels
        for (int i = 0; i < 2; i++) {
            pieces.add(new Colonel(this));
        }

        // 3 majors
        for (int i = 0; i < 3; i++) {
            pieces.add(new Major(this));
        }

        // 4 captains
        for (int i = 0; i < 4; i++) {
            pieces.add(new Captain(this));
        }

        // 4 lieutenants
        for (int i = 0; i < 4; i++) {
            pieces.add(new Lieutenant(this));
        }

        // 4 sergeants
        for (int i = 0; i < 4; i++) {
            pieces.add(new Sergeant(this));
        }

        // 5 miners
        for (int i = 0; i < 5; i++) {
            pieces.add(new Miner(this));
        }

        // 8 scouts
        for (int i = 0; i < 8; i++) {
            pieces.add(new Scout(this));
        }

        // 1 Spy
        pieces.add(new Spy(this));

        // 1 Flag
        pieces.add(new Flag(this, flag));
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
