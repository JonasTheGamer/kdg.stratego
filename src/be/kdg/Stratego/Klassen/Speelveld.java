package be.kdg.Stratego.Klassen;

public class Speelveld extends Positie {
    private int grootteX;
    private int grootteY;
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
        return null;
    }

    public Speelstuk getSpeelstuk(int positieX, int positieY) {
        return null;
    }
}
