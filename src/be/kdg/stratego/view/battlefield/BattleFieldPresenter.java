package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

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

                    // SELECT - Check if the player clicked on their own piece?
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
                            // Jonas: Ignore their request
                        }
                        return;
                    }

                    // MOVE - Check if a piece was selected
                    if (selectedPiece != null) {
                        try {
                            selectedPiece.moveTo(field);
                            clearSelection();
                        } catch (InvalidMoveException exception) {
                            /// Place an X on the field
                            //// Define X image
                            ImageView ivInvalid = new ImageView("error.png");
                            ivInvalid.setFitHeight(field.getFieldSize());
                            ivInvalid.setFitWidth(field.getFieldSize());
                            fieldPane.getChildren().add(ivInvalid);

                            //// Values for fade
                            KeyValue transparent = new KeyValue(ivInvalid.opacityProperty(), 0.0);
                            KeyValue opaque = new KeyValue(ivInvalid.opacityProperty(), 1.0);

                            //// Timeline
                            Timeline timeline = new Timeline();
                            timeline.getKeyFrames().addAll(
                                    new KeyFrame(Duration.ZERO, transparent),
                                    new KeyFrame(Duration.millis(250), opaque),
                                    new KeyFrame(Duration.millis(750), opaque),
                                    new KeyFrame(Duration.millis(1000), transparent)
                            );
                            timeline.play();
                        }
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
