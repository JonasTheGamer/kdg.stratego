/*
    Class: General
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class General extends MovingPiece {

    public General(Player player) {
        super(player, "general", "/pieces/general.png", 9);
    }

}
