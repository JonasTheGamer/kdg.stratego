package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class General extends MovingPiece {

    public General(Player player, GameBoardField field) {
        super(player, "general", "/pieces/general.png", 9, field);
    }

    public General(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
