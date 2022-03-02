package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.FieldTypes;
import be.kdg.stratego.view.Style;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;

public class ArmySetupPresenter {
    private ProgrammaModel model;
    private ArmySetupView view;

    private EventHandler onEmptyFieldClick = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent actionEvent) {
            // Find out which of the fields actually got pressed. All we need to know is the coordinates.
            Button clickedButton = (Button) actionEvent.getSource();
            String clickedId = clickedButton.getId();
            String[] idData = clickedId.split("-");
            int btnRow = Integer.parseInt(idData[1]);
            int btnCol = Integer.parseInt(idData[2]);


            // Currently, for debugging purposes, send an alert showing what the location is.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ding dong!");
            alert.setHeaderText(null);
            alert.setContentText("You clicked on me! My location is: \n\nX: " + btnCol + "\nY: " + btnRow);
            alert.initOwner(view.getScene().getWindow());

            alert.showAndWait();
        }
    };

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

        // Add row and column constraints for styling
        double fieldRowHeight = 75;
        double fieldColWidth  = 75;

        ColumnConstraints colStyle = new ColumnConstraints();
        colStyle.setPrefWidth(fieldColWidth);

        RowConstraints rowStyle = new RowConstraints();
        rowStyle.setPrefHeight(fieldRowHeight);

        for (int i = 0; i < fieldHeight; i++) {
            view.getGpField().getRowConstraints().add(rowStyle);
        }

        for (int i = 0; i < fieldWidth; i++) {
            view.getGpField().getColumnConstraints().add(colStyle);
        }

        // Fill top of field with question marks.
        for (int row = 0; row < amountOfRowsPerPlayer; row++) {
            // Per row

            for (int col = 0; col < fieldWidth; col++) {
                // Per column
                view.getGpField().add(FieldTypes.unknownField(fieldRowHeight, fieldColWidth), col, row);
            }
        }

        // Add no man's land
        //// Left path
        view.getGpField().add(FieldTypes.occupiedField(fieldRowHeight, fieldColWidth, "bomb"), 0, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 1, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 0, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 1, amountOfRowsPerPlayer + 1);

        // First swamp
        ImageView swamp1 = FieldTypes.swamp(fieldRowHeight * 2, fieldColWidth * 2);

        view.getGpField().add(swamp1, 2, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp1, 2);
        GridPane.setRowSpan(swamp1, 2);

        //// Middle path
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 4, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 5, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 4, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 5, amountOfRowsPerPlayer + 1);

        // Second swamp
        ImageView swamp2 = FieldTypes.swamp(fieldRowHeight * 2, fieldColWidth * 2);

        view.getGpField().add(swamp2, 6, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp2, 2);
        GridPane.setRowSpan(swamp2, 2);

        //// Right path
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 8, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 9, amountOfRowsPerPlayer);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 8, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldTypes.grass(fieldRowHeight, fieldColWidth), 9, amountOfRowsPerPlayer + 1);

        // Player fields
        // Fill top of field with question marks.
        for (int row = amountOfRowsPerPlayer + 2; row < fieldHeight; row++) {
            // Per row

            for (int col = 0; col < fieldWidth; col++) {
                // Per column
                Button emptyClickableField = new Button();
                emptyClickableField.setBackground(Style.grass);
                emptyClickableField.setPrefHeight(fieldRowHeight);
                emptyClickableField.setPrefWidth(fieldColWidth);

                emptyClickableField.setId("emptyfield-" + row + "-" + col);

                emptyClickableField.setOnAction(onEmptyFieldClick);

                view.getGpField().add(emptyClickableField, col, row);
            }
        }

    }

    public void addWindowEventHandlers() {

    }
}
