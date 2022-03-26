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
    protected Piece attackLose(Piece piece) {
        if (piece instanceof Bomb) {
            //Miner defuses the bomb
            return super.attackWin(piece);
        } else {
            return super.attackLose(piece);
        }
    }
}
