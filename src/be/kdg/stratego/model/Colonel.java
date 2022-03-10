package be.kdg.stratego.model;

public class Colonel extends MovingPiece {

    public Colonel(Player player, GameBoardField field) {
        this.player = player;
        this.name = "colonel";
        this.image = "/pieces/colonel.png";
        this.rank = 8;
        this.field = field;
    }

    public Colonel(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
