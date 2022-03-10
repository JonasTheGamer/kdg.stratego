package be.kdg.stratego.model;

public class Flag extends Piece {

    public Flag(Player player, String flagImagePath, GameBoardField field ) {
        if (flagImagePath.equals("default")) {
            flagImagePath = "/pieces/flag.png";
        }

        this.player = player;
        this.name = "flag";
        this.image = flagImagePath;
        this.rank = 0;
        this.field = field;
    }

    public Flag(Player player, String flagImagePath) {
        this(player, flagImagePath, null);
    }

}
