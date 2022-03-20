package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Objects;

public class ArmySetupPresenter {
    private ProgrammaModel model;
    private ArmySetupView view;
    private HashMap<String, Integer> piecesToPlace;

    private Boolean placingPiece = false;
    private Piece lastClickedPlaceablePiece;

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view) {
        this.model = model;
        this.view = view;
        this.piecesToPlace = new HashMap<>();

        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnStart().setOnAction(actionEvent -> {
            // Check if the player has placed all pieces
            boolean foundPieceThatHasNoField = false;

            for (Piece piece : model.getGame().getCurrentPlayer().getPieces()) {
                // If the piece is already on the field, no need to figure everything out
                if (!piece.isOnField()) {
                    foundPieceThatHasNoField = true;
                }
            }

            if (foundPieceThatHasNoField) {
                return;
            }

            // Hide the current player's pieces
            model.getGame().getCurrentPlayer().hidePieces();

            // Flip the board
            model.getGameBoard().rotate();

            // Check if we're on the first (0) or second (1) player
            if (model.getGame().getCurrentPlayer().equals(model.getGame().getPlayers()[0])) {
                // Reload this windo for the next player
                model.getGame().switchPlayer();
                Style.changeScreen(Style.Screens.ARMYSETUP, model, view);
            } else {
                // Start the game!
                model.getGame().switchPlayer();
                Style.changeScreen(Style.Screens.BATTLEFIELD, model, view);
            }
        });


        view.getBtnBack().setOnAction(actionEvent -> {
            // Empty the board
            model.getGameBoard().clearGameBoardFields();

            // Switch back to the newGameView
            Style.changeScreen(Style.Screens.NEWGAME, model, view);
        });


        view.getBtnFill().setOnAction(actionEvent -> {

            // Loop through placable pieces
            for (String pieceName : piecesToPlace.keySet()) {
                int amountPlacable = piecesToPlace.get(pieceName);

                for (int i = 0; i < amountPlacable; i++) {
                    // Get a random piece that has this name
                    Piece piece = getPieceFromName(pieceName);

                    // Get the next unoccupied (available) field
                    GameBoardField field = getNextAvailableField();

                    // Place the piece on the field
                    piece.placeOnField(field);
                }
            }

            // Now, update the view
            updateView();
        });


        // Placable pieces
        ObservableList<Node> stpPlacablePieces = view.getGpPieces().getChildren();
        for (Node stpPiece : stpPlacablePieces) {
            stpPiece.setOnMouseClicked(mouseEvent -> {
                // Find out which piece the user would like to place on the field
                String clickedId = stpPiece.getId();
                String[] idData = clickedId.split("-");
                String pieceClassName = idData[1];

                // Make sure we still have pieces left to place
                int amountPlacable = piecesToPlace.get(pieceClassName);
                if (amountPlacable == 0) {
                    return;
                }

                // Get a random piece that has this name to place on the field later on
                Piece pieceToPlace = getPieceFromName(pieceClassName);
                lastClickedPlaceablePiece = pieceToPlace;

                // Remember that the user is placing this piece.
                placingPiece = true;
            });
        }


        // Fields
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();
        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                StackPane fieldPane = field.getPane();
                fieldPane.setOnMouseClicked(mouseEvent -> {

                    // PLACE - Check if we were placing a piece.
                    if (mouseEvent.getButton() == MouseButton.PRIMARY) {
                        if (placingPiece) {
                            // Only allow placing in row 6 to 9;
                            if (field.getPositionY() < (model.getGameBoard().getGrootteY() / 2) + 1 || field.getPositionY() > model.getGameBoard().getGrootteY() - 1) {
                                return;
                            }

                            // Check if the field is already occupied.
                            if (field.isOccupied()) {
                                return;
                            }

                            // Grab a random piece that's not on the field yet
                            Piece pieceToPlace = getPieceFromName(lastClickedPlaceablePiece.getClass().getName());

                            // Place on the field
                            pieceToPlace.placeOnField(field);

                            // Add it to the board
                            model.getGameBoard().setGameBoardField(field);

                            // Stop placing
                            if (piecesToPlace.get(pieceToPlace.getClass().getName()) == 1) {
                                placingPiece = false;
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

    private void updateView() {
        // Initialize variables
        double placeablePieceHeight = Style.size(100);
        double placeablePieceWidth = Style.size(75);

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
            Piece piece = getPieceFromName(pieceName);

            /// Main piece container
            VBox pieceContainer = new VBox();
            pieceContainer.setBackground(Style.background);
            pieceContainer.setAlignment(Pos.CENTER);
            pieceContainer.setPrefHeight(placeablePieceHeight);
            pieceContainer.setPrefWidth(placeablePieceWidth);

            /// Piece image
            ImageView ivPiece = new ImageView(piece.getImage());
            ivPiece.setFitWidth(placeablePieceWidth * 0.5);
            ivPiece.setFitHeight(placeablePieceWidth * 0.5);
            pieceContainer.getChildren().add(ivPiece);

            /// Piece title
            Label lblPieceTitle = new Label(piece.getName());
            Style.txt(lblPieceTitle, 15);
            pieceContainer.getChildren().add(lblPieceTitle);

            /// Amount left to place
            Label lblPlacable = new Label(Integer.toString(amountPlacable));
            Style.txt(lblPlacable, 15);
            pieceContainer.getChildren().add(lblPlacable);

            pieceContainer.setId("placablePiece-" + pieceName);

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
                StackPane fieldPane = field.getPane();
                view.getGpBoard().add(fieldPane, field.getPositionX(), field.getPositionY());
            }
        }

        addEventHandlers();
    }

    public void addWindowEventHandlers() {

    }

    //Methoden
    private Piece getPieceFromName(String name) {
        Piece foundPiece = null;
        for (Piece piece : model.getGame().getCurrentPlayer().getPieces()) {
            if (piece.getClass().getName().equals(name) && Objects.isNull(piece.getField())) {
                foundPiece = piece;
                break;
            }
        }

        return foundPiece;
    }

    private void refreshPlacablePieces() {
        // Clear pieces to place
        piecesToPlace.clear();

        // Add the fresh ones in
        for (Piece piece : model.getGame().getCurrentPlayer().getPieces()) {
            // If the piece is already on the board, it's not placable anymore
            if (piece.isOnField()) {
                continue;
            }

            String pieceName = piece.getClass().getName();

            // Figure out if there's already something placed
            int amountPlacable = 0;
            if (piecesToPlace.containsKey(pieceName)) {
                amountPlacable = piecesToPlace.get(pieceName);
            }
            piecesToPlace.put(pieceName, amountPlacable + 1);
        }

    }

    private GameBoardField getNextAvailableField() {
        int boardSizeX = model.getGameBoard().getGrootteX();
        int boardSizeY = model.getGameBoard().getGrootteX();

        int currBoardPosX = 0;
        int currBoardPosY = 0;

        GameBoardField availableField = null;
        for (int posY = 0; posY < boardSizeY; posY++) {
            for (int posX = 0; posX < boardSizeX; posX++) {
                GameBoardField fieldOnThisPosition = model.getGameBoard().getGameBoardField(posX, posY);
                if (!fieldOnThisPosition.isOccupied()) {
                    availableField = fieldOnThisPosition;
                    break;
                }
            }
        }

        return availableField;
    }


}
