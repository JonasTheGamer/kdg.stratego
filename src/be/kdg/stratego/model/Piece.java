package be.kdg.stratego.model;

import java.util.Objects;

public class Piece {
    protected Player player;
    protected String name;
    protected GameBoardField field;
    protected String image;
    protected boolean hidden;

    // Constructors

    //// Default constructor
    public Piece() {}

    //// Constructor with field
    public Piece(Player player, String name, String image, GameBoardField field)  {
        this.player = player;
        this.name = name;
        this.field = field;
        this.image = image;
        this.hidden = false;
    }

    //// Constructor without field
    public Piece(Player player, String name, String defaultImage) {
        this(player, name, defaultImage, null);
    }

    // Getters & setters
    //// Get the piece name
    public String getName() {
        return name;
    }

    //// Field placing
    ////// Get the field the piece is on
    public GameBoardField getField() {
        return field;
    }

    ////// Place the piece on a field
    public void placeOnField(GameBoardField field) {
        this.field = field;
        this.field.setPiece(this);
    }

    ////// Remove the piece from the field
    public void removeFromField() {
        this.field.setPiece(null);
        this.field.regeneratePane();
        this.field = null;
    }

    //// Get player who the piece belongs to
    public Player getPlayer() {
        return player;
    }

    //// Get whether the piece is on a field (on the board)
    public boolean isOnField() {
        return !Objects.isNull(field);
    }

    //// Get the image that belongs to this piece
    public String getImage() {
        return image;
    }

    //// Hiding & showing
    ///// Set whether the piece is hidden or not
    public void setHidden(boolean hidden) {
        if(!Objects.isNull(field)) {
            this.hidden = hidden;
            if(this.isOnField()) {
                this.field.regeneratePane();
            }
        }
    }

    ///// Get whether the piece is hidden or not
    public boolean getHidden() {
        return hidden;
    }
}
