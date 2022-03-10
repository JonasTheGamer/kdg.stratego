package be.kdg.stratego.model;

public class Spy extends MovingPiece {

    public Spy(Player player, GameBoardField field) {
        this.player = player;
        this.name = "spy";
        this.image = "/pieces/spy.png";
        this.rank = 1;
        this.field = field;
    }

    public Spy(Player player) {
        this(player, null);
    }
    
    public void detonate() {
        this.field = null;
    }
}
