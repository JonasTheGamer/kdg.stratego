package be.kdg.stratego.view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class FieldType {
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
        ivTower.setFitHeight(height);
        ivTower.setFitWidth(width);

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

    public static Background background = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            new Insets(-10)
    ));

    public static Background backgroundBtn = new Background(new BackgroundFill(
            new Color(0, 0, 0.2, 0.8),
            new CornerRadii(20),
            null
    ));

    public static Background red = new Background(new BackgroundFill(
            new Color(1, 0, 0.2, 0.9),
            new CornerRadii(20),
            null
    ));

    public static Background green = new Background(new BackgroundFill(
            new Color(0, 1, 0.2, 0.9),
            new CornerRadii(20),
            null
    ));

    //Buttons
    public static void btn(Button btn, double fontSize) {
        FieldType.btn(btn,fontSize,400,50);
    }

    public static void btn(Button btn, double fontSize, double width, double height) {
        btn.setPrefWidth(width);
        btn.setPrefHeight(height);
        btn.setBackground(FieldType.backgroundBtn);
        FieldType.txt(btn,fontSize);
    }

    //Labels
    public static void txt(Labeled txt, double fontSize) {
        txt.setFont(Font.font("Verdana", fontSize));
        txt.setTextFill(Color.WHITE);
    }
}
