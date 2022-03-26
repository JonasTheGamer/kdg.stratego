/*
    Class: Bomb
    Responsiblity: Extends the Piece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

public class Bomb extends Piece {
    public Bomb(Player player) {
        super(player,"bomb","/pieces/bomb.png", 0);
    }
}
