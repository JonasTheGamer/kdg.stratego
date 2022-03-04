package be.kdg.stratego.model;

public class Sergeant extends MovingPiece {

    public Sergeant(Player player, Speelveld field) {
        this.player = player;
        this.name = "sergeant";
        this.image = "/pieces/sergeant.png";
        this.rank = 4;
        this.field = field;
    }

    public Sergeant(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
