package be.kdg.Stratego.Klassen;

import java.util.ArrayList;
import java.util.Objects;

public class Speelbord {
    private int grootteX = 10;
    private int grootteY = 10;
    private Speelveld[][] speelvelden;

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

    public Speelveld getSpeelveld(int positieX, int positieY) {
        Speelveld foundField = null;
        for (Speelveld[] fieldsRow: speelvelden) {
            for (Speelveld field: fieldsRow) {
                if(field.positieX == positieX && field.positieY == positieY) {
                    foundField = field;
                }
            }
        }
        return foundField;
    }

    public Speelstuk getSpeelstuk(int positieX, int positieY) {
        Speelveld field = this.getSpeelveld(positieX, positieY);

        if(Objects.isNull(field)) {
            return null;
        } else {
            return field.getSpeelstuk();
        }
    }

    public Speelstuk[] zoekSpeelstukken(Speler player) {
        ArrayList<Speelstuk> pieces = new ArrayList<Speelstuk>();
        for (Speelveld[] fieldsRow: speelvelden) {
            for (Speelveld field: fieldsRow) {
                if(field.getSpeelstuk().getSpeler().equals(player)) {
                    pieces.add(field.getSpeelstuk());
                }
            }
        }

        return (Speelstuk[]) pieces.toArray();
    }
}
