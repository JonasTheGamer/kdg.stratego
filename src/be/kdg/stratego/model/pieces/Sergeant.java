/*
    Class: Sergeant
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Sergeant extends MovingPiece {

    public Sergeant(Player player) {
        super(player, "sergeant", "/pieces/sergeant.png", 4);
    }

}
