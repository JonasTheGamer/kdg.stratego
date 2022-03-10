package be.kdg.stratego.model;

import java.util.Objects;

public class Piece {
    protected Player player;
    protected String name;
    protected int rank;
    protected GameBoardField field;
    protected String image;
    protected boolean hidden;

    // Constructors

    //// Default constructor
    public Piece() {}

    //// Constructor with field
    public Piece(Player player, String name, String image, int rank, GameBoardField field)  {
        this.player = player;
        this.name = name;
        this.rank = rank;
        this.field = field;
        this.image = image;
        this.hidden = false;
    }

    //// Constructor without field
    public Piece(Player player, String name, String defaultImage, int rank) {
        this(player, name, defaultImage, rank, null);
    }

    // Getters & setters
    //// Get the piece name
    public String getName() {
        return name;
    }

    //// Get piece rank
    public int getRank() {
        return rank;
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
        this.field = null;
    }

    //// Get player who the piece belongs to
    public Player getPlayer() {
        return player;
    }

    //// Get whether the piece is dead (not on the board)
    public boolean isDead() {
        return Objects.isNull(field);
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
        }
    }

    ///// Get whether the piece is hidden or not
    public boolean getHidden() {
        return hidden;
    }
}
