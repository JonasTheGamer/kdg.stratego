package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

public class Bomb extends Piece {

    public Bomb(Player player, GameBoardField field ) {
        super(player,"bomb","/pieces/bomb.png",field);
    }

    public Bomb(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
