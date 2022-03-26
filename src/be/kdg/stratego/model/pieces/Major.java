/*
    Class: Major
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Major extends MovingPiece {

    public Major(Player player) {
        super(player, "major", "/pieces/major.png", 7);
    }

}
