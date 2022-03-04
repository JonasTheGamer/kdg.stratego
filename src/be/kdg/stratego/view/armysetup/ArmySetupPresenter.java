package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.model.Speelveld;
import be.kdg.stratego.view.FieldType;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

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
        int boardWidth = model.getGameBoard().getGrootteX();
        int boardHeight = model.getGameBoard().getGrootteY();

        int amountOfRowsPerPlayer = (model.getGameBoard().getGrootteY() -2 ) / 2;

        // Empty game board
        view.getGpBoard().getChildren().clear();

        // Fill the board & add them to the gridpane

        Speelveld[][] fields = model.getGameBoard().getSpeelvelden();
        for (int posX = 0; posX < model.getGameBoard().getGrootteX(); posX++) {
            for (int posY = 0; posY < model.getGameBoard().getGrootteY(); posY++) {
                Speelveld field = fields[posX][posY];
                StackPane fieldType = field.getType();
                view.getGpBoard().add(fieldType, posX, posY);
            }
        }

        /*

        // Add row and column constraints for styling
        double fieldRowHeight = 75;
        double fieldColWidth  = 75;

        ColumnConstraints colStyle = new ColumnConstraints();
        colStyle.setPrefWidth(fieldColWidth);

        RowConstraints rowStyle = new RowConstraints();
        rowStyle.setPrefHeight(fieldRowHeight);

        // Fill top of field with question marks.
        for (int row = 0; row < amountOfRowsPerPlayer; row++) {
            // Per row

            for (int col = 0; col < model.getGameBoard().getGrootteX(); col++) {
                // Per column
                view.getGpField().add(FieldType.unknownField(fieldRowHeight, fieldColWidth), col, row);
            }
        }

        // Add no man's land
        //// Left path
        view.getGpField().add(FieldType.occupiedField(fieldRowHeight, fieldColWidth, "bomb"), 0, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 1, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 0, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 1, amountOfRowsPerPlayer + 1);

        // First swamp
        StackPane swamp1 = FieldType.swamp(fieldRowHeight * 2, fieldColWidth * 2);

        view.getGpField().add(swamp1, 2, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp1, 2);
        GridPane.setRowSpan(swamp1, 2);

        //// Middle path
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 4, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 5, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 4, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 5, amountOfRowsPerPlayer + 1);

        // Second swamp
        StackPane swamp2 = FieldType.swamp(fieldRowHeight * 2, fieldColWidth * 2);

        view.getGpField().add(swamp2, 6, amountOfRowsPerPlayer);
        GridPane.setColumnSpan(swamp2, 2);
        GridPane.setRowSpan(swamp2, 2);

        //// Right path
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 8, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 9, amountOfRowsPerPlayer);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 8, amountOfRowsPerPlayer + 1);
        view.getGpField().add(FieldType.grass(fieldRowHeight, fieldColWidth), 9, amountOfRowsPerPlayer + 1);

        // Player fields
        // Fill top of field with question marks.
        for (int row = amountOfRowsPerPlayer + 2; row < model.getGameBoard().getGrootteY(); row++) {
            // Per row

            for (int col = 0; col < model.getGameBoard().getGrootteX(); col++) {
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
        */

    }

    public void addWindowEventHandlers() {

    }
}
