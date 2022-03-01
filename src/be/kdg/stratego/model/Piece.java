package be.kdg.stratego.model;

public class Piece {
    protected Player player;
    protected String name;
    protected Speelveld speelveld;
    protected boolean dead;

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speelveld getSpeelveld() {
        return speelveld;
    }

    public void setSpeelveld(Speelveld speelveld) {
        this.speelveld = speelveld;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
