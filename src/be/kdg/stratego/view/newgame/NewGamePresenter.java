package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
        view.getTxtName()[0].setOnKeyTyped(keyEvent -> view.getLblName()[0].setText(view.getTxtName()[0].getText()));
        view.getTxtName()[1].setOnKeyTyped(keyEvent -> view.getLblName()[1].setText(view.getTxtName()[1].getText()));
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }
}
