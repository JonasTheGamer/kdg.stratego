package be.kdg.stratego.view.mainmenu;

// Imports
import be.kdg.stratego.view.Backgrounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class MainMenuView extends VBox {
    // Controls
    private ImageView imgTitle;
    private Button btnPlay;
    private Button btnHelp;
    private Button btnQuit;
    private Label lblHighscores;
    private Label lblScores;

    // Panes
    private VBox vBoxTitles;
    private VBox vBoxLeaderboards;
    private HBox hBoxTitlesAndLeaderboard;

    public MainMenuView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        this.imgTitle = new ImageView("/title.png");
        this.btnPlay = new Button("Play");
        this.btnHelp = new Button("Help");
        this.btnQuit = new Button("Quit");
        this.lblHighscores = new Label("Highscores");
        this.lblScores = new Label("score\nscore\nscore\nscore\nscore\nscore\nscore\nscore\nscore\nscore");

        // Panes
        this.vBoxTitles = new VBox();
        this.vBoxLeaderboards = new VBox();
        this.hBoxTitlesAndLeaderboard = new HBox();
    }

    private void layoutNodes() {
        // Styling variables
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

        // Styling
        imgTitle.setScaleX(1.5);
        imgTitle.setScaleY(1.5);

        btnPlay.setPrefWidth(widthBtn);
        btnPlay.setPrefHeight(heightBtn);
        btnPlay.setBackground(backgroundBtn);
        btnPlay.setTextFill(textColorWhite);

        btnHelp.setPrefWidth(widthBtn);
        btnHelp.setPrefHeight(heightBtn);
        btnHelp.setBackground(backgroundBtn);
        btnHelp.setTextFill(textColorWhite);

        btnQuit.setPrefWidth(widthBtn);
        btnQuit.setPrefHeight(heightBtn);
        btnQuit.setBackground(backgroundBtn);
        btnQuit.setTextFill(textColorWhite);

        lblScores.setTextFill(textColorWhite);

        lblHighscores.setTextFill(textColorWhite);
        lblHighscores.setStyle("-fx-font-size: 14; -fx-font-weight: bold");

        hBoxTitlesAndLeaderboard.setAlignment(Pos.CENTER);
        hBoxTitlesAndLeaderboard.setSpacing(40);

        vBoxTitles.setBackground(backgroundPane);
        vBoxTitles.setAlignment(Pos.CENTER);
        vBoxTitles.setSpacing(20);

        vBoxLeaderboards.setBackground(backgroundPane);
        vBoxLeaderboards.setAlignment(Pos.CENTER);
        vBoxLeaderboards.setSpacing(10);

        this.setBackground(Backgrounds.background);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(60);

        // Add to panes
        hBoxTitlesAndLeaderboard.getChildren().addAll(vBoxTitles, vBoxLeaderboards);
        vBoxTitles.getChildren().addAll(btnPlay, btnHelp, btnQuit);
        vBoxLeaderboards.getChildren().addAll(lblHighscores, lblScores);

        this.getChildren().addAll(imgTitle, hBoxTitlesAndLeaderboard);
    }

    public ImageView getImgTitle() {
        return imgTitle;
    }

    public Button getBtnPlay() {
        return btnPlay;
    }

    public Button getBtnHelp() {
        return btnHelp;
    }

    public Button getBtnQuit() {
        return btnQuit;
    }

    public Label getLblHighscores() {
        return lblHighscores;
    }

    public Label getLblScores() {
        return lblScores;
    }

    public VBox getvBoxTitles() {
        return vBoxTitles;
    }

    public VBox getvBoxLeaderboards() {
        return vBoxLeaderboards;
    }

    public HBox gethBoxTitlesAndLeaderboard() {
        return hBoxTitlesAndLeaderboard;
    }
}