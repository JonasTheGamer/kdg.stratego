/*
    Class: Player
    Responbility: Takes care of all data and methods from one individual player, such as their pieces, the
                  amount of turns they needed,...
 */
package be.kdg.stratego.model;

import be.kdg.stratego.model.pieces.*;
import java.util.ArrayList;

public class Player {
    public static final int MAX_NAME_CHARACTERS = 10;

    private String name;
    private String color;
    private String flag;
    private ArrayList<Piece> pieces = new ArrayList<>();
    private int amountOfTurns;
    private boolean piecesPlaced;

    // Constructor
    public Player(String naam, String color, String flag) {
        this.name = naam;
        this.color = color;
        this.flag = flag;
        this.piecesPlaced = false;
        this.amountOfTurns = 0;

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

    public void addTurn() {
        this.amountOfTurns++;
    }

    public Piece getPieceFromName(String pieceName) {
        Piece foundPiece = null;
        for (Piece piece : getPieces()) {
            if (piece.getName().equals(pieceName) && !piece.isOnField()) {
                foundPiece = piece;
                break;
            }
        }

        return foundPiece;
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

    public boolean isPiecesPlaced() {
        piecesPlaced = true;

        for (Piece piece : this.getPieces()) {
            // If the piece is already on the field, no need to figure everything out
            if (!piece.isOnField()) {
                this.piecesPlaced = false;
            }
        }
        return piecesPlaced;
    }

    public int getAmountOfTurns() {
        return amountOfTurns;
    }
}
