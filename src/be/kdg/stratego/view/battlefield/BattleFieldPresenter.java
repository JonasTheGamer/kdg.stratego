package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.model.*;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Objects;

public class BattleFieldPresenter {
    private ProgrammaModel model;
    private BattleFieldView view;
    private Player currentPlayer;

    // Variables for moving a piece
    private MovingPiece selectedPiece;

    private final EventHandler onFieldClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Find out which of the fields actually got pressed. All we need to know is the coordinates.
            StackPane clickedPane = (StackPane) mouseEvent.getSource();
            String clickedId = clickedPane.getId();

            String[] idData = clickedId.split("-");

            int posX = Integer.parseInt(idData[0]);
            int posY = Integer.parseInt(idData[1]);

            // Now, figure out the field object
            GameBoardField field = model.getGameBoard().getGameBoardField(posX, posY);

            // Did the player click on a piece that's one of their own?
            if (field.isOccupied() && field.getPiece().getPlayer().equals(currentPlayer)) {
                // They probably want to move this piece
                Piece piece = field.getPiece();

                // But let's check if it's movable
                if(piece instanceof MovingPiece) {
                    // Select it for movement!
                    selectedPiece = (MovingPiece) piece;
                    return;
                } else {
                    // Ignore their request
                    return;
                }
            } else {
                // They probably clicked somewhere where they want their selected piece to go.
                // Let's check if they have one selected
                if(!Objects.isNull(selectedPiece)) {
                    // Attempt to move the piece
                } else {
                    // Ignore their request
                    return;
                }
            }

        }
    };

    public BattleFieldPresenter(ProgrammaModel model, BattleFieldView view) {
        this.model = model;
        this.view = view;

        // Set the first player who will be playing
        currentPlayer = model.getGame().getPlayers()[0];

        // Make their pieces visible
        currentPlayer.showPieces();

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {

    }

    private void updateView() {
        // Initialize variables
        int boardWidth = model.getGameBoard().getGrootteX();
        int boardHeight = model.getGameBoard().getGrootteY();
        int amountOfRowsPerPlayer = (model.getGameBoard().getGrootteY() - 2) / 2;

        // Game board
        //// Empty game board gridpane
        view.getGpBoard().getChildren().clear();

        //// Add the fields to the gridpane
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();

        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                StackPane fieldPane = field.getPane();
                fieldPane.setOnMouseClicked(onFieldClick);
                view.getGpBoard().add(fieldPane, field.getPositionX(), field.getPositionY());
            }
        }
    }

    public void addWindowEventHandlers() {

    }

}
