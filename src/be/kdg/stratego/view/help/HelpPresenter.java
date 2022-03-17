package be.kdg.stratego.view.help;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

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

        view.getMediaPlayer().setOnEndOfMedia(new Runnable() {
            @Override
            public void run() {
                MainMenuView newMainMenuView = new MainMenuView();
                MainMenuPresenter newMainMenuPresenter = new MainMenuPresenter(model, newMainMenuView);
            }
        });
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

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
