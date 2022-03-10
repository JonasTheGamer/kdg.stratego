package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;

import java.util.Objects;

public class Piece {
    protected Player player;
    protected String name;
    protected int rank;
    protected GameBoardField field;
    protected String image;

    // Default constructor
    public Piece() {}

    public Piece(Player player, String name, String image, int rank, GameBoardField field)  {
        this.player = player;
        this.name = name;
        this.rank = rank;
        this.field = field;
        this.image = image;
    }

    public Piece(Player player, String name, String defaultImage, int rank) {
        this(player, name, defaultImage, rank, null);
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameBoardField getField() {
        return field;
    }

    public void setField(GameBoardField field) {
        this.field = field;
    }

    public boolean isDead() {
        return Objects.isNull(field);
    }

    public boolean isOnField() {
        return !Objects.isNull(field);
    }

    public String getImage() {
        return image;
    }

    public void removeFromField() {
        this.field.setPiece(null);
        this.field = null;
    }

    public void placeOnField(GameBoardField field) {
        this.field = field;
        this.field.setPiece(this);
    }

    public void hide() {
        if(!Objects.isNull(field)) {
            field.setType(FieldType.unknownField(field.getPositionX(), field.getPositionY()));
        }
    }

    public void show() {
        if(!Objects.isNull(field)) {
            field.setType(FieldType.occupiedField(field.getPositionX(), field.getPositionY(), this.name));
        }
    }

}
