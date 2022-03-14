package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Sergeant extends MovingPiece {

    public Sergeant(Player player, GameBoardField field) {
        super(player, "sergeant", "/pieces/sergeant.png", 4, field);
    }

    public Sergeant(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
