/*
    Class: Colonel
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

public class Colonel extends MovingPiece {
    public Colonel(Player player) {
        super(player, "colonel", "/pieces/colonel.png", 8);
    }
}
