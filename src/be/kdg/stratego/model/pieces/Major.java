package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Major extends MovingPiece {

    public Major(Player player, GameBoardField field) {
        super(player, "major", "/pieces/major.png", 7, field);
    }

    public Major(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
