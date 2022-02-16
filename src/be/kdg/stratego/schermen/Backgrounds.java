package be.kdg.stratego.schermen;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Backgrounds {
    public static Background background = new Background(new BackgroundImage(
            new Image("/background.jpg"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.SPACE,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
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

    public static Background boxBackground = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            new Insets(-10)
    ));
}
