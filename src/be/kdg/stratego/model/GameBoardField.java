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
    private boolean invalid;
    private GameBoard gameBoard;

    // Field (stackpane) properties
    private StackPane pane;
    private GroundType groundType;

    private final double paneHeight = Style.size(50);
    private final double paneWidth = Style.size(50);

    // Enumeration for ground type
    public enum GroundType {
        GRASS,
        WATER
    }

    public GameBoardField(GameBoard gameBoard, int posX, int posY, GroundType groundType) {
        // Set gameboard
        this.gameBoard = gameBoard;

        // Set position
        this.positionX = posX;
        this.positionY = posY;

        // Set highligted to false
        this.highlighted = false;

        // Set parameters for the pane
        this.groundType = groundType;

        // Figure out whether the field is walkable.
        this.walkable = (groundType != GroundType.WATER);

        // Generate pane
        regeneratePane();
    }

    // Methods
    public void findInformationAboutSurroundingFields() {
        // I need:
        // - The location of this piece within the board
        // - Reference to surrounding fields
        // - I can then ask the other fields whether they are occupied


    }

    public void regeneratePane() {
        // Generate the main stackpane
        StackPane container = new StackPane();

        container.setPrefHeight(paneHeight);
        container.setPrefWidth(paneWidth);

        // Set the right background
        if (groundType == GroundType.GRASS) {
            container.setBackground((this.highlighted) ? Style.highlightedGrass : Style.grass);
        } else {
            container.setBackground(Style.water);
        }

        // If there's a piece on it, place it
        if (this.isOccupied()) {
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

            ivTower.setEffect(new Blend(
                    BlendMode.MULTIPLY,
                    null,
                    new ColorInput(
                            0,
                            0,
                            ivTower.getImage().getWidth(),
                            ivTower.getImage().getHeight(),
                            piece.getPlayer().getColor()
                    )
            ));

            ivTower.setCache(true);
            ivTower.setCacheHint(CacheHint.SPEED);

            container.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if (!this.piece.getHidden()) {
                ImageView ivPiece = new ImageView(piece.getImage());
                ivPiece.setFitHeight(paneHeight * 0.4);
                ivPiece.setFitWidth(paneWidth * 0.4);

                container.getChildren().add(ivPiece);
            }
        }

        // If the field is invalid, show an X
        if (this.invalid) {
            ImageView ivInvalid = new ImageView("error.png");
            ivInvalid.setFitHeight(paneHeight);
            ivInvalid.setFitWidth(paneWidth);
            container.getChildren().add(ivInvalid);
        }

        container.setId(this.positionX + "-" + this.positionY);

        this.pane = container;
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

    // Getters
    public Piece getPiece() {
        return piece;
    }

    public StackPane getPane() {
        return pane;
    }

    public boolean isOccupied() {
        return !(Objects.isNull(this.piece));
    }

    public boolean isWalkable() {
        return this.walkable;
    }

    public boolean isInvalid() {
        return invalid;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public GameBoardField getFieldAbove() {
        return gameBoard.getGameBoardField(this.positionX, this.positionY - 1);
    }

    public GameBoardField getFieldBelow() {
        return gameBoard.getGameBoardField(this.positionX, this.positionY + 1);
    }

    public GameBoardField getFieldOnLeft() {
        return gameBoard.getGameBoardField(this.positionX - 1, this.positionY);
    }

    public GameBoardField getFieldOnRight() {
        return gameBoard.getGameBoardField(this.positionX + 1, this.positionY);
    }

    // Setters
    public void setPiece(Piece piece) {
        this.piece = piece;
        regeneratePane();
    }

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

    public void setInvalid(boolean invalid) {
        this.invalid = invalid;
        this.regeneratePane();
    }
}
