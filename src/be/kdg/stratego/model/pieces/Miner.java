package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

import java.util.ArrayList;

public class Miner extends MovingPiece {

    public Miner(Player player) {
        super(player, "miner", "/pieces/miner.png", 3);
    }

    @Override
    protected ArrayList<Piece> attack(Piece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();
        this.hidden = false;
        piece.setHidden(false);

        if(piece instanceof MovingPiece) {
            // Highest rank always wins
            MovingPiece convertedPiece = (MovingPiece) piece;
            if(rank < convertedPiece.getRank()) {
                killedPieces.add(this);
                this.startKill(); // No need to specify attacker, the winning piece doesn't have to move
            } else if(rank > convertedPiece.getRank()) {
                killedPieces.add(piece);
                piece.startKill(this);
            } else {
                // Ranks are equal, both pieces die
                killedPieces.add(this);
                this.startKill();
                killedPieces.add(piece);
                piece.startKill();
            }
        } else {
            // Check if piece jumps on flag
            if(piece instanceof Flag) {
                // Jonas: We won! :D
            }

            // We jumped on a bomb, but we can defuse it! :-)
            if(piece instanceof Bomb) {
                killedPieces.add(piece);
            }
        }
        return killedPieces;
    }
}
