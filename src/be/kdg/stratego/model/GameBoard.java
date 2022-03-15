package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;

import java.util.HashSet;
import java.util.Objects;

public class GameBoard {
    private int grootteX;
    private int grootteY;
    private final double gameBoardFieldHeight = Style.size(50);
    private final double gameBoardFieldWidth = Style.size(50);
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
                this.setGameBoardField(new GameBoardField(posX, posY, GameBoardField.GroundType.GRASS));
            }
        }

        //// Water
        ////// Left water
        this.setGameBoardField(new GameBoardField(2, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(3, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(2, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(3, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));

        ////// Right water
        this.setGameBoardField(new GameBoardField(6, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(7, amountOfRowsPerPlayer, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(6, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(7, amountOfRowsPerPlayer + 1, GameBoardField.GroundType.WATER));
    }

    // Methods
    // Method to rotate the board
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

    public HashSet<GameBoardField> getAllowedMoves(MovingPiece piece) {
        HashSet<GameBoardField> allowedMoves = new HashSet<>();
        HashSet<GameBoardField> allFields = new HashSet<>();
        GameBoardField field = piece.getField();

        // First, check if the piece is a scout
        if (piece.rank == 2) {
            // Do stuff for scout
        } else {
            // Do stuff for other pieces. They can move one step at a time
            GameBoardField fieldOnTop = this.getGameBoardField(field.positionX, field.positionY - 1);
            GameBoardField fieldOnBottom = this.getGameBoardField(field.positionX, field.positionY + 1);
            GameBoardField fieldOnLeft = this.getGameBoardField(field.positionX - 1, field.positionY);
            GameBoardField fieldOnRight = this.getGameBoardField(field.positionX + 1, field.positionY);

            if(!Objects.isNull(fieldOnTop) && fieldOnTop.isWalkable()) allFields.add(fieldOnTop);
            if(!Objects.isNull(fieldOnBottom) && fieldOnBottom.isWalkable()) allFields.add(fieldOnBottom);
            if(!Objects.isNull(fieldOnLeft) && fieldOnLeft.isWalkable()) allFields.add(fieldOnLeft);
            if(!Objects.isNull(fieldOnRight) && fieldOnRight.isWalkable()) allFields.add(fieldOnRight);
        }

        // Check if the fields are allowed (basically, no piece from the same player can be on it)
        for (GameBoardField fieldToCheck : allFields) {
            if (fieldToCheck.getPiece() == null || fieldToCheck.getPiece().getPlayer() != piece.getPlayer()) {
                allowedMoves.add(fieldToCheck);
            }
        }

        return allowedMoves;
    }

    // Method to highlight allowed moves
    public void highLightAllowedMoves(MovingPiece piece) {
        GameBoardField field = piece.getField();

        // Unhighlight all fields
        unHighlightAllFields();

        // Highlight the field itself
        field.highLight();

        // Get the allowed moves
        HashSet<GameBoardField> allowedMoves = getAllowedMoves(piece);

        // Highlight them
        for (GameBoardField fieldToHighlight : allowedMoves) {
            fieldToHighlight.highLight();
        }
    }

    // Method to unhighlight all fields
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
