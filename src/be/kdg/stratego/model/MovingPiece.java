/*
    Class: MovingPiece
    Responsibility: Manages everything that a moving piece can do on the board, such as moving & attacking,
                    and finding out the allowed moves for this piece in particular
 */
package be.kdg.stratego.model;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.pieces.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;

public abstract class MovingPiece extends Piece {

    public MovingPiece(Player player, String name, String image, int rank) {
        super(player, name, image, rank);
    }

    // Methods
    /// Move
    public ArrayList<Piece> moveTo(GameBoardField destination) throws InvalidMoveException {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        if (this.getAllowedMoves().contains(destination)) {
            if (!destination.isOccupied()) {
                this.removeFromField();
                this.placeOnField(destination);
            } else {
                killedPieces.addAll(this.attack(destination.getPiece()));
            }

        } else {
            throw new InvalidMoveException();
        }

        return killedPieces;
    }

    /// Attack
    public ArrayList<Piece> attack(Piece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();
        this.hidden = false;
        piece.setHidden(false);

        if (piece instanceof MovingPiece) {
            MovingPiece movingPiece = (MovingPiece)piece;

            if (rank < movingPiece.getRank()) {
                //Piece loses the attack
                killedPieces.add(attackLose(piece));
            } else if (rank > movingPiece.getRank()) {
                //Piece wins the attack
                killedPieces.add(attackWin(piece));
            } else {
                //Piece wins the attack at the cost of their own life
                killedPieces.add(this);
                this.startKill();
                killedPieces.add(piece);
                piece.startKill();
            }
        } else if (piece instanceof Bomb) {
            //Piece gets blown up
            killedPieces.add(attackLose(piece));
        } else if (piece instanceof Flag) {
            //We won! :D (catch flag from returned list in presenter)
            killedPieces.add(attackWin(piece));
        }
        return killedPieces;
    }

    ///Sub attacks (for overwriting purposes)
    protected Piece attackWin(Piece piece) {
        piece.startKill(this);
        return piece;
    }

    protected Piece attackLose(Piece piece) {
        this.startKill();
        return this;
    }

    // Highlight the allowed moves
    public void highLightAllowedMoves() {
        GameBoardField field = this.getField();

        // Highlight the field itself
        field.highLight();

        // Highlight them
        for (GameBoardField fieldToHighlight : this.getAllowedMoves()) {
            fieldToHighlight.highLight();
        }
    }

    // Getters
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
