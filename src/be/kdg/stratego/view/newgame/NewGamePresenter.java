package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
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

        // Change name label on text change
        view.getTxtName()[0].setOnKeyTyped(keyEvent -> view.getLblName()[0].setText(view.getTxtName()[0].getText()));
        view.getTxtName()[1].setOnKeyTyped(keyEvent -> view.getLblName()[1].setText(view.getTxtName()[1].getText()));

        view.getBtnReady().setOnAction(actionEvent -> {
            // Create both players
            model.createPlayers(
                    view.getLblName()[0].getText(),
                    view.getCpColor()[0].getValue(),
                    "default",
                    view.getLblName()[1].getText(),
                    view.getCpColor()[1].getValue(),
                    "default"
            );

            // Get the board ready
            model.createGameBoard();

            // Switch to the army setup view!
            ArmySetupView armySetupView = new ArmySetupView();
            ArmySetupPresenter armySetupPresenter = new ArmySetupPresenter(model, armySetupView, model.getPlayers()[0]);
            view.getScene().setRoot(armySetupView);
        });

        view.getBtnCancel().setOnAction(actionEvent -> {
            MainMenuView newMainMenuView = new MainMenuView();
            MainMenuPresenter newMainMenuPresenter = new MainMenuPresenter(model, newMainMenuView);
            view.getScene().setRoot(newMainMenuView);
        });

    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }
}
