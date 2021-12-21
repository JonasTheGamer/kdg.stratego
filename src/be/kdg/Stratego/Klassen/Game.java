package be.kdg.Stratego.Klassen;

import java.time.LocalDateTime;

public class Game {
    private Speler[] spelers;
    private LocalDateTime startTijd;
    private LocalDateTime eindTijd;

    public Speler[] getSpelers() {
        return spelers;
    }

    public void setSpelers(Speler[] spelers) {
        this.spelers = spelers;
    }

    public LocalDateTime getStartTijd() {
        return startTijd;
    }

    public void setStartTijd(LocalDateTime startTijd) {
        this.startTijd = startTijd;
    }

    public LocalDateTime getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(LocalDateTime eindTijd) {
        this.eindTijd = eindTijd;
    }

    public void start() {

    }

    public boolean opslaan() {
        return false;
    }

    public void hervat(Speelbord speelbord) {

    }

    public void stop() {

    }
}
