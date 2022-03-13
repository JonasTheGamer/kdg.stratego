package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Sergeant extends MovingPiece {

    public Sergeant(Player player, GameBoardField field) {
        this.player = player;
        this.name = "sergeant";
        this.image = "/pieces/sergeant.png";
        this.rank = 4;
        this.field = field;
    }

    public Sergeant(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
