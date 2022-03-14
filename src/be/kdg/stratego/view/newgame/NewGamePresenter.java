package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.Player;
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

        int maxCharacters = 10;
        view.getTxtName()[0].setOnKeyTyped(keyEvent -> {
            //Max characters for name
            String input = view.getTxtName()[0].getText();

            if (input.length() > maxCharacters) {
                view.getTxtName()[0].setText(input.substring(0, maxCharacters));
                view.getTxtName()[0].positionCaret(maxCharacters);
            }

            //Change Lblname
            view.getLblName()[0].setText(view.getTxtName()[0].getText());
        });
        view.getTxtName()[1].setOnKeyTyped(keyEvent -> {
            //Max characters for name
            String input = view.getTxtName()[1].getText();

            if (input.length() > maxCharacters) {
                view.getTxtName()[1].setText(input.substring(0, maxCharacters));
                view.getTxtName()[1].positionCaret(maxCharacters);
            }

            //Change Lblname
            view.getLblName()[1].setText(view.getTxtName()[1].getText());
        });


        view.getBtnReady().setOnAction(actionEvent -> {
            // Create players
            model.getGame().setPlayer1(new Player(view.getLblName()[0].getText(), view.getCpColor()[0].getValue(), ""));
            model.getGame().setPlayer2(new Player(view.getLblName()[1].getText(), view.getCpColor()[1].getValue(), ""));

            // Switch to the army setup view!
            ArmySetupView armySetupView = new ArmySetupView();
            ArmySetupPresenter armySetupPresenter = new ArmySetupPresenter(model, armySetupView, model.getGame().getPlayers()[0], 0);
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
