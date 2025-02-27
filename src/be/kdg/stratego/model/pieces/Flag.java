/*
    Class: Flag
    Responsiblity: Extends the Piece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

public class Flag extends Piece {
    public Flag(Player player, String flagImage) {
        super(player, "flag", (flagImage != null ? flagImage : "/pieces/flag.png"), 0);
    }

}
