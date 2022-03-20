package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.Player;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.armysetup.ArmySetupPresenter;
import be.kdg.stratego.view.armysetup.ArmySetupView;
import be.kdg.stratego.view.mainmenu.MainMenuPresenter;
import be.kdg.stratego.view.mainmenu.MainMenuView;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;

public class NewGamePresenter {
    private ProgrammaModel model;
    private NewGameView view;

    private String[] selectedFlag = new String[2];

    public NewGamePresenter(ProgrammaModel model, NewGameView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        //MaxNameCharacters
        ///Player 1
        view.getTxtName()[0].setOnKeyTyped(keyEvent -> maxNameCharacters(0));
        /// Player 2
        view.getTxtName()[1].setOnKeyTyped(keyEvent -> maxNameCharacters(1));


        //BtnFlag
        ///Player 1
        for (Button btn : view.getBtnFlag()[0]) {
            btn.setOnAction(actionEvent -> selectFlag(btn, 0));
        }
        ///Player 2
        for (Button btn : view.getBtnFlag()[1]) {
            btn.setOnAction(actionEvent -> selectFlag(btn, 1));
        }


        //BtnReady
        view.getBtnReady().setOnAction(actionEvent -> {
            /// Create new game with players
            model.newGame(
                    new Player(view.getLblName()[0].getText(), view.getCpColor()[0].getValue(), selectedFlag[0]),
                    new Player(view.getLblName()[1].getText(), view.getCpColor()[1].getValue(), selectedFlag[1])
            );


            /// Switch to the army setup view!
            Style.changeScreen(Style.Screens.ARMYSETUP, model, view);
        });


        //BtnCancel
        view.getBtnCancel().setOnAction(actionEvent -> Style.changeScreen(Style.Screens.MAINMENU, model, view));
    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }

    //Private methods
    private void selectFlag(Button btn, int player) {
        /// Save clicked btnFlag
        selectedFlag[player] = btn.getBackground().getImages().get(0).getImage().getUrl();

        /// Set all borders white
        for (int i = 0; i < view.getBtnFlag()[player].length; i++) {
            view.getBtnFlag()[player][i].setBorder(Style.border(Color.WHITE));
        }

        /// Set current border red
        btn.setBorder(Style.border(Color.RED));
    }

    private void maxNameCharacters(int player) {
        ///Max characters for name
        String input = view.getTxtName()[player].getText();

        if (input.length() > Player.maxNameCharacters) {
            view.getTxtName()[player].setText(input.substring(0, Player.maxNameCharacters));
            view.getTxtName()[player].positionCaret(Player.maxNameCharacters);
        }

        ///Change Lblname
        view.getLblName()[player].setText(view.getTxtName()[player].getText());
    }
}
