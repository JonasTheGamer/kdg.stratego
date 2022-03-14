package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Lieutenant extends MovingPiece {

    public Lieutenant(Player player, GameBoardField field) {
        super(player, "lieutenant", "/pieces/lieutenant.png", 5, field);
    }

    public Lieutenant(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
