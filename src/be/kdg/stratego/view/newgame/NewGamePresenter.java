package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.Player;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
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

        //TxtName maxCharacters
        int maxCharacters = 10;
        ///Player 1
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
        /// Player 2
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


        //BtnFlag
        String[] selectedFlag = {"",""};
        ///Player 1
        for (Button btn : view.getBtnFlag()[0]) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    selectedFlag[0] = btn.getId();
                }
            });
        }
        ///Player 2
        for (Button btn : view.getBtnFlag()[1]) {
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    selectedFlag[1] = btn.getId();
                }
            });
        }


        //BtnReady
        view.getBtnReady().setOnAction(actionEvent -> {
            /// Create players
            model.getGame().setPlayer1(new Player(view.getLblName()[0].getText(), view.getCpColor()[0].getValue(), selectedFlag[0]));
            model.getGame().setPlayer2(new Player(view.getLblName()[1].getText(), view.getCpColor()[1].getValue(), selectedFlag[1]));

            /// Switch to the army setup view!
            ArmySetupView armySetupView = new ArmySetupView();
            ArmySetupPresenter armySetupPresenter = new ArmySetupPresenter(model, armySetupView, model.getGame().getPlayers()[0], 0);
            view.getScene().setRoot(armySetupView);
        });


        //BtnCancel
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
