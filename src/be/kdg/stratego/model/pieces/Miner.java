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
    public ArrayList<Piece> attackBomb(Bomb piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        // The bomb is dead
        killedPieces.add(piece);
        piece.startKill(this);

        return killedPieces;
    }
}
