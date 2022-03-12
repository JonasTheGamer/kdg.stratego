package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameBoard {
    private int grootteX;
    private int grootteY;
    private GameBoardField[][] gameBoardFields;

    public GameBoard(double fieldHeight, double fieldWidth) {
        grootteX = 10;
        grootteY = 10;
        gameBoardFields = new GameBoardField[this.grootteX][this.grootteY];

        // Create empty fields
        int boardWidth = grootteX;
        int boardHeight = grootteY;

        int amountOfRowsPerPlayer = (boardHeight - 2) / 2;

        //// Add empty rows on top
        for (int posY = 0; posY < amountOfRowsPerPlayer; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField(posX, posY, fieldHeight, fieldWidth, GameBoardField.GroundType.GRASS));
            }
        }

        //// Add paths & water
        ////// Paths
        for (int posY = amountOfRowsPerPlayer; posY < amountOfRowsPerPlayer + 2; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField(posX, posY, fieldHeight, fieldWidth, GameBoardField.GroundType.GRASS));
            }
        }

        //// Water
        ////// Left water
        this.setGameBoardField(new GameBoardField(2, amountOfRowsPerPlayer, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(3, amountOfRowsPerPlayer, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(2, amountOfRowsPerPlayer + 1, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(3, amountOfRowsPerPlayer + 1, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));

        ////// Right water
        this.setGameBoardField(new GameBoardField(6, amountOfRowsPerPlayer, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(7, amountOfRowsPerPlayer, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(6, amountOfRowsPerPlayer + 1, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));
        this.setGameBoardField(new GameBoardField(7, amountOfRowsPerPlayer + 1, fieldHeight, fieldWidth, GameBoardField.GroundType.WATER));

        //// Add empty rows on the bottom
        for (int posY = amountOfRowsPerPlayer + 2; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField(posX, posY, fieldHeight, fieldWidth, GameBoardField.GroundType.GRASS));
            }
        }
    }

    public GameBoard() {
        this(Style.size(50), Style.size(50));
    }

    public int getGrootteX() {
        return grootteX;
    }

    public void setGrootteX(int grootteX) {
        this.grootteX = grootteX;
    }

    public int getGrootteY() {
        return grootteY;
    }

    public void setGrootteY(int grootteY) {
        this.grootteY = grootteY;
    }

    public GameBoardField[][] getGameBoardFields() {
        return this.gameBoardFields;
    }

    public GameBoardField getGameBoardField(int positieX, int positieY) {
        GameBoardField foundField = null;
        for (GameBoardField[] fieldsRow: gameBoardFields) {
            for (GameBoardField field: fieldsRow) {
                if(field.positionX == positieX && field.positionY == positieY) {
                    foundField = field;
                }
            }
        }
        return foundField;
    }

    public void setGameBoardField(GameBoardField field) {
        this.gameBoardFields[field.getPositionX()][field.getPositionY()] = field;
    }

    public Piece getPiece(int positieX, int positieY) {
        GameBoardField field = this.getGameBoardField(positieX, positieY);

        if(Objects.isNull(field)) {
            return null;
        } else {
            return field.getPiece();
        }
    }

    public void rotate() {

        // First, give all fields a new position
        for (int posX = 0; posX < grootteX; posX++) {
            for (int posY = 0; posY < grootteY; posY++) {
                GameBoardField field = gameBoardFields [posX][posY];
                field.setPositionX(grootteX - 1 - field.getPositionX());
                field.setPositionY(grootteY  - 1 - field.getPositionY());
            }
        }

        // Then, loop trough them to update their position in the 2D array
        GameBoardField[][] tempGameBoardFields = new GameBoardField[grootteX][grootteY];

        for (int posX = 0; posX < grootteX; posX++) {
            for (int posY = 0; posY < grootteY; posY++) {
                GameBoardField field = gameBoardFields [posX][posY];
                tempGameBoardFields[field.getPositionX()][field.getPositionY()] = field;
            }
        }

        // Update the 2D array that holds all pieces
        gameBoardFields = tempGameBoardFields;
    }

    // Utility methods for reversing the board fields
    public static List<GameBoardField> gbfArrayToList(GameBoardField[] arr) {
        List<GameBoardField> intList = new ArrayList<GameBoardField>(arr.length);

        for (GameBoardField i : arr)
        {
            intList.add(i);
        }

        return intList;
    }
    public static List<GameBoardField[]> gbfArrArrayToList(GameBoardField[][] arr) {
        List<GameBoardField[]> gbfList = new ArrayList<>(arr.length);

        for (GameBoardField[] i : arr)
        {
            gbfList.add(i);
        }
        return gbfList;
    }

    public static GameBoardField[] gbfListToArray(List<GameBoardField> list) {
        GameBoardField[] convertedArray = new GameBoardField[list.size()];
        for (int index = 0; index < list.size(); index++) {
            convertedArray[index] = list.get(index);
        }
        return convertedArray;
    }
    public static GameBoardField[][] gbfArrListToArray(List<GameBoardField[]> list) {
        GameBoardField[][] convertedArray = new GameBoardField[list.size()][list.get(0).length];
        for (int index = 0; index < list.size(); index++) {
            convertedArray[index] = list.get(index);
        }
        return convertedArray;
    }


}
