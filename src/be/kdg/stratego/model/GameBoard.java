package be.kdg.stratego.model;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.view.Style;

import java.util.HashSet;
import java.util.Objects;

public class GameBoard {
    private int grootteX;
    private int grootteY;
    private GameBoardField[][] gameBoardFields;

    public GameBoard() {
        grootteX = 10;
        grootteY = 10;
        gameBoardFields = new GameBoardField[this.grootteX][this.grootteY];

        // Create empty fields
        int boardWidth = grootteX;
        int boardHeight = grootteY;

        int amountOfRowsPerPlayer = boardHeight / 2 - 1;

        //// Add fields
        for (int posY = 0; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField(this, posX, posY, GameBoardField.GroundType.GRASS));
            }
        }

        //// Water
        ////// Left water
        this.setGameBoardField(new GameBoardField(this,2, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,3, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,2, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,3, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));

        ////// Right water
        this.setGameBoardField(new GameBoardField(this,6, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,7, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,6, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this,7, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
    }

    // Methods
    // Rotate the board
    public void rotate() {

        // First, give all fields a new position
        for (int posX = 0; posX < grootteX; posX++) {
            for (int posY = 0; posY < grootteY; posY++) {
                GameBoardField field = gameBoardFields[posX][posY];
                field.setPositionX(grootteX - 1 - field.getPositionX());
                field.setPositionY(grootteY - 1 - field.getPositionY());
            }
        }

        // Then, loop trough them to update their position in the 2D array
        GameBoardField[][] tempGameBoardFields = new GameBoardField[grootteX][grootteY];

        for (int posX = 0; posX < grootteX; posX++) {
            for (int posY = 0; posY < grootteY; posY++) {
                GameBoardField field = gameBoardFields[posX][posY];
                tempGameBoardFields[field.getPositionX()][field.getPositionY()] = field;
            }
        }

        // Update the 2D array that holds all pieces
        gameBoardFields = tempGameBoardFields;
    }

    // Highlight the allowed moves
    public void highLightAllowedMoves(MovingPiece piece) {
        GameBoardField field = piece.getField();

        // Unhighlight all fields
        unHighlightAllFields();

        // Highlight the field itself
        field.highLight();

        // Get the allowed moves
        HashSet<GameBoardField> allowedMoves = piece.getAllowedMoves();

        // Highlight them
        for (GameBoardField fieldToHighlight : allowedMoves) {
            fieldToHighlight.highLight();
        }
    }

    // Unhighlight all fields
    public void unHighlightAllFields() {
        // Loop through all fields
        for (int posX = 0; posX < grootteX; posX++) {
            for (int posY = 0; posY < grootteY; posY++) {
                GameBoardField field = gameBoardFields[posX][posY];
                field.unHighLight();
            }
        }
    }

    // Getters
    public int getGrootteX() {
        return grootteX;
    }

    public int getGrootteY() {
        return grootteY;
    }

    public GameBoardField[][] getGameBoardFields() {
        return this.gameBoardFields;
    }

    public GameBoardField getGameBoardField(int posX, int posY) {
        GameBoardField foundField = null;

        // We're looping through it this way so it throws null instead of an indexOutOfBoundsException
        for (GameBoardField[] fieldsRow : gameBoardFields) {
            for (GameBoardField field : fieldsRow) {
                if (field.positionX == posX && field.positionY == posY) {
                    foundField = field;
                }
            }
        }
        return foundField;
    }

    // Setters
    public void setGameBoardField(GameBoardField field) {
        this.gameBoardFields[field.getPositionX()][field.getPositionY()] = field;
    }

    public void clearGameBoardFields() {
        for (int posX = 0; posX < getGrootteX(); posX++) {
            for (int posY = 0; posY < getGrootteY(); posY++) {
                gameBoardFields[posX][posY] = null;
            }
        }
    }
}
