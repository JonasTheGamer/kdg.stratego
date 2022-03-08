package be.kdg.stratego.model;

import java.util.ArrayList;
import java.util.Objects;

public class GameBoard {
    private int grootteX;
    private int grootteY;
    private Speelveld[][] speelvelden;

    public GameBoard() {
        grootteX = 10;
        grootteY = 10;
        speelvelden = new Speelveld[this.grootteX][this.grootteY];
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

    public Speelveld[][] getSpeelvelden() {
        return this.speelvelden;
    }

    public Speelveld getSpeelveld(int positieX, int positieY) {
        Speelveld foundField = null;
        for (Speelveld[] fieldsRow: speelvelden) {
            for (Speelveld field: fieldsRow) {
                if(field.positionX == positieX && field.positionY == positieY) {
                    foundField = field;
                }
            }
        }
        return foundField;
    }

    public void setSpeelveld(Speelveld field) {
        this.speelvelden[field.positionX][field.positionY] = field;
    }

    public Piece getSpeelstuk(int positieX, int positieY) {
        Speelveld field = this.getSpeelveld(positieX, positieY);

        if(Objects.isNull(field)) {
            return null;
        } else {
            return field.getPiece();
        }
    }

    public Piece[] zoekSpeelstukken(Player player) {
        ArrayList<Piece> pieces = new ArrayList<Piece>();
        for (Speelveld[] fieldsRow: speelvelden) {
            for (Speelveld field: fieldsRow) {
                if(field.getPiece().getPlayer().equals(player)) {
                    pieces.add(field.getPiece());
                }
            }
        }

        return (Piece[]) pieces.toArray();
    }


}
