package be.kdg.stratego.model;

public abstract class MovingPiece extends Piece {

    public MovingPiece() {
    }

    public MovingPiece(Player player, String name, String image, int rank, Speelveld field) {
        super(player, name, image, rank, field);
    }

    public MovingPiece(Player player, String name, String image, int rank) {
        super(player, name, image, rank);
    }

    public void changePosition(Speelveld newSpeelveld) {
        //Variables to shorten code
        int x = this.field.positionX;
        int y = this.field.positionY;
        int newX = newSpeelveld.getPositionX();
        int newY = newSpeelveld.getPositionY();

        // Check if newSpeelveld isBewandelbaar
        if (newSpeelveld.isWalkable()) {
            //Check if newSpeelveld is next to current speelveld
            if ((newX == x + 1 | newX == x - 1) & newY == y | (newY == y + 1 | newY == y - 1) & newX == x) {
                //Change Speelstuk of current speelveld because this speelstuk is changing position
                this.field.setPiece(null);
                //Change current speelveld to newSpeelveld
                this.field = newSpeelveld;
                if (newSpeelveld.getPiece() != null) {
                    if (newSpeelveld.getPiece().player != this.player) {
                        attack(newSpeelveld.getPiece());
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
                speelstuk.setField(null);
                System.out.println("Your " + this.name + " has killed a " + speelstuk.getName());
            } else {
                this.field = null;
                System.out.println("Your " + this.name + " was killed by a " + speelstuk.getName());
            }
        }
    }
}
