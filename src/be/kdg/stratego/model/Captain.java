package be.kdg.stratego.model;

public class Captain extends MovingPiece {

    public Captain(Player player, Speelveld field) {
        this.player = player;
        this.name = "captain";
        this.image = "/pieces/captain.png";
        this.rank = 6;
        this.field = field;
    }

    public Captain(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
