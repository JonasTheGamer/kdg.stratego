package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ArmySetupPresenter {
    private ProgrammaModel model;
    private ArmySetupView view;

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view) {
        this.model = model;
        this.view = view;
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Code

    }

    private void updateView() {

    }

    public void addWindowEventHandlers() {

    }
}
