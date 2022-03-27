package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.GameBoard;
import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.Board;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class ArmySetupPresenter {
    private final double FIELD_SIZE = Style.size(55);
    private final double PLACEABLE_PIECE_HEIGTH = 165;
    private final double PLACEABLE_PIECE_WIDTH = 110;

    private ProgrammaModel model;
    private ArmySetupView view;

    private TreeMap<String, Integer> piecesToPlace; //Amount of pieces per type
    private HashMap<GameBoardField, Pane> fieldPanes; //Gameboardfields and linked Panes
    private String selectedPlaceablePiece; //Holds id of Vbox placeable piece

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view) {
        this.model = model;
        this.view = view;
        this.piecesToPlace = new TreeMap<>();
        this.fieldPanes = new HashMap<>();
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        // Start button
        view.getBtnStart().setOnAction(actionEvent -> {

            // Check if the player has placed all pieces
            if (model.getGame().getCurrentPlayer().isPiecesPlaced()) {
                // Unselect PlaceablePiece before switching players
                selectedPlaceablePiece = null;

                // Check if we're on the first (0) or second (1) player
                if (model.getGame().getCurrentPlayer().equals(model.getGame().getPlayers()[0])) {
                    // Next turn
                    model.getGame().nextTurn();
                    view.getBtnStart().setText("Start");
                    updateView();
                } else {
                    // Start the game!
                    model.getGame().nextTurn();
                    Style.changeScreen(Style.Screens.BATTLEFIELD, model, view);
                }
            }
        });

        // Back button
        view.getBtnBack().setOnAction(actionEvent -> Style.changeScreen(Style.Screens.NEWGAME, model, view));

        // Fill button
        view.getBtnFill().setOnAction(actionEvent -> {

            // Special places pieces (for quick winning, ...)
            GameBoard gb = model.getGameBoard();
            GameBoardField specialField = gb.getGameBoardField(gb.coordinateX(1), gb.coordinateY(4));
            if (!specialField.isOccupied() && !specialField.getFieldOnRight().isOccupied()) {
                // Check if there's still a scout and a flag available
                if (model.getGame().getCurrentPlayer().getPieceFromName("scout") != null && model.getGame().getCurrentPlayer().getPieceFromName("flag") != null) {

                    model.getGame().getCurrentPlayer().getPieceFromName("spy").placeOnField(specialField);
                    model.getGame().getCurrentPlayer().getPieceFromName("miner").placeOnField(specialField.getFieldOnRight());

                    specialField = gb.getGameBoardField(gb.coordinateX(5), gb.coordinateY(4));
                    model.getGame().getCurrentPlayer().getPieceFromName("scout").placeOnField(specialField);
                    model.getGame().getCurrentPlayer().getPieceFromName("flag").placeOnField(specialField.getFieldOnRight());

                    specialField = gb.getGameBoardField(gb.coordinateX(9), gb.coordinateY(4));
                    model.getGame().getCurrentPlayer().getPieceFromName("bomb").placeOnField(specialField);
                    model.getGame().getCurrentPlayer().getPieceFromName("marshal").placeOnField(specialField.getFieldOnRight());

                    specialField = gb.getGameBoardField(gb.coordinateX(10), gb.coordinateY(3));
                    model.getGame().getCurrentPlayer().getPieceFromName("scout").placeOnField(specialField);
                    specialField = gb.getGameBoardField(gb.coordinateX(1), gb.coordinateY(3));
                    model.getGame().getCurrentPlayer().getPieceFromName("scout").placeOnField(specialField);

                    refreshPlaceablePieces();
                }
            }

            // Loop through placeable pieces
            for (String pieceName : piecesToPlace.keySet()) {
                int amountPlaceable = piecesToPlace.get(pieceName);

                for (int i = 0; i < amountPlaceable; i++) {
                    // Get a random piece that has this name
                    Piece piece = model.getGame().getCurrentPlayer().getPieceFromName(pieceName.split("-")[1]);

                    // Get the next unoccupied (available) field
                    GameBoardField field = model.getGameBoard().getNextAvailableField();

                    // Place the piece on the field
                    piece.placeOnField(field);
                }
            }

            // Now, update the view
            updateView();
        });

        // Placeable pieces
        ObservableList<Node> vbsPlaceablePieces = view.getGpPieces().getChildren();
        for (Node vbPiece : vbsPlaceablePieces) {
            vbPiece.setOnMouseClicked(mouseEvent -> {
                //Border selected placeable piece (in updateview)
                if (vbPiece instanceof VBox) {
                    selectedPlaceablePiece = vbPiece.getId();
                }

                // Find out which piece the user would like to place on the field
                String clickedId = vbPiece.getId();

                // Make sure we still have pieces left to place
                int amountPlaceable = piecesToPlace.get(clickedId);
                if (amountPlaceable == 0) {
                    return;
                }

                //Update view
                updateView();
            });
        }

        // Fields
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();
        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                Pane fieldPane = fieldPanes.get(field);
                if (!Objects.isNull(fieldPane)) {
                    fieldPane.setOnMouseClicked(mouseEvent -> {

                        // PLACE - Check if we were placing a piece.
                        if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                            if (!Objects.isNull(selectedPlaceablePiece)) {
                                // Only allow placing in row 6 to 9;
                                if (field.getPositionY() < (model.getGameBoard().getGROOTTE_Y() / 2) + 1 || field.getPositionY() > model.getGameBoard().getGROOTTE_Y() - 1) {
                                    return;
                                }

                                // Check if the field is already occupied.
                                if (field.isOccupied()) {
                                    return;
                                }

                                // Grab a random piece that's not on the field yet
                                Piece pieceToPlace = model.getGame().getCurrentPlayer().getPieceFromName(selectedPlaceablePiece.split("-")[1]);

                                // Place on the field
                                pieceToPlace.placeOnField(field);

                                // Stop placing
                                int adjustedRank = 0;
                                if (pieceToPlace.getRank() > 0) {
                                    adjustedRank = pieceToPlace.getRank() - 1;
                                }

                                // Check if this was the last piece of this piece type that could be placed
                                if (piecesToPlace.get(adjustedRank + "-" + pieceToPlace.getName()) == 1) {
                                    selectedPlaceablePiece = null;
                                }

                                // Refresh the view
                                updateView();
                            }
                        }


                        // REMOVE - Check if player wants to remove a piece from the board
                        if (mouseEvent.getButton() == MouseButton.SECONDARY) {

                            // Only remove if the field is occupied & the piece is not hidden
                            if (field.isOccupied() && !field.getPiece().getHidden()) {
                                // Remove the piece from the field
                                field.getPiece().removeFromField();

                                // Refresh the view
                                updateView();
                            }
                        }
                    });
                }
            }
        }
    }

    private void updateView() {
        // Set title
        view.getLblScreenTitle().setText(model.getGame().getCurrentPlayer().getName() + ": Place your army");

        // Refresh pieces that are placeable
        refreshPlaceablePieces();

        // Available Pieces
        int posXCounter = 0;
        int posYCounter = 0;

        view.getGpPieces().getChildren().clear();
        for (String pieceName : piecesToPlace.keySet()) {
            int amountPlaceable = piecesToPlace.get(pieceName);
            /// Get a random piece that has this name. We'll use it to find out the image
            Piece piece = model.getGame().getCurrentPlayer().getPieceFromName(pieceName.split("-")[1]);

            /// Piece image
            ImageView ivPiece = new ImageView(piece.getImage());
            ivPiece.setPreserveRatio(true);
            ivPiece.setFitWidth(PLACEABLE_PIECE_WIDTH * 0.5);

            /// Piece title
            Label lblPieceTitle = new Label(piece.getName());
            Style.txt(lblPieceTitle, 15);

            /// Amount left to place
            Label lblPlaceable = new Label(Integer.toString(amountPlaceable));
            Style.txt(lblPlaceable, 15);

            /// Main piece container
            VBox pieceContainer = new VBox();
            pieceContainer.setBackground(Style.bgBoxNoPadding);
            pieceContainer.setAlignment(Pos.CENTER);
            pieceContainer.setPrefHeight(PLACEABLE_PIECE_HEIGTH);
            pieceContainer.setPrefWidth(PLACEABLE_PIECE_WIDTH);

            pieceContainer.getChildren().addAll(ivPiece, lblPieceTitle, lblPlaceable);
            pieceContainer.setId(pieceName);

            //// Border selected Placeable piece
            if (pieceContainer.getId().equals(selectedPlaceablePiece)){
                pieceContainer.setBorder(Style.border(Color.RED, 20));
            }

            view.getGpPieces().add(pieceContainer, posXCounter, posYCounter);

            posXCounter++;

            if (posXCounter > 2) {
                posXCounter -= 3;
                posYCounter++;
            }
        }

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

    //Methods
    private void refreshPlaceablePieces() {
        // Clear pieces to place
        piecesToPlace.clear();

        // Add the fresh ones in
        for (Piece piece : model.getGame().getCurrentPlayer().getPieces()) {
            // If the piece is already on the board, it's not placeable anymore
            if (piece.isOnField()) {
                continue;
            }

            int adjustedRank = 0;
            if (piece.getRank() > 0) {
                adjustedRank = piece.getRank() - 1;
            }
            String pieceName = adjustedRank + "-" + piece.getName();

            // Figure out if there's already a piece of this type in the piecesToPlace keyset
            // and calculate placeable amount
            int amountPlaceable = 0;
            if (piecesToPlace.containsKey(pieceName)) {
                amountPlaceable = piecesToPlace.get(pieceName);
            }
            piecesToPlace.put(pieceName, amountPlaceable + 1);
        }
    }
}