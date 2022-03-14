package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Marshal extends MovingPiece {

    public Marshal(Player player) {
        super(player, "marshal", "/pieces/marshal.png", 10);
    }

    public void detonate() {
        this.field = null;
    }
}
