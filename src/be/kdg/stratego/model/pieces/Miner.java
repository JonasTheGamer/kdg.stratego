package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Miner extends MovingPiece {

    public Miner(Player player, GameBoardField field) {
        super(player = player, "miner", "/pieces/miner.png", 3, field);
    }

    public Miner(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
