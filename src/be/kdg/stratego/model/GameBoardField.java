/*
    Class: GameBoardField
    Responsiblity: Handles everything regarding one field on the gameboard, such as remembering the position,
                    the ground type,...
*/

package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import java.util.Objects;

public class GameBoardField extends Position {
    private final double FIELDSIZE = Style.size(50);

    // GameBoardField properties
    private Piece piece;
    private boolean walkable;
    private boolean highlighted;
    private GameBoard gameBoard;

    // Enumeration for ground type
    public enum GroundType {
        GRASS,
        WATER
    }

    public GameBoardField(GameBoard gameBoard, int posX, int posY, GroundType groundType) {
        // Set gameboard
        this.gameBoard = gameBoard;

        // Set position
        this.positionX = posX;
        this.positionY = posY;

        // Set highligted to false
        this.highlighted = false;

        // Figure out whether the field is walkable.
        this.walkable = (groundType != GroundType.WATER);
    }

    // Methods
    /// Highlight field
    public void highLight() {
        this.highlighted = true;
    }

    /// Unhighlight field
    public void unHighLight() {
        this.highlighted = false;
    }

    // Getters
    public double getFIELDSIZE() {
        return FIELDSIZE;
    }

    public Piece getPiece() {
        return piece;
    }

    public boolean isHighlighted() {
        return highlighted;
    }

    public boolean isOccupied() {
        return !(Objects.isNull(this.piece));
    }

    public boolean isWalkable() {
        return this.walkable;
    }

    public GameBoardField getFieldAbove() {
        return gameBoard.getGameBoardField(this.positionX, this.positionY - 1);
    }

    public GameBoardField getFieldBelow() {
        return gameBoard.getGameBoardField(this.positionX, this.positionY + 1);
    }

    public GameBoardField getFieldOnLeft() {
        return gameBoard.getGameBoardField(this.positionX - 1, this.positionY);
    }

    public GameBoardField getFieldOnRight() {
        return gameBoard.getGameBoardField(this.positionX + 1, this.positionY);
    }

    // Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
