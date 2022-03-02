package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

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
        // Initialize variables
        int fieldWidth = model.getGameBoard().getGrootteX();
        int fieldHeight = model.getGameBoard().getGrootteY();

        int amountOfRowsPerPlayer = (fieldHeight -2 ) / 2;

        // Empty game field
        view.getGpField().getChildren().clear();

        // Fill top of field with question marks.
        for (int row = 0; row < amountOfRowsPerPlayer; row++) {
            // Per row
            
            for (int col = 0; col < fieldWidth; col++) {
                // Per column
                view.getGpField().add(new Label("?"), col, row);
            }
        }

        // Add no man's land
        //// Left path
        view.getGpField().add(new Label(""), 0, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 1, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 0, amountOfRowsPerPlayer + 1);
        view.getGpField().add(new Label(""), 1, amountOfRowsPerPlayer + 1);

        // First swamp
        ImageView swamp1 = new ImageView("/Swamp.png");

        view.getGpField().add(swamp1, 0, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp1, 2);
        GridPane.setRowSpan(swamp1, 2);

        //// Middle path
        view.getGpField().add(new Label(""), 4, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 5, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 4, amountOfRowsPerPlayer + 1);
        view.getGpField().add(new Label(""), 5, amountOfRowsPerPlayer + 1);

        // Second swamp
        ImageView swamp2 = new ImageView("/Swamp.png");

        view.getGpField().add(swamp2, 0, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp2, 2);
        GridPane.setRowSpan(swamp2, 2);

        //// Right path
        view.getGpField().add(new Label(""), 8, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 9, amountOfRowsPerPlayer);
        view.getGpField().add(new Label(""), 8, amountOfRowsPerPlayer + 1);
        view.getGpField().add(new Label(""), 9, amountOfRowsPerPlayer + 1);

    }

    public void addWindowEventHandlers() {

    }
}
