package be.kdg.stratego.model;

import java.util.Objects;

public class Piece {
    // References
    protected Player player;
    protected GameBoardField field;

    // Attributes
    protected String name;
    protected String image;
    protected boolean hidden;
    protected boolean dying;
    protected Piece attacker;

    public Piece(Player player, String name, String image)  {
        this.player = player;
        this.name = name;
        this.image = image;
        this.hidden = false;
        this.dying = false;
        this.attacker = null;
    }

    //Methods
    public void placeOnField(GameBoardField field) {
        this.field = field;
        this.field.setPiece(this);
        // Update global 2D array
        this.field.getGameBoard().setGameBoardField(field);
    }

    public void removeFromField() {
        this.field.setPiece(null);
        // Remove from global 2D array
        this.field.getGameBoard().setGameBoardField(this.field);
        this.field = null;
    }

    public void startKill(Piece attacker) {
        this.dying = true;

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
        this.dying = false;

        // Store the field this piece is on for later use
        GameBoardField oldField = this.field;
        this.removeFromField();

        // If there is an attacker to be moved to this spot, move the attacker
        MovingPiece pieceToMove = (MovingPiece) attacker;
        pieceToMove.moveTo(oldField);
    }

    // Getters

    public boolean isDying() {
        return dying;
    }
    public String getName() {
        return name;
    }
    public GameBoardField getField() {
        return field;
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

    // Setters
    public void setHidden(boolean hidden) {
        if(!Objects.isNull(field)) {
            this.hidden = hidden;
            if(this.isOnField()) {
                this.field.regeneratePane();
            }
        }
    }

    public void setImage(String image) {
        this.image = image;
    }
}
