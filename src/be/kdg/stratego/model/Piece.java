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

    //Methods
    public void placeOnField(GameBoardField field) {
        this.field = field;
        this.field.setPiece(this);
    }

    public void removeFromField() {
        this.field.setPiece(null);
        this.field.regeneratePane();
        this.field = null;
    }

    // Getters
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
