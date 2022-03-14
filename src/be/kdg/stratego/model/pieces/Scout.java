package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Scout extends MovingPiece {

    public Scout(Player player) {
        super(player, "scout", "/pieces/scout.png", 2);
    }

    public void detonate() {
        this.field = null;
    }
}
