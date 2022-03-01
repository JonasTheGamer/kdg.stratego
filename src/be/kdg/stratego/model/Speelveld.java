package be.kdg.stratego.model;

public class Speelveld extends Position {
    private Piece speelstuk;
    private boolean bewandelbaar;
    private String achtergrondAfbeelding;

    public Piece getSpeelstuk() {
        return speelstuk;
    }

    public void setSpeelstuk(Piece speelstuk) {
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
