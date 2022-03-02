package be.kdg.stratego;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.application.Application;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        ProgrammaModel model = new ProgrammaModel();




        //MainMenuView view = new MainMenuView();
        //MainMenuPresenter presenter = new MainMenuPresenter(model, view);

        //NewGameView view = new NewGameView();
        //NewGamePresenter presenter = new NewGamePresenter(model, view);

        ArmySetupView view = new ArmySetupView();
        ArmySetupPresenter presenter = new ArmySetupPresenter(model, view);



        // Set window to show this view
        primaryStage.setScene(new Scene(view));

        // Add event handlers
        presenter.addWindowEventHandlers();

        // Show window
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);

        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

/*
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

        imgTitle.setScaleX(1.5);
        imgTitle.setScaleY(1.5);

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
        VBox vBoxTitles = new VBox(btnPlay, btnHelp, btnQuit);
        vBoxTitles.setSpacing(20);
        vBoxTitles.setBackground(backgroundPane);

        VBox vBoxLeaderboards = new VBox(lblHighscores, lblScores);
        vBoxLeaderboards.setBackground(backgroundPane);
        vBoxLeaderboards.setSpacing(10);

        HBox hBoxTitlesAndLeaderboard = new HBox(vBoxTitles, vBoxLeaderboards);
        hBoxTitlesAndLeaderboard.setAlignment(Pos.TOP_CENTER);
        hBoxTitlesAndLeaderboard.setSpacing(40);

        VBox vBoxBackground = new VBox(imgTitle, hBoxTitlesAndLeaderboard);
        vBoxBackground.setAlignment(Pos.CENTER);
        vBoxBackground.setSpacing(60);
        // Achtergrond instellen
        vBoxBackground.setBackground(Backgrounds.background);

        //Scene
        Scene scene = new Scene(vBoxBackground);

        //Stage
        stage.setScene(scene);
        stage.setFullScreen(false);
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
*/
