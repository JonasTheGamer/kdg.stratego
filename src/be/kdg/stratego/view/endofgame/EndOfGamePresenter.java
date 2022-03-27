package be.kdg.stratego.view.endofgame;

import be.kdg.stratego.model.ProgrammaModel;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EndOfGamePresenter {
    private ProgrammaModel model;
    private EndOfGameView view;

    public EndOfGamePresenter(ProgrammaModel model, EndOfGameView view) {
        this.model = model;
        this.view = view;

        addEventHandlers();
        updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
        // Set player that has won the game
        view.getLblSubtitle().setText(model.getGame().getCurrentPlayer().getName() + " has won the game!");

        // Set the amount of turns
        view.getLblTurnsValue().setText(Integer.toString(model.getGame().getCurrentPlayer().getAmountOfTurns()));

        // Set the leftover piece score
        view.getLblScoreValue().setText(Integer.toString(model.getGame().calculateLeftOverPiecesScore()));
    }

    public void windowEventHandlers(){
        Stage stage = (Stage) view.getScene().getWindow();

        view.getBtnMenu().setOnAction(actionEvent -> stage.close());
    }
}
