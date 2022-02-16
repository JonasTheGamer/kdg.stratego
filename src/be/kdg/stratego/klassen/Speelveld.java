package be.kdg.stratego.klassen;

public class Speelveld extends Positie {
    private Speelstuk speelstuk;
    private boolean bewandelbaar;
    private String achtergrondAfbeelding;

    public Speelstuk getSpeelstuk() {
        return speelstuk;
    }

    public void setSpeelstuk(Speelstuk speelstuk) {
        this.speelstuk = speelstuk;
    }

    public boolean isBewandelbaar() {
        return bewandelbaar;
    }

    public void setBewandelbaar(boolean bewandelbaar) {
        this.bewandelbaar = bewandelbaar;
    }

    public String getAchtergrondAfbeelding() {
        return achtergrondAfbeelding;
    }

    public void setAchtergrondAfbeelding(String achtergrondAfbeelding) {
        this.achtergrondAfbeelding = achtergrondAfbeelding;
    }
}
