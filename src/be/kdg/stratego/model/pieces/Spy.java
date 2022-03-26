/*
    Class: Spy
    Responsiblity: Extends the MovingPiece class with character-specific properties and methods
 */
package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

import java.util.ArrayList;

public class Spy extends MovingPiece {

    public Spy(Player player) {
        super(player, "spy", "/pieces/spy.png", 1);
    }

    @Override
    protected Piece attackLose(Piece piece) {
        if (piece instanceof Marshal) {
            //Spy assassinates the Marshal
            return super.attackWin(piece);
        } else {
            return super.attackLose(piece);
        }
    }
}
