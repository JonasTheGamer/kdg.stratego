package be.kdg.stratego.model;

public abstract class MovingPiece extends Piece {
    protected int rank;

    public MovingPiece() {
    }

    public MovingPiece(Player player, String name, String image, int rank, GameBoardField field) {
        super(player, name, image, rank, field);
    }

    public MovingPiece(Player player, String name, String image, int rank) {
        super(player, name, image, rank);
    }

    public void changePosition(GameBoardField newGameBoardField) {
        //Variables to shorten code
        int x = this.field.positionX;
        int y = this.field.positionY;
        int newX = newGameBoardField.getPositionX();
        int newY = newGameBoardField.getPositionY();

        // Check if newGameBoardField isWalkable
        if (newGameBoardField.getWalkable()) {
            //Check if newGameBoardField is next to current gameBoardField
            if ((newX == x + 1 | newX == x - 1) & newY == y | (newY == y + 1 | newY == y - 1) & newX == x) {
                //Change Piece of current GameBOardField because this Piece is changing position
                this.field.setPiece(null);
                //Change current GameBoardField to newGameBoardField
                this.field = newGameBoardField;
                if (newGameBoardField.getPiece() != null) {
                    if (newGameBoardField.getPiece().player != this.player) {
                        attack(newGameBoardField.getPiece());
                    } else {
                        System.out.println("You can't attack your own team");
                    }
                } else {
                    System.out.println("Your move was a succes");
                }
            } else {
                System.out.println("Field to far away");
            }
        } else {
            System.out.println("Field not walkable");
        }
    }

    protected void attack(Piece speelstuk) {
        if (speelstuk.name.equals("flag")) {
            //Liam: Game.stop()
        } else if (speelstuk.name.equals("bomb")) {
            this.field = null;
            System.out.println("Your " + this.name + " was killed by a " + speelstuk.getName());
        } else if (true/*Liam: Speelstuk instanceof ....*/) {
            MovingPiece movingPiece = (MovingPiece) speelstuk;
            if (this.rank > movingPiece.getRank()) {
                speelstuk.removeFromField();
                System.out.println("Your " + this.name + " has killed a " + speelstuk.getName());
            } else {
                this.field = null;
                System.out.println("Your " + this.name + " was killed by a " + speelstuk.getName());
            }
        }
    }

    public int getRank() {
        return rank;
    }
}
