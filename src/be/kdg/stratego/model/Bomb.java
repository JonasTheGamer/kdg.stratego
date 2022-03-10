package be.kdg.stratego.model;

public class Bomb extends Piece {

    public Bomb(Player player, GameBoardField field ) {
        this.player = player;
        this.name = "bomb";
        this.image = "/pieces/bomb.png";
        this.rank = 0;
        this.field = field;
    }

    public Bomb(Player player) {
        this(player, null);
    }

    public void detonate() {
        this.field = null;
    }
}
