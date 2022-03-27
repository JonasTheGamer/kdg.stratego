package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MainMenuView extends VBox {
    private ImageView imgTitle;
    private Button btnPlay;
    private Button btnHelp;
    private Button btnQuit;
    private Label lblHighscores;
    private VBox vBoxTitles;
    private GridPane gpWinners;
    private VBox vBoxLeaderboards;
    private HBox hBoxTitlesAndLeaderboard;

    public MainMenuView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Controls
        this.imgTitle = new ImageView("/title.png");

        this.btnPlay = new Button("Play");
        this.btnHelp = new Button("Help");
        this.btnQuit = new Button("Quit");

        this.lblHighscores = new Label("Highscores/Lowturns");

        // Panes
        this.gpWinners = new GridPane();
        this.vBoxTitles = new VBox();
        this.vBoxLeaderboards = new VBox();
        this.hBoxTitlesAndLeaderboard = new HBox();
    }

    private void layoutNodes() {
        imgTitle.setScaleX(Style.scale(1.5));
        imgTitle.setScaleY(Style.scale(1.5));

        Style.btn(btnPlay, 15, 400, 50);
        Style.btn(btnHelp, 15, 400, 50);
        Style.btn(btnQuit, 15, 400, 50);

        Style.txt(lblHighscores, 12);
        lblHighscores.setStyle("-fx-font-weight: bold");

        gpWinners.setHgap(20);

        hBoxTitlesAndLeaderboard.getChildren().addAll(vBoxTitles, vBoxLeaderboards);
        hBoxTitlesAndLeaderboard.setAlignment(Pos.CENTER);
        hBoxTitlesAndLeaderboard.setSpacing(Style.size(40));

        vBoxTitles.getChildren().addAll(btnPlay, btnHelp, btnQuit);
        vBoxTitles.setBackground(Style.background);
        vBoxTitles.setAlignment(Pos.CENTER);
        vBoxTitles.setSpacing(Style.size(20));

        vBoxLeaderboards.getChildren().addAll(lblHighscores, gpWinners);
        vBoxLeaderboards.setBackground(Style.background);
        vBoxLeaderboards.setAlignment(Pos.CENTER);
        vBoxLeaderboards.setSpacing(Style.size(10));

        this.getChildren().addAll(imgTitle, hBoxTitlesAndLeaderboard);
        this.setBackground(Style.bgApplication);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Style.size(80));
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

    public GridPane getGpWinners() {
        return gpWinners;
    }

    public Label getLblHighscores() {
        return lblHighscores;
    }
}