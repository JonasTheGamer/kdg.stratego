package be.kdg.stratego.model;

import java.util.Objects;

public class Piece {
    protected Player player;
    protected String name;
    protected int rank;
    protected Speelveld field;
    protected String image;

    // Default constructor
    public Piece() {}

    public Piece(Player player, String name, String image, int rank, Speelveld field)  {
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

    public Speelveld getField() {
        return field;
    }

    public void setField(Speelveld field) {
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
}
