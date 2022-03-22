package be.kdg.stratego.view.newgame;

import be.kdg.stratego.model.Player;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
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
        // Players (1 & 2)
        /// TxtNameChanged
        view.getTxtName()[0].setOnKeyTyped(keyEvent -> txtNameChanged(0));
        view.getTxtName()[1].setOnKeyTyped(keyEvent -> txtNameChanged(1));

        // BtnFlag
        for (Button btn : view.getBtnFlag()[0]) {
            btn.setOnAction(actionEvent -> selectFlag(btn, 0));
        }
        for (Button btn : view.getBtnFlag()[1]) {
            btn.setOnAction(actionEvent -> selectFlag(btn, 1));
        }

        // Menu
        view.getBtnReady().setOnAction(actionEvent -> {
            /// Create new game with players
            model.newGame(
                    new Player(view.getLblName()[0].getText(), view.getCpColor()[0].getValue().toString(), selectedFlag[0]),
                    new Player(view.getLblName()[1].getText(), view.getCpColor()[1].getValue().toString(), selectedFlag[1])
            );

            /// Switch to the army setup view!
            Style.changeScreen(Style.Screens.ARMYSETUP, model, view);
        });

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

        /// Set selected flag border red
        btn.setBorder(Style.border(Color.RED));
    }

    private void txtNameChanged(int player) {
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
