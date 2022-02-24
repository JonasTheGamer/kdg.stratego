package be.kdg.stratego.view.mainmenu;

import be.kdg.stratego.model.ProgrammaModel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class MainMenuPresenter {
    private ProgrammaModel model;
    private MainMenuView view;

    public MainMenuPresenter(ProgrammaModel model, MainMenuView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //Code
        view.getBtnQuit().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage mainStage = (Stage) view.getScene().getWindow();
                mainStage.close();
            }
        });

        view.getBtnHelp().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getBtnHelp().setText("Nee");
            }
        });
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }

}
