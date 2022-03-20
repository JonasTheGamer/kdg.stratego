package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.*;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;

import java.util.HashSet;


public class BattleFieldPresenter {

    // References
    private ProgrammaModel model;
    private BattleFieldView view;
    private Player currentPlayer;

    // Variables for moving a piece
    private MovingPiece selectedPiece;
    private HashSet<GameBoardField> allowedMoves;

    public BattleFieldPresenter(ProgrammaModel model, BattleFieldView view) {
        this.model = model;
        this.view = view;

        // Set the first player who will be playing
        currentPlayer = model.getGame().getCurrentPlayer();

        // Initialize allowed moves
        allowedMoves = new HashSet<>();

        // Make their pieces visible
        currentPlayer.showPieces();

        this.addEventHandlers();
        this.updateView();
    }

    private synchronized void addEventHandlers() {
        // Click on field
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();
        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {

                StackPane fieldPane = field.getPane();
                fieldPane.setOnMouseClicked(mouseEvent -> {
                    // UNSELECT - Check if player clicked on the currently selected piece
                    if (field.isOccupied() && field.getPiece().equals(selectedPiece)) {
                        // Clear the selection
                        clearSelection();

                        // Update view
                        updateView();
                        return;
                    }

                    // SELECT - Did the player click on a piece that's one of their own?
                    if (field.isOccupied() && field.getPiece().getPlayer().equals(currentPlayer)) {
                        // They probably want to move this piece
                        Piece piece = field.getPiece();

                        // But let's check if it's movable
                        if (piece instanceof MovingPiece) {
                            // Select it for movement!
                            selectedPiece = (MovingPiece) piece;

                            // Get the allowed moves
                            allowedMoves = selectedPiece.getAllowedMoves();

                            // Highlight them
                            model.getGameBoard().highLightAllowedMoves(selectedPiece);

                            updateView();

                        } else {
                            // Ignore their request
                        }
                        return;
                    }

                    // MOVE
                    MovingPiece piece = selectedPiece;

                    // Move the piece
                    try {
                        piece.moveTo(field);
                        clearSelection();
                    } catch (InvalidMoveException exception) {
                        // Place an X on the field
                        field.setInvalid(true);
                        updateView();

                        // delay & exit on other thread
                        Platform.runLater(() -> {
                            // Wait 2 seconds
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException ex) {
                            }
                            // Remove the x from the field)
                            field.setInvalid(false);
                            updateView();
                        });
                    }

                    // Update the view
                    updateView();
                });
            }
        }
    }

    private void updateView() {
        // Game board
        //// Empty game board gridpane
        view.getGpBoard().getChildren().clear();

        //// Add the fields to the gridpane
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();

        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                StackPane fieldPane = field.getPane();
                view.getGpBoard().add(fieldPane, field.getPositionX(), field.getPositionY());
            }
        }
        addEventHandlers();
    }
    public void addWindowEventHandlers() {

    }


    private void clearSelection() {
        // Unhighlight
        model.getGameBoard().unHighlightAllFields();

        // Unselect
        selectedPiece = null;
        allowedMoves.clear();
    }
}
