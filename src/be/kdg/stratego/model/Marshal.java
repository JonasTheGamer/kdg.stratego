package be.kdg.stratego.model;

public class Marshal extends MovingPiece {

    public Marshal(Player player, Speelveld field) {
        this.player = player;
        this.name = "marshal";
        this.image = "/pieces/marshal.png";
        this.rank = 10;
        this.field = field;
    }

    public Marshal(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
