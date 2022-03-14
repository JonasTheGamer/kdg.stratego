package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

public class Flag extends Piece {

    public Flag(Player player, String flagImage, GameBoardField field) {
        super(player, "flag", "", field);

        switch (flagImage) {
            default: this.setImage("/pieces/flag.png");
        }
    }

    public Flag(Player player, String flagImagePath) {
        this(player, flagImagePath, null);
    }

}
