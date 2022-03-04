package be.kdg.stratego.model;

public class Miner extends MovingPiece {

    public Miner(Player player, Speelveld field) {
        this.player = player;
        this.name = "miner";
        this.image = "/pieces/miner.png";
        this.rank = 3;
        this.field = field;
    }

    public Miner(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
