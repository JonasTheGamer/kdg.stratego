package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.beans.binding.Bindings;
import javafx.scene.CacheHint;
import javafx.scene.control.Label;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.Objects;

public class GameBoardField extends Position {
    // GameBoardField properties
    private Piece piece;
    private boolean walkable;
    private boolean highlighted;

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

        // Set highligted to false
        this.highlighted = false;

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

        // Set the right background
        if(groundType == GroundType.GRASS) {
            container.setBackground((this.highlighted) ? Style.highlightedGrass : Style.grass);
        } else {
            container.setBackground(Style.water);
        }

        // If there's a piece on it, place it
        if(this.isOccupied()) {
            // Define the tower image
            String towerImage = (this.piece.getHidden() ? "/towerBackView.png" : "/towerFrontView.png");

            // Define the main imageView
            ImageView ivTower = new ImageView(towerImage);
            ivTower.setFitHeight(paneHeight * 0.95);
            ivTower.setFitWidth(paneWidth * 0.95);

            // Define the clip imageView
            ImageView ivClip = new ImageView(towerImage);
            ivClip.setFitHeight(paneHeight * 0.95);
            ivClip.setFitWidth(paneWidth * 0.95);

            // Set the image view clip
            ivTower.setClip(ivClip);

            // Color the tower image
            ColorAdjust monochrome = new ColorAdjust();
            monochrome.setSaturation(-1.0);

            Blend blush = new Blend(
                    BlendMode.MULTIPLY,
                    monochrome,
                    new ColorInput(
                            0,
                            0,
                            ivTower.getImage().getWidth(),
                            ivTower.getImage().getHeight(),
                            piece.getPlayer().getColor()
                    )
            );

            ivTower.setEffect(blush);

            ivTower.setCache(true);
            ivTower.setCacheHint(CacheHint.SPEED);

            container.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if(!this.piece.getHidden()) {
                ImageView ivPiece = new ImageView("/pieces/" + this.piece.getName() + ".png");
                ivPiece.setFitHeight(paneHeight * 0.4);
                ivPiece.setFitWidth(paneWidth * 0.4);

                container.getChildren().add(ivPiece);
            }
        }

        // For debugging purposes: include the position
        /*Label lblPosition = new Label("X: " + positionX + "\nY: " + positionY);
        lblPosition.setTextFill(Color.RED);
        container.getChildren().add(lblPosition);*/

        container.setId(this.positionX + "-" + this.positionY);

        this.pane = container;
    }

    // Check if a piece is placed on this field
    public boolean isOccupied() {
        return !(Objects.isNull(this.piece));
    }

    // Highlight field
    public void highLight() {
        this.highlighted = true;
        regeneratePane();
    }

    // Unhighlight field
    public void unHighLight() {
        this.highlighted = false;
        regeneratePane();
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

    // Overrides

    @Override
    public void setPositionX(int positionX) {
        super.setPositionX(positionX);
        regeneratePane();
    }

    @Override
    public void setPositionY(int positionY) {
        super.setPositionY(positionY);
        regeneratePane();
    }
}
