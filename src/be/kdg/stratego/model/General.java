package be.kdg.stratego.model;

public class General extends MovingPiece {

    public General(Player player, Speelveld field) {
        this.player = player;
        this.name = "general";
        this.image = "/pieces/general.png";
        this.rank = 9;
        this.field = field;
    }

    public General(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
