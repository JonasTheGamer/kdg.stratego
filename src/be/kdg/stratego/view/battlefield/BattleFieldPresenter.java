package be.kdg.stratego.view.battlefield;

import be.kdg.stratego.exceptions.InvalidMoveException;
import be.kdg.stratego.model.*;
import be.kdg.stratego.model.pieces.Flag;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.endofgame.EndOfGamePresenter;
import be.kdg.stratego.view.endofgame.EndOfGameView;
import be.kdg.stratego.view.help.HelpPresenter;
import be.kdg.stratego.view.help.HelpView;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;


public class BattleFieldPresenter {
    // Variables for generating StackPanes
    private final double fieldSize = Style.size(65);
    // References
    private ProgrammaModel model;
    private BattleFieldView view;
    private boolean killFadeOngoing = false;
    private HashMap<GameBoardField, StackPane> fieldPanes;

    // Variables for moving a piece
    private MovingPiece selectedPiece;

    // Variables for switching to the next player
    private ArrayList<Piece> lastKilledPieces;
    private MovingPiece attackingPiece;

    public BattleFieldPresenter(ProgrammaModel model, BattleFieldView view) {
        this.model = model;
        this.view = view;
        this.fieldPanes = new HashMap<>();

        lastKilledPieces = new ArrayList<>();
        attackingPiece = null;

        this.addEventHandlers();
        this.updateView();
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
                StackPane fieldPane = fieldPanes.get(field);
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
                                    Timeline showKilledPieces = null;
                                    boolean containsFlag = false;
                                    for (Piece killedPiece : killedPieces) {
                                        killFadeOngoing = true;

                                        StackPane killedPiecePane = fieldPanes.get(killedPiece.getField());
                                        ImageView ivTower = null;
                                        ImageView ivPiece = null;

                                        for (Node child : killedPiecePane.getChildren()) {
                                            if (child.getId().equals("tower")) {
                                                ivTower = (ImageView) child;
                                            } else if (child.getId().equals("piece")) {
                                                ivPiece = (ImageView) child;
                                            }
                                        }

                                        //// Fadeout the killed piece to half transparent
                                        showKilledPieces = new Timeline();
                                        KeyValue transparentTower = new KeyValue(ivTower.opacityProperty(), 0.5);
                                        KeyValue opaqueTower = new KeyValue(ivTower.opacityProperty(), 1.0);
                                        KeyValue transparentPiece = new KeyValue(ivPiece.opacityProperty(), 0.5);
                                        KeyValue opaquePiece = new KeyValue(ivPiece.opacityProperty(), 1.0);

                                        //// Timelines
                                        showKilledPieces.getKeyFrames().addAll(
                                                new KeyFrame(Duration.ZERO, opaqueTower, opaquePiece),
                                                new KeyFrame(Duration.millis(250), transparentTower, transparentPiece),
                                                new KeyFrame(Duration.millis(1500), transparentTower, transparentPiece)
                                        );

                                        showKilledPieces.play();
                                        // Check whether the killed piece was the flag
                                        if (killedPiece instanceof Flag) {
                                            containsFlag = true;
                                        }
                                    }

                                    lastKilledPieces = killedPieces;

                                    // Check if the game ended or we need to switch to the next player
                                    if (containsFlag) {
                                        System.out.println("A flag was hit!");

                                        // Stop the game
                                        model.getGame().stop();
                                        System.out.println("The game was stopped");

                                        // Show the winner screen
                                        EndOfGameView eoGameView = new EndOfGameView();
                                        EndOfGamePresenter eoGamePresenter = new EndOfGamePresenter(model, eoGameView);

                                        Stage stage = new Stage();
                                        stage.setScene(new Scene(eoGameView));

                                        stage.setTitle("Einde spel");
                                        stage.initOwner(view.getScene().getWindow());
                                        stage.initModality(Modality.APPLICATION_MODAL);
                                        stage.showAndWait();

                                        Style.changeScreen(Style.Screens.MAINMENU, model, view);

                                    } else {
                                        System.out.println("No flag was hit");
                                        // Switch to next player
                                        if (!Objects.isNull(showKilledPieces)) {
                                            // Wait for the fade to finish before showing the overlay
                                            showKilledPieces.setOnFinished(actionEvent -> toggleNextPlayerOverlay());
                                        } else {
                                            toggleNextPlayerOverlay();
                                        }
                                    }

                                } catch (InvalidMoveException exception) {
                                    /// Place an X on the field
                                    //// Define X image
                                    ImageView ivInvalid = new ImageView("error.png");
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

                                    ivInvalid.setFitHeight(field.getFieldSize());
                                    ivInvalid.setFitWidth(field.getFieldSize());
                                    fieldPane.getChildren().add(ivInvalid);


                                    timeline.play();

                                    updateView();
                                }
                            }
                        }
                    });
                }
            }
        }

        // Click on overlay to pass to the next player
        view.getBtnNextPlayer().setOnAction(actionEvent -> {
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
                    StackPane killedPiecePane = fieldPanes.get(killedPiece.getField());
                    ImageView ivTower = null;
                    ImageView ivPiece = null;

                    for (Node child : killedPiecePane.getChildren()) {
                        if (child.getId().equals("tower")) {
                            ivTower = (ImageView) child;
                        } else if (child.getId().equals("piece")) {
                            ivPiece = (ImageView) child;
                        }
                    }

                    //// Make them vanish
                    KeyValue transparentTower = new KeyValue(ivTower.opacityProperty(), 0.0);
                    KeyValue opaqueTower = new KeyValue(ivTower.opacityProperty(), 0.5);
                    KeyValue transparentPiece = new KeyValue(ivPiece.opacityProperty(), 0.0);
                    KeyValue opaquePiece = new KeyValue(ivPiece.opacityProperty(), 0.5);

                    //// Timeline
                    Timeline fadeoutTimeline = new Timeline();
                    fadeoutTimeline.getKeyFrames().addAll(
                            new KeyFrame(Duration.ZERO, opaqueTower, opaquePiece),
                            new KeyFrame(Duration.millis(1750), opaqueTower, opaquePiece),
                            new KeyFrame(Duration.millis(2000), transparentTower, transparentPiece)
                    );

                    fadeoutTimeline.play();
                    fadeoutTimeline.setOnFinished(actionEvent1 -> {
                        killedPiece.finishKill();
                        killFadeOngoing = false;
                        updateView();
                    });
                }
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
                StackPane fieldPane = generatePane(field);
                fieldPanes.put(field, fieldPane);
                view.getGpBoard().add(fieldPane, field.getPositionX(), field.getPositionY());
            }
        }
        addEventHandlers();
    }

    public void addWindowEventHandlers() {

    }


    // Methods
    public StackPane generatePane(GameBoardField field) {
        // Generate the main stackpane
        StackPane container = new StackPane();

        container.setPrefSize(fieldSize, fieldSize);

        // Set the right background
        if (field.isWalkable()) {
            container.setBackground((field.isHighlighted()) ? Style.highlightedGrass : Style.grass);
        } else {
            container.setBackground(Style.water);
        }

        // If there's a piece on it, place it
        if (field.isOccupied()) {
            // Define the tower image
            String towerImage = (field.getPiece().getHidden() ? "/towerBackView.png" : "/towerFrontView.png");

            // Define the main imageView
            ImageView ivTower = new ImageView(towerImage);
            ivTower.setFitHeight(fieldSize * 0.95);
            ivTower.setFitWidth(fieldSize * 0.95);
            ivTower.setId("tower");
            if (field.getPiece().isDying()) {
                ivTower.setOpacity(0.5);
            }

            // Define the clip imageView
            ImageView ivClip = new ImageView(towerImage);
            ivClip.setFitHeight(fieldSize * 0.95);
            ivClip.setFitWidth(fieldSize * 0.95);

            // Set the image view clip
            ivTower.setClip(ivClip);

            ivTower.setEffect(new Blend(
                    BlendMode.MULTIPLY,
                    null,
                    new ColorInput(
                            0,
                            0,
                            ivTower.getImage().getWidth(),
                            ivTower.getImage().getHeight(),
                            Color.valueOf(field.getPiece().getPlayer().getColor())

                    )
            ));

            container.getChildren().add(ivTower);

            // If the piece is not hidden, add the icon
            if (!field.getPiece().getHidden()) {
                ImageView ivPiece = new ImageView(field.getPiece().getImage());
                ivPiece.setFitHeight(fieldSize * 0.4);
                ivPiece.setFitWidth(fieldSize * 0.4);
                ivPiece.setId("piece");
                if (field.getPiece().isDying()) {
                    ivPiece.setOpacity(0.5);
                }

                container.getChildren().add(ivPiece);
            }

        }

        return container;
    }

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
        Timeline timeline = new Timeline();
        KeyValue visableBtn = new KeyValue(btn.opacityProperty(), 1);
        KeyValue hiddenBtn = new KeyValue(btn.opacityProperty(), 0);
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, (nextPlayerOverlay ? hiddenBtn : visableBtn)),
                new KeyFrame(Duration.millis(500), (nextPlayerOverlay ? visableBtn : hiddenBtn))
        );
        timeline.play();

        //When turned off
        if (!nextPlayerOverlay) {
            timeline.setOnFinished(actionEvent -> btn.setVisible(false));
        }
    }
}



















