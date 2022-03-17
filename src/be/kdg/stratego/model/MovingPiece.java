package be.kdg.stratego.model;

import be.kdg.stratego.exceptions.InvalidMoveException;

import java.util.HashSet;
import java.util.Objects;

public abstract class MovingPiece extends Piece {
    protected int rank;

    public MovingPiece(Player player, String name, String image, int rank) {
        super(player, name, image);
        this.rank = rank;
    }

    // Methods
    // Attack
    protected void attack(Piece piece) {
        if (piece.getName().equals("flag")) {
            //Liam: Game.stop()
            // Jonas:
        } else if (piece.getName().equals("bomb")) {
            this.field = null;
            System.out.println("Your " + this.name + " was killed by a " + piece.getName());
        } else if (true/*Liam: Speelstuk instanceof ....*/) {
            MovingPiece movingPiece = (MovingPiece) piece;
            if (this.rank > movingPiece.getRank()) {
                piece.removeFromField();
                System.out.println("Your " + this.name + " has killed a " + piece.getName());
            } else {
                this.field = null;
                System.out.println("Your " + this.name + " was killed by a " + piece.getName());
            }
        }
    }

    // Move
    public void moveTo(GameBoardField destination) throws InvalidMoveException {
        if(this.getAllowedMoves().contains(destination)) {
            this.removeFromField();
            this.placeOnField(destination);
        } else {
            throw new InvalidMoveException();
        }
    }

    // Getters
    public int getRank() {
        return rank;
    }
    public HashSet<GameBoardField> getAllowedMoves() {
        HashSet<GameBoardField> allowedMoves = new HashSet<>();
        HashSet<GameBoardField> allFields = new HashSet<>();

        // Get surrounding fields
        GameBoardField fieldOnTop = field.getFieldAbove();
        GameBoardField fieldOnBottom = field.getFieldBelow();
        GameBoardField fieldOnLeft = field.getFieldOnLeft();
        GameBoardField fieldOnRight = field.getFieldOnRight();

        if (!Objects.isNull(fieldOnTop) && fieldOnTop.isWalkable()) allFields.add(fieldOnTop);
        if (!Objects.isNull(fieldOnBottom) && fieldOnBottom.isWalkable()) allFields.add(fieldOnBottom);
        if (!Objects.isNull(fieldOnLeft) && fieldOnLeft.isWalkable()) allFields.add(fieldOnLeft);
        if (!Objects.isNull(fieldOnRight) && fieldOnRight.isWalkable()) allFields.add(fieldOnRight);

        // Check if the fields are allowed (basically, no piece from the same player can be on it)
        for (GameBoardField fieldToCheck : allFields) {
            if (fieldToCheck.getPiece() == null || fieldToCheck.getPiece().getPlayer() != this.player) {
                allowedMoves.add(fieldToCheck);
            }
        }
        return allowedMoves;
    }
}
