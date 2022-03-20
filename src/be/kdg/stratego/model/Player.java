package be.kdg.stratego.model;

import be.kdg.stratego.model.pieces.*;

import java.util.ArrayList;

public class Player {
    public static final int maxNameCharacters = 10;

    private String name;
    private String color;
    private String flag;
    private ArrayList<Piece> pieces = new ArrayList<>();

    // Constructor
    public Player(String naam, String color, String flag) {
        this.name = naam;
        this.color = color;
        this.flag = flag;

        this.givePieces();
    }

    // Methods
    public void givePieces() {
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
        pieces.add(new Flag(this, this.flag));
    }

    public void hidePieces() {
        for (Piece piece : pieces) {
            piece.setHidden(true);
        }
    }

    public void showPieces() {
        for (Piece piece : pieces) {
            piece.setHidden(false);
        }
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<Piece> getPieces() {
        return pieces;
    }
}
