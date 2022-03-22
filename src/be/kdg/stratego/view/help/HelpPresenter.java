package be.kdg.stratego.view.help;

import be.kdg.stratego.model.ProgrammaModel;
import javafx.scene.input.KeyCode;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

public class HelpPresenter {
    private ProgrammaModel model;
    private HelpView view;

    public HelpPresenter(ProgrammaModel model, HelpView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

        view.getMediaView().setOnMouseClicked(mouseEvent -> videoControl());

        view.getBtnControl().setOnAction(actionEvent -> videoControl());
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {
        Stage stage = (Stage) view.getScene().getWindow();

        // Stop media when window closes
        stage.setOnCloseRequest(windowEvent -> view.getMediaPlayer().stop());

        // Close window at end video
        view.getMediaPlayer().setOnEndOfMedia(() -> stage.close());

        // All KeyEvents
        view.getScene().setOnKeyPressed(keyEvent -> {
            MediaPlayer mp = view.getMediaPlayer();

            if (keyEvent.getCode() == KeyCode.SPACE) {
                videoControl();
            }

            if (keyEvent.getCode() == KeyCode.LEFT || keyEvent.getCode() == KeyCode.Q) {
                mp.seek(new Duration(mp.getCurrentTime().toMillis()-5000));
            }

            if (keyEvent.getCode() == KeyCode.RIGHT || keyEvent.getCode() == KeyCode.D) {
                mp.seek(new Duration(mp.getCurrentTime().toMillis()+5000));
            }
        });
    }

    private void videoControl() {
        // Play & Pause video
        boolean visible = !view.getBtnControl().isVisible();

        view.getBtnControl().setVisible(visible);
        if (visible) {
            view.getMediaPlayer().pause();
        } else {
            view.getMediaPlayer().play();
        }
    }

}
