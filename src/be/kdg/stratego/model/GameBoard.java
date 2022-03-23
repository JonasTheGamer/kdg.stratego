package be.kdg.stratego.model;

import java.util.HashSet;

public class GameBoard {
    private final int grootteX = 10;
    private final int grootteY = 10;
    private GameBoardField[][] gameBoardFields;

    public GameBoard() {
        gameBoardFields = new GameBoardField[this.grootteX][this.grootteY];

        //// Add fields
        for (int posY = 0; posY < grootteY; posY++) {
            // Per row
            for (int posX = 0; posX < grootteX; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField(this, posX, posY, GameBoardField.GroundType.GRASS));
            }
        }

        //// Water
        ////// Left water
        this.setGameBoardField(new GameBoardField(this, coordinateX(3), coordinateY(5), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(3), coordinateY(6), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(4), coordinateY(5), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(4), coordinateY(6), GameBoardField.GroundType.WATER));

        ////// Right water
        this.setGameBoardField(new GameBoardField(this, coordinateX(7), coordinateY(5), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(7), coordinateY(6), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(8), coordinateY(5), GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(this, coordinateX(8), coordinateY(6), GameBoardField.GroundType.WATER));
    }

    // Methods
    // Rotate the board
    public void rotate() {
        GameBoardField[][] tempGameBoardFields = new GameBoardField[grootteX][grootteY];

        for (GameBoardField[] fieldRow : gameBoardFields) {
            for (GameBoardField field : fieldRow) {

                // Change field position
                field.setPositionX(grootteX - 1 - field.getPositionX());
                field.setPositionY(grootteY - 1 - field.getPositionY());

                // Update their position in the 2D array
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

    public int coordinateX(int x) {
        //Humanreadable version x in GameBoard(Field)
        return x - 1;
    }

    public int coordinateY(int y) {
        //Humanreadable version y in GameBoard(Field)
        return grootteY - y;
    }

    // Getters
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

    public GameBoardField getNextAvailableField() {
        GameBoardField availableField = null;
        for (int posY = 0; posY < grootteY; posY++) {
            for (int posX = 0; posX < grootteX; posX++) {
                GameBoardField fieldOnThisPosition = getGameBoardField(posX, posY);
                if (!fieldOnThisPosition.isOccupied()) {
                    availableField = fieldOnThisPosition;
                    break;
                }
            }
        }

        return availableField;
    }

    // Setters
    public void setGameBoardField(GameBoardField field) {
        this.gameBoardFields[field.getPositionX()][field.getPositionY()] = field;
    }
}
