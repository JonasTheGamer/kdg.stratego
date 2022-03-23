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
    protected ArrayList<Piece> attack(Piece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        if(piece instanceof MovingPiece) {
            // Highest rank always wins
            MovingPiece convertedPiece = (MovingPiece) piece;
            if(convertedPiece instanceof Marshal) {
                killedPieces.add(piece);
            } else if(rank < convertedPiece.getRank()) {
                killedPieces.add(this);
            } else if(rank > convertedPiece.getRank()) {
                killedPieces.add(piece);
            } else {
                // Ranks are equal, both pieces die
                killedPieces.add(this);
                killedPieces.add(piece);
            }
        } else {
            // Check if piece jumps on flag
            if(piece instanceof Flag) {
                // Jonas: We won! :D
            }

            // We jumped on a bomb
            if(piece instanceof Bomb) {
                killedPieces.add(this);
            }
        }
        return killedPieces;
    }
}
