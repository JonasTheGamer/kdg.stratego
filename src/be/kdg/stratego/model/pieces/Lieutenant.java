package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Lieutenant extends MovingPiece {

    public Lieutenant(Player player, GameBoardField field) {
        this.player = player;
        this.name = "lieutenant";
        this.image = "/pieces/lieutenant.png";
        this.rank = 5;
        this.field = field;
    }

    public Lieutenant(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
