package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Marshal extends MovingPiece {

    public Marshal(Player player, GameBoardField field) {
        this.player = player;
        this.name = "marshal";
        this.image = "/pieces/marshal.png";
        this.rank = 10;
        this.field = field;
    }

    public Marshal(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
