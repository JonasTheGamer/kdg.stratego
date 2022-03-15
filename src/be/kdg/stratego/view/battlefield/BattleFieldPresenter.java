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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.logging.SocketHandler;

public class BattleFieldPresenter {
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
        currentPlayer = model.getGame().getPlayers()[0];

        // Initialize allowed moves
        allowedMoves = new HashSet<>();

        // Make their pieces visible
        currentPlayer.showPieces();

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();

        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                StackPane fieldPane = field.getPane();

                fieldPane.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        //Liam: Model

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
                                allowedMoves = model.getGameBoard().getAllowedMoves(selectedPiece);

                                // Highlight them
                                model.getGameBoard().highLightAllowedMoves(selectedPiece);

                                updateView();

                                return;
                            } else {
                                // Ignore their request
                                return;
                            }
                        }

                        // MOVE - Did the player click in one of the allowed fields (allowedMoves)?
                        if (allowedMoves.contains(field)) {
                            MovingPiece piece = (MovingPiece) selectedPiece;

                            // Clear the selection
                            clearSelection();

                            // Move the field
                            piece.moveTo(field);

                            // Update the view
                            updateView();
                        }
                    }
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

    private void addWindowEventHandlers() {

    }


    private void clearSelection() {
        // Unhighlight
        model.getGameBoard().unHighlightAllFields();

        // Unselect
        selectedPiece = null;
        allowedMoves.clear();
    }
}
