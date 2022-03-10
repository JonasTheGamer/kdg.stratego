package be.kdg.stratego.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FieldType {
    private Background background;
    private boolean isHidden;
    private boolean isOccupied;
    private String occupiedBy;

    public static StackPane unknownField (double height, double width) {
        StackPane container = new StackPane();
        container.setBackground(Style.grass);

        ImageView iv = new ImageView("/hiddenTower.png");
        iv.setFitHeight(height);
        iv.setFitWidth(width);

        container.getChildren().add(iv);

        return container;
    };

    public static StackPane occupiedField (double height, double width, String occupiedBy) {
        StackPane container = new StackPane();
        container.setBackground(Style.grass);

        // Tower
        ImageView ivTower = new ImageView("/emptyTower.png");
        ivTower.setFitHeight(height * 0.95);
        ivTower.setFitWidth(width * 0.95);

        // Piece
        ImageView ivPiece = new ImageView("/pieces/"+occupiedBy+".png");
        ivPiece.setFitHeight(height*0.4);
        ivPiece.setFitWidth(width*0.4);

        container.getChildren().addAll(ivTower, ivPiece);

        return container;
    };

    public static StackPane swamp (double height, double width) {
        StackPane container = new StackPane();
        container.setBackground(Style.swamp);

        container.setPrefHeight(height);
        container.setPrefWidth(width);
        return container;
    };

    public static StackPane grass (double height, double width) {
        StackPane container = new StackPane();
        container.setBackground(Style.grass);

        container.setPrefHeight(height);
        container.setPrefWidth(width);

        return container;
    };
}
