package be.kdg.stratego;

import be.kdg.stratego.Klassen.*;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);

        // Code that will be executed after launching the application window
        Game myGame = new Game();

        myGame.opslaan();
    }

    @Override
    public void start(Stage stage) {
        double widthBtn = 400;
        double heightBtn = 50;
        Color textColorWhite = new Color(1, 1, 1, 1);
        Background backgroundBtn = new Background(new BackgroundFill(
                new Color(0, 0, 0.2, 0.9),
                new CornerRadii(20),
                null
        ));
        Background backgroundPane = new Background(new BackgroundFill(
                new Color(0, 0, 0, 0.8),
                new CornerRadii(20),
                new Insets(-10)
        ));


        //Nodes
        ImageView imgTitle = new ImageView("/title.png");
        Button btnPlay = new Button("Play");
        Button btnHelp = new Button("Help");
        Button btnQuit = new Button("Quit");

        imgTitle.setFitHeight(300);
        imgTitle.setFitWidth(600);

        btnPlay.setPrefWidth(widthBtn);
        btnHelp.setPrefWidth(widthBtn);
        btnQuit.setPrefWidth(widthBtn);

        btnPlay.setPrefHeight(heightBtn);
        btnHelp.setPrefHeight(heightBtn);
        btnQuit.setPrefHeight(heightBtn);

        btnPlay.setBackground(backgroundBtn);
        btnHelp.setBackground(backgroundBtn);
        btnQuit.setBackground(backgroundBtn);

        btnPlay.setTextFill(textColorWhite);
        btnHelp.setTextFill(textColorWhite);
        btnQuit.setTextFill(textColorWhite);

        Label lblHighscores = new Label("Highscores");
        Label lblScores = new Label("score\nscore\nscore\nscore\nscore\nscore\nscore\nscore\nscore\nscore");

        lblHighscores.setTextFill(textColorWhite);
        lblScores.setTextFill(textColorWhite);

        lblHighscores.setStyle("-fx-font-size: 14; -fx-font-weight: bold");

        //Panes
        VBox vBoxTitles = new VBox(imgTitle, btnPlay, btnHelp, btnQuit);
        vBoxTitles.setSpacing(20);
        vBoxTitles.setAlignment(Pos.CENTER);
        vBoxTitles.setBackground(backgroundPane);

        VBox vBoxLeaderboards = new VBox(lblHighscores, lblScores);
        vBoxLeaderboards.setBackground(backgroundPane);
        vBoxLeaderboards.setSpacing(5);
        vBoxLeaderboards.setPrefWidth(100);
        vBoxLeaderboards.setPrefHeight(50);

        HBox hBoxTitlesAndLeaderboard = new HBox();
        hBoxTitlesAndLeaderboard.getChildren().addAll(vBoxTitles, vBoxLeaderboards);
        hBoxTitlesAndLeaderboard.setSpacing(40);
        hBoxTitlesAndLeaderboard.setAlignment(Pos.CENTER);

        VBox vBoxBackground = new VBox();
        vBoxBackground.getChildren().addAll(imgTitle, hBoxTitlesAndLeaderboard);
        vBoxBackground.setSpacing(20);
        vBoxBackground.setAlignment(Pos.CENTER);
        // Achtergrond instellen
        vBoxBackground.setBackground(new Background(new BackgroundImage(
                new Image("/background.png"),
                BackgroundRepeat.ROUND,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.CENTER,
                BackgroundSize.DEFAULT
        )));

        //Scene
        Scene scene = new Scene(vBoxBackground);

        //Stage
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.show();

        //Code
        stage.addEventFilter(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {
            public void handle(KeyEvent ke) {
                if (ke.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            }
        });
    }
}
