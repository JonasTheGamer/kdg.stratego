package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Scout extends MovingPiece {

    public Scout(Player player, GameBoardField field) {
        this.player = player;
        this.name = "scout";
        this.image = "/pieces/scout.png";
        this.rank = 2;
        this.field = field;
    }

    public Scout(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
