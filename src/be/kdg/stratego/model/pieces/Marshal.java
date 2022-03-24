package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;

import java.util.ArrayList;

public class Marshal extends MovingPiece {

    public Marshal(Player player) {
        super(player, "marshal", "/pieces/marshal.png", 10);
    }
}
