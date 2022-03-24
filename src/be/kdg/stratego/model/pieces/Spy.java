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
    protected ArrayList<Piece> attackMarshal(MovingPiece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        // Spy jumped on Marshal, Marshal dead
        killedPieces.add(piece);
        piece.startKill(this);

        return killedPieces;
    }
}
