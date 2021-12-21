package be.kdg.Stratego.Klassen;

public class BewegendSpeelstuk extends Speelstuk {
    protected int rang;

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public void veranderPositie(int positieX, int positieY) {
        aanvallen(null);
    }

    protected void aanvallen(Speelstuk speelstuk) {

    }
}
