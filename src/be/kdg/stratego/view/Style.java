package be.kdg.stratego.view;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.battlefield.BattleFieldPresenter;
import be.kdg.stratego.view.battlefield.BattleFieldView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Screen;

public class Style {
    private final static double BASE_SCALE = 1.25;
    public enum Screens {
        MAINMENU,
        NEWGAME,
        ARMYSETUP,
        BATTLEFIELD
    }

    // Backgrounds
    public static Background bgImage(String path, boolean contain) {
        return new Background(new BackgroundImage(
                new Image(path),
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(1.0, 1.0, true, true, true, contain)
        ));
    }

    public static Background bgApplication = bgImage("background.jpg", true);

    // Field backgrounds
    public static Background grass = bgImage("grassTexture.png", true);
    public static Background highlightedGrass = bgImage("highlightedGrassTexture.png", true);
    public static Background water = bgImage("waterTexture.png", true);

    // Semi-transparent black background
    public static Background background = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            new Insets(-10)
    ));

    // Same as above, without padding
    public static Background bgBoxNoPadding = new Background(new BackgroundFill(
            new Color(0, 0, 0, 0.8),
            new CornerRadii(20),
            null
    ));

    // Dark blueish button background
    public static Background backgroundBtn = new Background(new BackgroundFill(
            new Color(0, 0, 0.2, 0.8),
            new CornerRadii(20),
            null
    ));

    // Borders
    public static Border border(Paint paint, double cornerRadius) {
        return new Border(new BorderStroke(
                paint,
                new BorderStrokeStyle(null, null, null, 1, 0, null),
                new CornerRadii(cornerRadius),
                new BorderWidths(1)
        ));
    }

    public static Border border(Paint paint) {
        return border(paint,5);
    }

    // Buttons
    public static void btn(Button btn, double fontSize, double width, double height) {
        btn.setPrefWidth(Style.size(width));
        btn.setPrefHeight(Style.size(height));
        btn.setBackground(Style.backgroundBtn);
        Style.txt(btn, fontSize);
    }

    // Labels
    public static void txt(Labeled txt, double fontSize) {
        Style.txt(txt, fontSize, Color.WHITE);
    }

    public static void txt(Labeled txt, double fontSize, Paint paint) {
        txt.setFont(Font.font("Verdana", size(fontSize)));
        txt.setTextFill(paint);
    }

    // Responsive handler
    // Size = pixels
    public static double size(double pixels) {
        Screen screen = Screen.getPrimary();
        double screenScale = screen.getOutputScaleX();

        return (pixels / screenScale * BASE_SCALE);
    }

    // Scale = "percentage" (1 = 100%)
    public static double scale(double scale) {
        Screen screen = Screen.getPrimary();
        double screenScale = screen.getOutputScaleX();

        return (scale / screenScale * BASE_SCALE);
    }

    public static void changeScreen(Screens screen, ProgrammaModel model, Pane view) {
        switch (screen) {
            case MAINMENU:
                MainMenuView mainMenuView = new MainMenuView();
                MainMenuPresenter mainMenuPresenter = new MainMenuPresenter(model, mainMenuView);
                view.getScene().setRoot(mainMenuView);
                mainMenuPresenter.addWindowEventHandlers();
                break;
            case NEWGAME:
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
                view.getScene().setRoot(newGameView);
                newGamePresenter.addWindowEventHandlers();
                break;
            case ARMYSETUP:
                ArmySetupView armySetupView = new ArmySetupView();
                ArmySetupPresenter armySetupPresenter = new ArmySetupPresenter(model, armySetupView);
                view.getScene().setRoot(armySetupView);
                armySetupPresenter.addWindowEventHandlers();
                break;
            case BATTLEFIELD:
                BattleFieldView battleFieldView = new BattleFieldView();
                BattleFieldPresenter battleFieldPresenter = new BattleFieldPresenter(model, battleFieldView);
                view.getScene().setRoot(battleFieldView);
                battleFieldPresenter.addWindowEventHandlers();
        }
    }
}
