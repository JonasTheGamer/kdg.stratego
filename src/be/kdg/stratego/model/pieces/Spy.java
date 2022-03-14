package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Spy extends MovingPiece {

    public Spy(Player player) {
        super(player, "spy", "/pieces/spy.png", 1);
    }

    public void detonate() {
        this.field = null;
    }
}
