package be.kdg.stratego.view.help;

import be.kdg.stratego.view.Style;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class HelpView extends StackPane {
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Button btnControl;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        // Controls
        mediaPlayer = new MediaPlayer(new Media(new File("resources/help.mp4").toURI().toString()));
        mediaView = new MediaView(mediaPlayer);

        btnControl = new Button();
    }

    private void layoutNodes() {
        mediaView.setFitWidth(Style.size(1120));
        mediaView.setFitHeight(Style.size(630));

        btnControl.setBackground(Style.bgImage("btnPlay.png", true));
        btnControl.setPrefSize(Style.size(100), Style.size(100));

        this.getChildren().addAll(mediaView, btnControl);
        this.setMaxWidth(mediaView.getFitWidth());
        this.setMaxHeight(mediaView.getFitHeight());
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public Button getBtnControl() {
        return btnControl;
    }
}

