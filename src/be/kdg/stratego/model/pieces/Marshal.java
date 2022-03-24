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

    @Override
    public ArrayList<Piece> attackMarshal(Marshal piece) {
        // Just run the attackNormal method as the normal behavior is ok here
        return super.attackNormal(piece);
    }
}
