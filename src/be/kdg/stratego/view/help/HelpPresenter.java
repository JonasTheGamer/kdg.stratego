package be.kdg.stratego.view.help;

import be.kdg.stratego.model.ProgrammaModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
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

        // Play & Pause video
        view.getMediaView().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                videoControl();
            }
        });
        view.getBtnControl().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                videoControl();
            }
        });
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {
        Stage stage = (Stage) view.getScene().getWindow();

        // Stop media when window closes
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent windowEvent) {
                view.getMediaPlayer().stop();
            }
        });

        // Close window at end video
        view.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });

        // All KeyEvents
        view.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
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
            }
        });
    }

    private void videoControl() {
        boolean visible = !view.getBtnControl().isVisible();

        view.getBtnControl().setVisible(visible);
        if (visible) {
            view.getMediaPlayer().pause();
        } else {
            view.getMediaPlayer().play();
        }
    }

}
