package be.kdg.stratego.view.mainmenu;

// Imports
import be.kdg.stratego.view.Style;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

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
        imgTitle.setScaleX(1.5);
        imgTitle.setScaleY(1.5);

        Style.btn(btnPlay, 15);
        Style.btn(btnHelp, 15);
        Style.btn(btnQuit, 15);

        Style.txt(lblScores, 10);

        Style.txt(lblHighscores, 12);
        lblHighscores.setStyle("-fx-font-weight: bold");

        hBoxTitlesAndLeaderboard.setAlignment(Pos.CENTER);
        hBoxTitlesAndLeaderboard.setSpacing(Style.width(40));

        vBoxTitles.setBackground(Style.background);
        vBoxTitles.setAlignment(Pos.CENTER);
        vBoxTitles.setSpacing(Style.height(20));

        vBoxLeaderboards.setBackground(Style.background);
        vBoxLeaderboards.setAlignment(Pos.CENTER);
        vBoxLeaderboards.setSpacing(Style.height(10));

        this.setBackground(Style.applicationBackground);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Style.height(60));

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