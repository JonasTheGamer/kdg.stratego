package be.kdg.stratego.model;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.pieces.*;

import java.util.ArrayList;
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
    public ArrayList<Piece> attackNormal(MovingPiece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        // Highest rank always wins
        if (rank < piece.getRank()) {
            killedPieces.add(this);
            this.startKill(); // No need to specify attacker, the winning piece doesn't have to move
        } else if (rank > piece.getRank()) {
            killedPieces.add(piece);
            piece.startKill(this);
        } else {
            // Ranks are equal, both pieces die
            killedPieces.add(this);
            this.startKill();
            killedPieces.add(piece);
            piece.startKill();
        }
        return killedPieces;
    }

    protected ArrayList<Piece> attackMarshal(MovingPiece piece) {
        return attackNormal(piece);
    }


    public ArrayList<Piece> attackBomb(Bomb piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();

        killedPieces.add(this);
        this.startKill();

        return killedPieces;
    }

    public ArrayList<Piece> attack(Piece piece) {
        ArrayList<Piece> killedPieces = new ArrayList<>();
        this.hidden = false;
        piece.setHidden(false);

        if (piece instanceof Marshal) {
            killedPieces = this.attackMarshal((MovingPiece) piece);

        } else if (piece instanceof MovingPiece) {
            killedPieces = this.attackNormal((MovingPiece) piece);

        } else if (piece instanceof Bomb) {
            killedPieces = attackBomb((Bomb) piece);

        } else if (piece instanceof Flag) {
            // Jonas: We won! :D
        }

        return killedPieces;
    }

    // Move
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
