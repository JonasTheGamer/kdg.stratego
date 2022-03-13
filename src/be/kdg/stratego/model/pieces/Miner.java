package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Miner extends MovingPiece {

    public Miner(Player player, GameBoardField field) {
        this.player = player;
        this.name = "miner";
        this.image = "/pieces/miner.png";
        this.rank = 3;
        this.field = field;
    }

    public Miner(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
