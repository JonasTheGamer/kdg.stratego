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
    private final double PLACABLE_PIECE_HEIGTH = 165;
    private final double PLACABLE_PIECE_WIDTH = 110;

    private ProgrammaModel model;
    private ArmySetupView view;
    private TreeMap<String, Integer> piecesToPlace;
    private HashMap<GameBoardField, Pane> fieldPanes;
    private String selectedPlaceablePiece;
    private Piece lastClickedPlaceablePiece;

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
            GameBoardField specialField = gb.getGameBoardField(gb.coordinateX(5), gb.coordinateY(4));
            if (Objects.isNull(specialField.getPiece()) && Objects.isNull(specialField.getFieldOnRight().getPiece())) {
                if (model.getGame().getCurrentPlayer().getPieceFromName("scout") != null && model.getGame().getCurrentPlayer().getPieceFromName("flag") != null) {

                    model.getGame().getCurrentPlayer().getPieceFromName("scout").placeOnField(specialField);
                    model.getGame().getCurrentPlayer().getPieceFromName("flag").placeOnField(specialField.getFieldOnRight());

                    refreshPlacablePieces();
                }
            }

            // Loop through placable pieces
            for (String pieceName : piecesToPlace.keySet()) {
                int amountPlacable = piecesToPlace.get(pieceName);

                for (int i = 0; i < amountPlacable; i++) {
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

        // Placable pieces
        ObservableList<Node> vbsPlacablePieces = view.getGpPieces().getChildren();
        for (Node vbPiece : vbsPlacablePieces) {
            vbPiece.setOnMouseClicked(mouseEvent -> {
                //Border selected placable piece (in updateview)
                if (vbPiece instanceof VBox) {
                    selectedPlaceablePiece = vbPiece.getId();
                }

                // Find out which piece the user would like to place on the field
                String clickedId = vbPiece.getId();
                String[] idData = clickedId.split("-");
                String pieceClassName = idData[1];

                // Make sure we still have pieces left to place
                int amountPlacable = piecesToPlace.get(clickedId);
                if (amountPlacable == 0) {
                    return;
                }

                // Get a random piece that has this name to place on the field later on
                lastClickedPlaceablePiece = model.getGame().getCurrentPlayer().getPieceFromName(pieceClassName);

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
                                Piece pieceToPlace = model.getGame().getCurrentPlayer().getPieceFromName(lastClickedPlaceablePiece.getName());

                                // Place on the field
                                pieceToPlace.placeOnField(field);

                                // Stop placing
                                int adjustedRank = 0;
                                if (pieceToPlace.getRank() > 0) {
                                    adjustedRank = pieceToPlace.getRank() - 1;
                                }
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

        // Refresh pieces that are placable
        refreshPlacablePieces();

        // Available Pieces
        int posXCounter = 0;
        int posYCounter = 0;

        view.getGpPieces().getChildren().clear();
        for (String pieceName : piecesToPlace.keySet()) {
            int amountPlacable = piecesToPlace.get(pieceName);
            /// Get a random piece that has this name. We'll use it to find out the image
            Piece piece = model.getGame().getCurrentPlayer().getPieceFromName(pieceName.split("-")[1]);

            /// Piece image
            ImageView ivPiece = new ImageView(piece.getImage());
            ivPiece.setPreserveRatio(true);
            ivPiece.setFitWidth(PLACABLE_PIECE_WIDTH * 0.5);

            /// Piece title
            Label lblPieceTitle = new Label(piece.getName());
            Style.txt(lblPieceTitle, 15);

            /// Amount left to place
            Label lblPlacable = new Label(Integer.toString(amountPlacable));
            Style.txt(lblPlacable, 15);

            /// Main piece container
            VBox pieceContainer = new VBox();
            pieceContainer.setBackground(Style.bgBoxNoPadding);
            pieceContainer.setAlignment(Pos.CENTER);
            pieceContainer.setPrefHeight(PLACABLE_PIECE_HEIGTH);
            pieceContainer.setPrefWidth(PLACABLE_PIECE_WIDTH);

            pieceContainer.getChildren().addAll(ivPiece, lblPieceTitle, lblPlacable);
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
    private void refreshPlacablePieces() {
        // Clear pieces to place
        piecesToPlace.clear();

        // Add the fresh ones in
        for (Piece piece : model.getGame().getCurrentPlayer().getPieces()) {
            // If the piece is already on the board, it's not placable anymore
            if (piece.isOnField()) {
                continue;
            }

            int adjustedRank = 0;
            if (piece.getRank() > 0) {
                adjustedRank = piece.getRank() - 1;
            }
            String pieceName = adjustedRank + "-" + piece.getName();

            // Figure out if there's already something placed
            int amountPlacable = 0;
            if (piecesToPlace.containsKey(pieceName)) {
                amountPlacable = piecesToPlace.get(pieceName);
            }
            piecesToPlace.put(pieceName, amountPlacable + 1);
        }
    }
}