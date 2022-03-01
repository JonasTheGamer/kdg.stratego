package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class NewGamePresenter {
    private ProgrammaModel model;
    private NewGameView view;

    public NewGamePresenter(ProgrammaModel model, NewGameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Code
        view.getTxtNamePlayer1().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.getLblNamePlayer1().setText(view.getTxtNamePlayer1().getText());
            }
        });
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }
}
