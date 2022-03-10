package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;
import be.kdg.stratego.view.Style;

import java.util.ArrayList;
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
                this.setGameBoardField(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }

        //// Add paths & water
        ////// Paths
        for (int posY = amountOfRowsPerPlayer; posY < amountOfRowsPerPlayer + 2; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }

        //// Water
        ////// Left water
        this.setGameBoardField(new GameBoardField("water", 2, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 3, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 2, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 3, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        ////// Right water
        this.setGameBoardField(new GameBoardField("water", 6, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 7, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 6, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        this.setGameBoardField(new GameBoardField("water", 7, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        //// Add empty rows on the bottom
        for (int posY = amountOfRowsPerPlayer + 2; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                this.setGameBoardField(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
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
        this.gameBoardFields[field.positionX][field.positionY] = field;
    }

    public Piece getSpeelstuk(int positieX, int positieY) {
        GameBoardField field = this.getGameBoardField(positieX, positieY);

        if(Objects.isNull(field)) {
            return null;
        } else {
            return field.getPiece();
        }
    }

    public Piece[] zoekSpeelstukken(Player player) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (GameBoardField[] fieldsRow: gameBoardFields) {
            for (GameBoardField field: fieldsRow) {
                if(field.getPiece().getPlayer().equals(player)) {
                    pieces.add(field.getPiece());
                }
            }
        }

        return (Piece[]) pieces.toArray();
    }


}
