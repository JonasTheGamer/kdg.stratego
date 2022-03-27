package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.*;
import be.kdg.stratego.model.pieces.Flag;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.Board;
import be.kdg.stratego.view.endofgame.*;
import javafx.animation.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.*;


public class BattleFieldPresenter {
    // Variables for generating StackPanes
    private final double FIELD_SIZE = Style.size(65);

    // References
    private ProgrammaModel model;
    private BattleFieldView view;
    private boolean killFadeOngoing = false;
    private HashMap<GameBoardField, Pane> fieldPanes;
    private GameStopwatch stopwatch;

    // Variables for moving a piece
    private MovingPiece selectedPiece;

    // Variables for switching to the next player
    private ArrayList<Piece> lastKilledPieces;
    private MovingPiece attackingPiece;
    private boolean overlayClickDebounce;

    public BattleFieldPresenter(ProgrammaModel model, BattleFieldView view) {
        this.model = model;
        this.view = view;
        this.fieldPanes = new HashMap<>();
        this.stopwatch = new GameStopwatch();
        startStopwatch();

        lastKilledPieces = new ArrayList<>();
        attackingPiece = null;
        overlayClickDebounce = false;

        this.addEventHandlers();
        this.updateView();

        model.getGame().start();
    }

    private synchronized void addEventHandlers() {
        view.getBtnClose().setOnAction(actionEvent -> {
            //Liam: game.save() & game.stop() modal
            Style.changeScreen(Style.Screens.MAINMENU, model, view);
        });

        // Click on field
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();
        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                Pane fieldPane = fieldPanes.get(field);
                if (!Objects.isNull(fieldPane)) {
                    fieldPane.setOnMouseClicked(mouseEvent -> {

                        if (!killFadeOngoing) {
                            // UNSELECT - Check if player clicked on the currently selected piece
                            if (field.isOccupied() && field.getPiece().equals(selectedPiece)) {
                                // Clear the selection
                                clearSelection();

                                // Update view
                                updateView();
                                return;
                            }

                            // SELECT - Check if the player clicked on their own piece?
                            if (field.isOccupied() && field.getPiece().getPlayer().equals(model.getGame().getCurrentPlayer())) {
                                // They probably want to move this piece
                                Piece piece = field.getPiece();

                                // But let's check if it's movable
                                if (piece instanceof MovingPiece) {
                                    // Select it for movement!
                                    selectedPiece = (MovingPiece) piece;

                                    // Highlight them
                                    model.getGameBoard().highLightAllowedMoves(selectedPiece);

                                    updateView();

                                }
                                return;
                            }

                            // MOVE - Check if a piece was selected
                            if (selectedPiece != null) {
                                try {
                                    // Move the piece
                                    attackingPiece = selectedPiece;
                                    ArrayList<Piece> killedPieces = selectedPiece.moveTo(field);

                                    // Clear the selection
                                    clearSelection();

                                    // Update the view
                                    updateView();

                                    // Make killed pieces transparent
                                    SequentialTransition showKilledPiecesFade = null;
                                    boolean containsFlag = false;

                                    for (Piece killedPiece : killedPieces) {
                                        killFadeOngoing = true;

                                        StackPane panePiece = (StackPane) fieldPanes.get(killedPiece.getField()).getChildren().get(0);

                                        //// Half Fadeout
                                        FadeTransition transitionFadeOut = new FadeTransition(Duration.millis(250), panePiece);
                                        transitionFadeOut.setFromValue(1);
                                        transitionFadeOut.setToValue(0.5);
                                        PauseTransition pauseTransition = new PauseTransition(Duration.millis(1250));
                                        showKilledPiecesFade = new SequentialTransition(
                                                transitionFadeOut,
                                                pauseTransition
                                        );
                                        showKilledPiecesFade.play();


                                        // Check whether the killed piece was the flag
                                        if (killedPiece instanceof Flag) {
                                            containsFlag = true;
                                        }
                                    }

                                    lastKilledPieces = killedPieces;

                                    // Check if the game ended, or we need to switch to the next player
                                    if (containsFlag) {
                                        // Stop the game
                                        model.getGame().stop(model.getWinnersFile());

                                        // Show the winner screen
                                        EndOfGameView eoGameView = new EndOfGameView();
                                        EndOfGamePresenter eoGamePresenter = new EndOfGamePresenter(model, eoGameView);

                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(eoGameView));

                                        stage.initOwner(view.getScene().getWindow());
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.showAndWait();

                                        Style.changeScreen(Style.Screens.MAINMENU, model, view);

                                    } else {
                                        // Switch to next player
                                        if (!Objects.isNull(showKilledPiecesFade)) {
                                            // Wait for the fade to finish before showing the overlay
                                            showKilledPiecesFade.setOnFinished(actionEvent -> toggleNextPlayerOverlay());
                                        } else {
                                            toggleNextPlayerOverlay();
                                        }
                                    }

                                } catch (InvalidMoveException exception) {
                                    /// Place an X on the field
                                    //// Define X image
                                    ImageView ivInvalid = new ImageView("error.png");
                                    fieldPane.getChildren().add(ivInvalid);
                                    ivInvalid.setFitHeight(FIELD_SIZE);
                                    ivInvalid.setFitWidth(FIELD_SIZE);

                                    //// Fade
                                    FadeTransition transitionFadeIn = new FadeTransition(Duration.millis(250), ivInvalid);
                                    transitionFadeIn.setFromValue(0);
                                    transitionFadeIn.setToValue(1);
                                    PauseTransition pauseTransition = new PauseTransition(Duration.millis(500));
                                    FadeTransition transitionFadeOut = new FadeTransition(Duration.millis(250), ivInvalid);
                                    transitionFadeOut.setFromValue(1);
                                    transitionFadeOut.setToValue(0);
                                    SequentialTransition errorTransitions = new SequentialTransition(
                                            transitionFadeIn,
                                            pauseTransition,
                                            transitionFadeOut
                                    );
                                    errorTransitions.play();

                                } catch (IOException e) {
                                    Alert alert = new Alert(Alert.AlertType.ERROR);
                                    alert.setTitle("Error");
                                    alert.setHeaderText(null);
                                    alert.setContentText("An error happened whilst trying to save your scores, we're sorry...");

                                    alert.showAndWait();
                                }
                            }
                        }
                    });
                }
            }
        }

        // Click on overlay to pass to the next player
        view.getBtnNextPlayer().setOnAction(actionEvent -> {
            // Do nothing if the debounce is still activated
            if (overlayClickDebounce) return;

            // Enable the debounce
            overlayClickDebounce = true;

            // Switch to the next player
            model.getGame().nextTurn();

            // Update the view
            updateView();

            // Hide the overlay
            toggleNextPlayerOverlay();

            // If pieces were killed, show them & do the animation
            if (lastKilledPieces.size() > 0) {
                // Make the killed pieces visible again
                for (Piece killedPiece : lastKilledPieces) {
                    killedPiece.setHidden(false);
                }

                // Make the attacking piece visible
                attackingPiece.setHidden(false);

                // Update the view
                updateView();

                // Make the killed pieces vanish
                for (Piece killedPiece : lastKilledPieces) {
                    Pane killedPiecePane = fieldPanes.get(killedPiece.getField());
                    StackPane panePiece = (StackPane) killedPiecePane.getChildren().get(0);

                    //// Full Fadeout
                    PauseTransition pauseTransition = new PauseTransition(Duration.millis(1750));
                    FadeTransition transitionFadeOut = new FadeTransition(Duration.millis(250), panePiece);
                    transitionFadeOut.setFromValue(0.5);
                    transitionFadeOut.setToValue(0);
                    SequentialTransition fadeoutTransitions = new SequentialTransition(
                            pauseTransition,
                            transitionFadeOut
                    );
                    fadeoutTransitions.play();

                    fadeoutTransitions.setOnFinished(actionEvent1 -> {
                        killedPiece.finishKill();
                        killFadeOngoing = false;
                        updateView();
                    });
                }
            } else {
                // Highlight the moved piece
                attackingPiece.getField().highLight();
                updateView();
            }
        });
    }

    private void updateView() {
        // Title
        view.getLblScreenTitle().setText(model.getGame().getCurrentPlayer().getName());

        // Game board
        //// Empty game board gridpane
        view.getGpBoard().getChildren().clear();

        //// Add the fields to the gridpane
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();

        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                Pane fieldPane = Board.generatePane(field, FIELD_SIZE);
                fieldPanes.put(field, fieldPane);
                view.getGpBoard().add(fieldPane, field.getPositionX(), field.getPositionY());
            }
        }
        addEventHandlers();
    }

    public void addWindowEventHandlers() {

    }


    // Methods
    private void clearSelection() {
        // Unhighlight
        model.getGameBoard().unHighlightAllFields();

        // Unselect
        selectedPiece = null;
    }

    private void toggleNextPlayerOverlay() {
        Button btn = view.getBtnNextPlayer();

        //Toggler of this button
        boolean nextPlayerOverlay = !view.getBtnNextPlayer().isVisible();

        //When turned on
        if (nextPlayerOverlay) {
            btn.setVisible(true);
            btn.setText("Pass the computer to " + model.getGame().getNextPlayer().getName() + ". \nClick to continue");
        }

        //Each toggle
        FadeTransition transition = new FadeTransition(Duration.millis(500), btn);
        transition.setFromValue(nextPlayerOverlay ? 0 : 1);
        transition.setToValue(nextPlayerOverlay ? 1 : 0);
        transition.play();

        transition.setOnFinished(actionEvent -> overlayClickDebounce = false);

        //When turned off
        if (!nextPlayerOverlay) {
            transition.setOnFinished(actionEvent -> btn.setVisible(false));
        }
    }

    private void startStopwatch() {
        Timeline stopwatchTimeline = new Timeline();
        stopwatchTimeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1), event -> {
                    stopwatch.tick();
                    view.getLblClock().setText(String.format("%02d:%02d:%02d", stopwatch.getHours(), stopwatch.getMinutes(), stopwatch.getSeconds()));
                })
        );

        stopwatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopwatchTimeline.play();
    }
}



















