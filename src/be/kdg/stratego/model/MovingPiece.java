package be.kdg.stratego.model;

public abstract class MovingPiece extends Piece {
    protected int rank;

    public MovingPiece() {
    }

    public MovingPiece(Player player, String name, String image, int rank, GameBoardField field) {
        super(player, name, image, field);
        this.rank = rank;
    }

    public MovingPiece(Player player, String name, String image, int rank) {
        super(player, name, image);
        this.rank = rank;
    }

    // Methods
    public void moveTo(GameBoardField destination) {
        this.removeFromField();
        this.placeOnField(destination);
    }

    protected void attack(Piece piece) {
        if (piece.name.equals("flag")) {
            //Liam: Game.stop()
        } else if (piece.name.equals("bomb")) {
            this.field = null;
            System.out.println("Your " + this.name + " was killed by a " + piece.getName());
        } else if (true/*Liam: Speelstuk instanceof ....*/) {
            MovingPiece movingPiece = (MovingPiece) piece;
            if (this.rank > movingPiece.getRank()) {
                piece.removeFromField();
                System.out.println("Your " + this.name + " has killed a " + piece.getName());
            } else {
                this.field = null;
                System.out.println("Your " + this.name + " was killed by a " + piece.getName());
            }
        }
    }

    // Getters
    public int getRank() {
        return rank;
    }
}
