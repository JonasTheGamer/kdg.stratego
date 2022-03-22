package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.CacheHint;
import javafx.scene.effect.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.Objects;

public class GameBoardField extends Position {
    private final double fieldSize = Style.size(50);

    // GameBoardField properties
    private Piece piece;
    private boolean walkable;
    private boolean highlighted;
    private GameBoard gameBoard;

    // Field (stackpane) properties
    private StackPane pane;
    private GroundType groundType;

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

        container.setPrefSize(fieldSize, fieldSize);

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
            ivTower.setFitHeight(fieldSize * 0.95);
            ivTower.setFitWidth(fieldSize * 0.95);

            // Define the clip imageView
            ImageView ivClip = new ImageView(towerImage);
            ivClip.setFitHeight(fieldSize * 0.95);
            ivClip.setFitWidth(fieldSize * 0.95);

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
                            Color.valueOf(piece.getPlayer().getColor())

                    )
            ));

            ivTower.setCache(true);
            ivTower.setCacheHint(CacheHint.SPEED);

            container.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if (!this.piece.getHidden()) {
                ImageView ivPiece = new ImageView(piece.getImage());
                ivPiece.setFitHeight(fieldSize * 0.4);
                ivPiece.setFitWidth(fieldSize * 0.4);

                container.getChildren().add(ivPiece);
            }
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
    public double getFieldSize() {
        return fieldSize;
    }

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
}
