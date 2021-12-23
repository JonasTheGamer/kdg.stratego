package be.kdg.Stratego.Klassen;

public abstract class BewegendSpeelstuk extends Speelstuk {
    protected int rang;

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public void veranderPositie(Speelveld newSpeelveld) {
        //Variables to shorten code
        int x = this.speelveld.positieX;
        int y = this.speelveld.positieY;
        int newX = newSpeelveld.getPositieX();
        int newY = newSpeelveld.getPositieY();

        //Check if newSpeelveld isBewandelbaar
        if (newSpeelveld.isBewandelbaar()) {
            //Check if newSpeelveld is next to current speelveld
            if ((newX == x + 1 | newX == x - 1 & newY == y) | (newY == y + 1 | newY == y - 1 & newX == x)) {
                //Change Speelstuk of current speelveld because this speelstuk is changing position
                this.speelveld.setSpeelstuk(null);
                //Change current speelveld to newSpeelveld
                this.speelveld = newSpeelveld;
                if (newSpeelveld.getSpeelstuk() != null) {
                    if (newSpeelveld.getSpeelstuk().speler != this.speler) {
                        aanvallen(newSpeelveld.getSpeelstuk());
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

    protected void aanvallen(Speelstuk speelstuk) {
        if (speelstuk.naam.equals("flag")) {
            //Liam: Game.stop()
        } else if (speelstuk.naam.equals("bomb")) {
            this.dood = true;
            System.out.println("Your " + this.naam + " was killed by a " + speelstuk.getNaam());
        } else if (true/*Liam: Speelstuk instanceof ....*/) {
            BewegendSpeelstuk bewegendSpeelstuk = (BewegendSpeelstuk) speelstuk;
            if (this.rang > bewegendSpeelstuk.getRang()) {
                speelstuk.setDood(true);
                System.out.println("Your " + this.naam + " has killed a " + speelstuk.getNaam());
            } else {
                this.dood = true;
                System.out.println("Your " + this.naam + " was killed by a " + speelstuk.getNaam());
            }
        }
    }
}
