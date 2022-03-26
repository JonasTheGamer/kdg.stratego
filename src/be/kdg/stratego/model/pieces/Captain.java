/*
    Class: Captain
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Captain extends MovingPiece {
    public Captain(Player player) {
        super(player, "captain", "/pieces/captain.png", 6);
    }
}
