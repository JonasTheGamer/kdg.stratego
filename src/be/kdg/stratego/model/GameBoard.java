package be.kdg.stratego.model;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoard {
    private int grootteX;
    private int grootteY;
    private GameBoardField[][] gameBoardFields;

    public GameBoard() {
        grootteX = 10;
        grootteY = 10;
        gameBoardFields = new GameBoardField[this.grootteX][this.grootteY];
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

    public void setGameBoardView(GameBoardField field) {
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
