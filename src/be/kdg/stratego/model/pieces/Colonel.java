package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Colonel extends MovingPiece {

    public Colonel(Player player, GameBoardField field) {
        super(player, "colonel", "/pieces/colonel.png", 8, field);
    }

    public Colonel(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
