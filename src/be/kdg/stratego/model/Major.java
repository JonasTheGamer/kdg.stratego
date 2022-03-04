package be.kdg.stratego.model;

public class Major extends MovingPiece {

    public Major(Player player, Speelveld field) {
        this.player = player;
        this.name = "major";
        this.image = "/pieces/major.png";
        this.rank = 7;
        this.field = field;
    }

    public Major(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
