package be.kdg.stratego.view;

import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class Style {
    private static double base = 1.25;
    public static Background applicationBackground = new Background(new BackgroundImage(
            new Image("background.jpg"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.SPACE,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, false)
    ));

    public static Background grass = new Background(new BackgroundImage(
            new Image("grassTexture.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, true)
    ));

    public static Background highlightedGrass = new Background(new BackgroundImage(
            new Image("highlightedGrassTexture.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, true)
    ));

    public static Background water = new Background(new BackgroundImage(
            new Image("waterTexture.png"),
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(1.0, 1.0, true, true, false, true)
    ));

    public static Background background = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            new Insets(-10)
    ));

    public static Background bgBoxNoPadding = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            null
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
        Style.btn(btn, fontSize, 400, 50);
    }

    public static void btn(Button btn, double fontSize, double width, double height) {
        btn.setPrefWidth(Style.size(width));
        btn.setPrefHeight(Style.size(height));
        btn.setBackground(Style.backgroundBtn);
        Style.txt(btn, fontSize);
    }

    //Labels
    public static void txt(Labeled txt, double fontSize) {
        Style.txt(txt, fontSize, Color.WHITE);
    }

    public static void txt(Labeled txt, double fontSize, Paint paint) {
        txt.setFont(Font.font("Verdana", size(fontSize)));
        txt.setTextFill(paint);
    }

    // Responsive handler
    public static double size(double pixels) {
        Screen screen = Screen.getPrimary();
        double screenScale = screen.getOutputScaleX();

        return (pixels / screenScale * base);
    }

    public static double scale(double scale) {
        Screen screen = Screen.getPrimary();
        double screenScale = screen.getOutputScaleX();

        return (scale / screenScale * base);
    }
}
