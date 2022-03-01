package be.kdg.stratego.model;

public abstract class MovingPiece extends Piece {
    protected int rank;

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void changePosition(Speelveld newSpeelveld) {
        //Variables to shorten code
        int x = this.speelveld.positionX;
        int y = this.speelveld.positionY;
        int newX = newSpeelveld.getPositionX();
        int newY = newSpeelveld.getPositionY();

        // Check if newSpeelveld isBewandelbaar
        if (newSpeelveld.isBewandelbaar()) {
            //Check if newSpeelveld is next to current speelveld
            if ((newX == x + 1 | newX == x - 1 & newY == y) | (newY == y + 1 | newY == y - 1 & newX == x)) {
                //Change Speelstuk of current speelveld because this speelstuk is changing position
                this.speelveld.setSpeelstuk(null);
                //Change current speelveld to newSpeelveld
                this.speelveld = newSpeelveld;
                if (newSpeelveld.getSpeelstuk() != null) {
                    if (newSpeelveld.getSpeelstuk().player != this.player) {
                        attack(newSpeelveld.getSpeelstuk());
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
            this.dead = true;
            System.out.println("Your " + this.name + " was killed by a " + speelstuk.getName());
        } else if (true/*Liam: Speelstuk instanceof ....*/) {
            MovingPiece movingPiece = (MovingPiece) speelstuk;
            if (this.rank > movingPiece.getRank()) {
                speelstuk.setDead(true);
                System.out.println("Your " + this.name + " has killed a " + speelstuk.getName());
            } else {
                this.dead = true;
                System.out.println("Your " + this.name + " was killed by a " + speelstuk.getName());
            }
        }
    }
}
