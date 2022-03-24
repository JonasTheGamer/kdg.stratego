package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

import java.util.ArrayList;

public class Spy extends MovingPiece {

    public Spy(Player player) {
        super(player, "spy", "/pieces/spy.png", 1);
    }

    @Override
    public ArrayList<Piece> attackNormal(MovingPiece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();
        // Highest rank always wins
        MovingPiece convertedPiece = (MovingPiece) piece;
        if (convertedPiece instanceof Marshal) {
            killedPieces.add(piece);
            piece.startKill(this);
        } else if (rank < convertedPiece.getRank()) {
            killedPieces.add(this);
            this.startKill();
        } else if (rank > convertedPiece.getRank()) {
            killedPieces.add(piece);
            piece.startKill(this);
        } else {
            // Ranks are equal, both pieces die
            killedPieces.add(this);
            this.startKill();
            killedPieces.add(piece);
            piece.startKill();
        }
        return killedPieces;
    }

    @Override
    public ArrayList<Piece> attackMarshal(Marshal piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        // Spy jumped on Marshal, Marshal dead
        killedPieces.add(piece);
        piece.startKill(this);

        return killedPieces;
    }
}
