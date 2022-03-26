package be.kdg.stratego.view.endofgame;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

public class EndOfGamePresenter {
    private ProgrammaModel model;
    private EndOfGameView view;

    public EndOfGamePresenter(ProgrammaModel model, EndOfGameView view) {
        this.model = model;
        this.view = view;

        updateView();
        addEventHandlers();
    }

    public void addEventHandlers() {
        view.getBtnMenu().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Scene scene = view.getScene();
                Window wd = scene.getWindow();
                Stage stg = (Stage) wd;

                stg.close();
            }
        });
    }

    public void updateView() {
        // Set player that has won the game
        view.getLblSubtitle().setText(model.getGame().getCurrentPlayer().getName() + " has won the game!");

        // Set the amount of turns
        view.getLblTurnsValue().setText(Integer.toString(model.getGame().getCurrentPlayer().getAmountOfTurns()));

        // Set the leftover piece score
        view.getLblScoreValue().setText(Integer.toString(model.getGame().calculateLeftOverPiecesScore()));
    }

    public void addWindowEventHandlers() {

    }
}
