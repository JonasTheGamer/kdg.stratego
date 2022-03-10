package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class GameBoardField extends Position {
    // GameBoardField properties
    private Piece piece;
    private boolean walkable;

    // Field (stackpane) properties
    private StackPane pane;
    private GroundType groundType;

    private double paneHeight;
    private double paneWidth;

    // Enumeration for ground type
    public static enum GroundType {
        GRASS,
        WATER
    }

    // Constructor with piece to be placed on the field
    public GameBoardField(int posX, int posY, double paneHeight, double paneWidth, GroundType groundType, Piece piece) {
        // Set position
        this.positionX = posX;
        this.positionY = posY;

        // Set piece
        this.piece = piece;

        // Set parameters for the pane
        this.paneHeight = paneHeight;
        this.paneWidth = paneWidth;
        this.groundType = groundType;

        // Figure out whether the field is walkable.
        this.walkable = (groundType != GroundType.WATER);

        // Generate pane
        regeneratePane();
    }

    // Constructor without the piece to be placed on the field
    public GameBoardField(int posX, int posY, double paneHeight , double paneWidth, GroundType groundType) {
        this(posX, posY,paneHeight, paneWidth, groundType, null);
    }

    public void regeneratePane() {
        // Generate the main stackpane
        StackPane container = new StackPane();
        container.setBackground(Style.water);

        container.setPrefHeight(paneHeight);
        container.setPrefWidth(paneWidth);

        container.setId(this.positionX + "-" + this.positionY);

        // Set the right background
        container.setBackground((groundType == GroundType.GRASS) ? Style.grass : Style.water);

        // If there's a piece on it, place it
        if(this.isOccupied()) {
            // Define the tower image
            ImageView ivTower = new ImageView((this.piece.getHidden() ? "/hiddenTower.png" : "/emptyTower.png"));
            ivTower.setFitHeight(paneHeight * 0.95);
            ivTower.setFitWidth(paneWidth * 0.95);

            container.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if(!this.piece.getHidden()) {
                ImageView ivPiece = new ImageView("/pieces/" + this.piece.getName() + ".png");
                ivPiece.setFitHeight(paneHeight * 0.4);
                ivPiece.setFitWidth(paneWidth * 0.4);

                container.getChildren().add(ivPiece);
            }
        }

        this.pane = container;
    }

    // Check if a piece is placed on this field
    public boolean isOccupied() {
        return !(Objects.isNull(this.piece));
    }

    // Getters & setters
    //// Find out which piece is placed on this field
    public Piece getPiece() {
        return piece;
    }

    //// Place a piece on this field
    public void setPiece(Piece piece) {
        this.piece = piece;
        regeneratePane();
    }

    //// Check if this is walkable
    public boolean getWalkable() {
        return walkable;
    }

    //// Get the stackpane type
    public StackPane getPane() {
        return pane;
    }

}
