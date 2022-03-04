package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;
import javafx.scene.layout.StackPane;

import java.util.Objects;
import java.util.Stack;

public class Speelveld extends Position {
    private Piece piece;
    private StackPane type;
    private boolean walkable;
    private String typeName;

    public Speelveld(String typeName, int posX, int posY, StackPane type, boolean walkable, Piece piece) {
        this.typeName = typeName;
        this.positionX = posX;
        this.positionY = posY;

        this.type = type;
        this.walkable = walkable;
        this.piece = piece;
    }

    public Speelveld(String typeName, int posX, int posY, StackPane type, boolean walkable) {
        this(typeName, posX, posY, type, walkable, null);
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isWalkable() {
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        this.walkable = walkable;
    }

    public StackPane getType() {
        return type;
    }

    public void setType(StackPane type) {
        this.type = type;
    }

    public boolean isOccupied() {
        return !(Objects.isNull(this.piece));
    }

    public String getTypeName() {
        return typeName;
    }
}
