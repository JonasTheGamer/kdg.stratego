package be.kdg.stratego.model.pieces;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.MovingPiece;
import be.kdg.stratego.model.Player;

import java.util.HashSet;
import java.util.Objects;

public class Scout extends MovingPiece {

    public Scout(Player player) {
        super(player, "scout", "/pieces/scout.png", 2);
    }

    @Override
    public HashSet<GameBoardField> getAllowedMoves() {
        HashSet<GameBoardField> allowedMoves = new HashSet<>();
        HashSet<GameBoardField> allFields = new HashSet<>();

        // Get all fields on the left
        GameBoardField fieldOnLeft = this.field.getFieldOnLeft();
        while (!Objects.isNull(fieldOnLeft) && fieldOnLeft.isWalkable()) {
            allFields.add(fieldOnLeft);
            if(fieldOnLeft.isOccupied()) break;
            fieldOnLeft = fieldOnLeft.getFieldOnLeft();
        }

        // Get all fields on the right
        GameBoardField fieldOnRight = this.field.getFieldOnRight();
        while (!Objects.isNull(fieldOnRight) && fieldOnRight.isWalkable()) {
            allFields.add(fieldOnRight);
            if(fieldOnRight.isOccupied()) break;
            fieldOnRight = fieldOnRight.getFieldOnRight();
        }

        // Get all fields above
        GameBoardField fieldAbove = this.field.getFieldAbove();
        while (!Objects.isNull(fieldAbove) && fieldAbove.isWalkable()) {
            allFields.add(fieldAbove);
            if(fieldAbove.isOccupied()) break;
            fieldAbove = fieldAbove.getFieldAbove();
        }

        // Get all fields below
        GameBoardField fieldBelow = this.field.getFieldBelow();
        while (!Objects.isNull(fieldBelow) && fieldBelow.isWalkable()) {
            allFields.add(fieldBelow);
            if(fieldBelow.isOccupied()) break;
            fieldBelow = fieldBelow.getFieldBelow();
        }

        // Check if the fields are allowed (basically, no piece from the same player can be on it)
        for (GameBoardField fieldToCheck : allFields) {
            if (fieldToCheck.getPiece() == null || fieldToCheck.getPiece().getPlayer() != this.player) {
                allowedMoves.add(fieldToCheck);
            }
        }
        return allowedMoves;
    }
}
