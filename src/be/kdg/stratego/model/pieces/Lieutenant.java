/*
    Class: Lieutenant
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Lieutenant extends MovingPiece {

    public Lieutenant(Player player) {
        super(player, "lieutenant", "/pieces/lieutenant.png", 5);
    }

}
