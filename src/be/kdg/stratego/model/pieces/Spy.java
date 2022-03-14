package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Spy extends MovingPiece {

    public Spy(Player player, GameBoardField field) {
        super(player, "spy", "/pieces/spy.png", 1, field);
    }

    public Spy(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
