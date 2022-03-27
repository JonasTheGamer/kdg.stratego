/*
    Class: Board
    Responsibility: Takes care of all common board methods
*/
package be.kdg.stratego.view;

import be.kdg.stratego.model.GameBoardField;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Board {
    public static Pane generatePane(GameBoardField field, double FIELD_SIZE) {
        // Generate the containers
        Pane container = new Pane();
        StackPane pieceContainer = new StackPane();

        // Container
        container.setPrefSize(FIELD_SIZE, FIELD_SIZE);
        if (field.isWalkable()) {
            container.setBackground((field.isHighlighted()) ? Style.highlightedGrass : Style.grass);
        } else {
            container.setBackground(Style.water);
        }

        // If the field has a piece, add the piece
        if (field.isOccupied()) {
            // Define the tower image
            String towerImage = (field.getPiece().getHidden() ? "/towerBackView.png" : "/towerFrontView.png");

            // Define the main imageView
            ImageView ivTower = new ImageView(towerImage);
            ivTower.setFitHeight(FIELD_SIZE * 0.95);
            ivTower.setFitWidth(FIELD_SIZE * 0.95);

            // Define the clip imageView
            ImageView ivClip = new ImageView(towerImage);
            ivClip.setFitHeight(FIELD_SIZE * 0.95);
            ivClip.setFitWidth(FIELD_SIZE * 0.95);

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
                            Color.valueOf(field.getPiece().getPlayer().getColor())

                    )
            ));
            pieceContainer.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if (!field.getPiece().getHidden()) {
                ImageView ivPiece = new ImageView(field.getPiece().getImage());
                ivPiece.setFitHeight(FIELD_SIZE * 0.4);
                ivPiece.setFitWidth(FIELD_SIZE * 0.4);
                pieceContainer.getChildren().add(ivPiece);
            }

            //Add the pieceContainer to the field pane
            container.getChildren().add(pieceContainer);
        }
        return container;
    }
}
