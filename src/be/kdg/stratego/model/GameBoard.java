/*
    Class: GameBoard
    Responsibility: Takes care of everything that has to do with the GameBoard, such as placing
                    pieces, rotating the board,...

*/
package be.kdg.stratego.model;

public class GameBoard {
    private final int GROOTTE_X = 10;
    private final int GROOTTE_Y = 10;
    private GameBoardField[][] gameBoardFields;

    public GameBoard() {
        gameBoardFields = new GameBoardField[this.GROOTTE_X][this.GROOTTE_Y];

        //// Add fields
        for (int posY = 0; posY < GROOTTE_Y; posY++) {
            // Per row
            for (int posX = 0; posX < GROOTTE_X; posX++) {
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
        GameBoardField[][] rotatedGameBoardFields = new GameBoardField[GROOTTE_X][GROOTTE_Y];

        for (GameBoardField[] fieldRow : this.gameBoardFields) {
            for (GameBoardField field : fieldRow) {

                // Reverse field position
                field.setPositionX(GROOTTE_X - 1 - field.getPositionX());
                field.setPositionY(GROOTTE_Y - 1 - field.getPositionY());

                // Fill empty 2D array
                rotatedGameBoardFields[field.getPositionX()][field.getPositionY()] = field;

            }
        }

        // Update our 2D array
        this.gameBoardFields = rotatedGameBoardFields;
    }

    // Highlight the allowed moves
    public void highLightAllowedMoves(MovingPiece piece) {
        GameBoardField field = piece.getField();

        // Unhighlight all fields
        unHighlightAllFields();

        // Highlight the field itself
        field.highLight();

        // Highlight them
        for (GameBoardField fieldToHighlight : piece.getAllowedMoves()) {
            fieldToHighlight.highLight();
        }
    }

    // Unhighlight all fields
    public void unHighlightAllFields() {
        // Loop through all fields
        for (int posX = 0; posX < GROOTTE_X; posX++) {
            for (int posY = 0; posY < GROOTTE_Y; posY++) {
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
        return GROOTTE_Y - y;
    }

    // Getters
    public int getGROOTTE_Y() {
        return GROOTTE_Y;
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
        for (int posY = 0; posY < GROOTTE_Y; posY++) {
            for (int posX = 0; posX < GROOTTE_X; posX++) {
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
