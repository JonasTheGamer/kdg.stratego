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
    private Label lblPositie;
    private Label lblScore;
    private Label lblName;
    private GridPane gpHighscores;

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
        this.lblPositie = new Label("Rank");
        this.lblScore = new Label("Score");
        this.lblName = new Label("Name");
        this.gpHighscores = new GridPane();

        // Panes
        this.vBoxTitles = new VBox();
        this.vBoxLeaderboards = new VBox();
        this.hBoxTitlesAndLeaderboard = new HBox();
    }

    private void layoutNodes() {
        imgTitle.setScaleX(Style.scale(1.5));
        imgTitle.setScaleY(Style.scale(1.5));

        Style.btn(btnPlay, Style.fontSize(15));
        Style.btn(btnHelp, Style.fontSize(15));
        Style.btn(btnQuit, Style.fontSize(15));

        Style.txt(lblHighscores, Style.fontSize(12));
        lblHighscores.setStyle("-fx-font-weight: bold");

        Style.txt(lblPositie, Style.fontSize(10));
        Style.txt(lblScore, Style.fontSize(10));
        Style.txt(lblName, Style.fontSize(10));

        gpHighscores.add(lblPositie,0,0);
        gpHighscores.add(lblScore,1,0);
        gpHighscores.add(lblName,2,0);
        gpHighscores.setHgap(20);

        hBoxTitlesAndLeaderboard.setAlignment(Pos.CENTER);
        hBoxTitlesAndLeaderboard.setSpacing(Style.size(40));

        vBoxTitles.setBackground(Style.background);
        vBoxTitles.setAlignment(Pos.CENTER);
        vBoxTitles.setSpacing(Style.size(20));

        vBoxLeaderboards.setBackground(Style.background);
        vBoxLeaderboards.setAlignment(Pos.CENTER);
        vBoxLeaderboards.setSpacing(Style.size(10));

        this.setBackground(Style.applicationBackground);
        this.setAlignment(Pos.CENTER);
        this.setSpacing(Style.size(60));

        // Add to panes
        hBoxTitlesAndLeaderboard.getChildren().addAll(vBoxTitles, vBoxLeaderboards);
        vBoxTitles.getChildren().addAll(btnPlay, btnHelp, btnQuit);
        vBoxLeaderboards.getChildren().addAll(lblHighscores, gpHighscores);

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

    public GridPane getGpHighscores() {
        return gpHighscores;
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