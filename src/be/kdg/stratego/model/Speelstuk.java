package be.kdg.stratego.model;

public class Speelstuk {
    protected Speler speler;
    protected String naam;
    protected Speelveld speelveld;
    protected boolean dood;

    public Speler getSpeler() {
        return speler;
    }

    public void setSpeler(Speler speler) {
        this.speler = speler;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public Speelveld getSpeelveld() {
        return speelveld;
    }

    public void setSpeelveld(Speelveld speelveld) {
        this.speelveld = speelveld;
    }

    public boolean isDood() {
        return dood;
    }

    public void setDood(boolean dood) {
        this.dood = dood;
    }
}
