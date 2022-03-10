package be.kdg.stratego.model;

public class Lieutenant extends MovingPiece {

    public Lieutenant(Player player, GameBoardField field) {
        this.player = player;
        this.name = "lieutenant";
        this.image = "/pieces/lieutenant.png";
        this.rank = 5;
        this.field = field;
    }

    public Lieutenant(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
