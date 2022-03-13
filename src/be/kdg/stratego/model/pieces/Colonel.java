package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Colonel extends MovingPiece {

    public Colonel(Player player, GameBoardField field) {
        this.player = player;
        this.name = "colonel";
        this.image = "/pieces/colonel.png";
        this.rank = 8;
        this.field = field;
    }

    public Colonel(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
