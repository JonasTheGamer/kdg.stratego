/*
    Class: Piece
    Responsibility: Handles all information and methods regarding one piece on the board, such as the field
                    it's on, placing and removing it on and from a field, bieng killed,...
 */
package be.kdg.stratego.model;

import java.util.Objects;

public class Piece implements Comparable {
    // References
    protected Player player;
    protected GameBoardField field;

    // Attributes
    protected String name;
    protected String image;
    protected boolean hidden;
    protected Piece attacker;
    protected int rank;

    public Piece(Player player, String name, String image, int rank)  {
        this.player = player;
        this.name = name;
        this.image = image;
        this.hidden = false;
        this.attacker = null;
        this.rank = rank;
    }

    //Methods
    public void placeOnField(GameBoardField field) {
        this.field = field;
        this.field.setPiece(this);
    }

    public void removeFromField() {
        this.field.setPiece(null);
        this.field = null;
    }

    public void startKill(Piece attacker) {
        if(attacker instanceof MovingPiece) {
            this.attacker = attacker;
        } else {
            this.attacker = null;
        }
    }
    public void startKill() {
        this.startKill(null);
    }

    public void finishKill() {
        // When the kill is being finished, the attacker should be moved towards the current pieces field
        // Store the field this piece is on for later use
        GameBoardField oldField = this.field;
        this.removeFromField();

        // If there is an attacker to be moved to this spot, move the attacker
        MovingPiece pieceToMove = (MovingPiece) attacker;
        if(!Objects.isNull(pieceToMove)){
            pieceToMove.moveTo(oldField);
        }
    }

    // Getters
    public String getName() {
        return name;
    }
    public Player getPlayer() {
        return player;
    }
    public boolean isOnField() {
        return !Objects.isNull(field);
    }
    public String getImage() {
        return image;
    }
    public boolean getHidden() {
        return hidden;
    }
    public int getRank() {
        return rank;
    }

    public GameBoardField getField() {
        return field;
    }

    // Setters
    public void setHidden(boolean hidden) {
        if(!Objects.isNull(field)) {
            this.hidden = hidden;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Piece otherPiece = (Piece)obj;
        return (field == otherPiece.getField() && rank == otherPiece.getRank());
    }

    @Override
    public int hashCode() {
        if(Objects.isNull(this.field)) {
            return Objects.hash(Integer.toString(this.rank));
        } else {
            return Objects.hash((this.rank) + this.field.toString());
        }
    }

    @Override
    public int compareTo(Object o) {
        Piece otherPiece = (Piece) o;
        return Integer.compare(this.rank, otherPiece.getRank());
    }
}
