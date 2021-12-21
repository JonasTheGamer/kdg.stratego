package be.kdg.Stratego.Klassen;

public class Speler {
    private String naam;
    private String kleur;
    private boolean klaarOmTeSpelen;

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getKleur() {
        return kleur;
    }

    public void setKleur(String kleur) {
        this.kleur = kleur;
    }

    public boolean isKlaarOmTeSpelen() {
        return klaarOmTeSpelen;
    }

    public void setKlaarOmTeSpelen(boolean klaarOmTeSpelen) {
        this.klaarOmTeSpelen = klaarOmTeSpelen;
    }
}
