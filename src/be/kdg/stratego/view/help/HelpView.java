package be.kdg.stratego.view.help;

import be.kdg.stratego.view.Style;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.File;

public class HelpView extends StackPane {
    // Controls
    private MediaPlayer mediaPlayer;
    private MediaView mediaView;
    private Button btnControl;

    public HelpView() {
        this.initialiseNodes();
        this.layoutNodes();
    }

    private void initialiseNodes() {
        mediaPlayer = new MediaPlayer(new Media(new File("resources/help.mp4").toURI().toString()));
        mediaView = new MediaView(mediaPlayer);

        btnControl = new Button();
    }

    private void layoutNodes() {
        mediaView.setFitHeight(this.getHeight());
        mediaView.setFitWidth(this.getWidth());

        btnControl.setBackground(Style.bgImage("btnPlay.png",true));
        btnControl.setPrefSize(Style.size(100),Style.size(100));

        this.getChildren().addAll(mediaView,btnControl);
        this.setPrefSize(Style.size(1000),Style.size(500));
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void setMediaPlayer(MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
    }

    public MediaView getMediaView() {
        return mediaView;
    }

    public void setMediaView(MediaView mediaView) {
        this.mediaView = mediaView;
    }

    public Button getBtnControl() {
        return btnControl;
    }

    public void setBtnControl(Button btnControl) {
        this.btnControl = btnControl;
    }
}

