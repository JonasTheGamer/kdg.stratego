package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.GameBoard;
import be.kdg.stratego.model.GameBoardField;
import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.view.Style;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.Objects;

public class ArmySetupPresenter {
    private final double fieldSize = Style.size(55);
    private final double placeablePieceWidth = fieldSize * 2;
    private final double placeablePieceHeight = fieldSize * 2 * 1.5;


    private ProgrammaModel model;
    private ArmySetupView view;
    private HashMap<String, Integer> piecesToPlace;
    private HashMap<GameBoardField, StackPane> fieldPanes;

    private Boolean placingPiece = false;
    private Piece lastClickedPlaceablePiece;

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view) {
        this.model = model;
        this.view = view;
        this.piecesToPlace = new HashMap<>();
        this.fieldPanes = new HashMap<>();
        this.addEventHandlers();
        this.updateView();
    }

    private void addEventHandlers() {
        view.getBtnStart().setOnAction(actionEvent -> {

            // Check if the player has placed all pieces
            if (model.getGame().getCurrentPlayer().isPiecesPlaced()) {

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


        view.getBtnBack().setOnAction(actionEvent -> Style.changeScreen(Style.Screens.NEWGAME, model, view));


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
                    Piece piece = model.getGame().getCurrentPlayer().getPieceFromName(pieceName);

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
                lastClickedPlaceablePiece = model.getGame().getCurrentPlayer().getPieceFromName(pieceClassName);

                // Remember that the user is placing this piece.
                placingPiece = true;
            });
        }


        // Fields
        GameBoardField[][] fields = model.getGameBoard().getGameBoardFields();
        for (GameBoardField[] fieldColumn : fields) {
            for (GameBoardField field : fieldColumn) {
                StackPane fieldPane = fieldPanes.get(field);
                if (!Objects.isNull(fieldPane)) {
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
                                Piece pieceToPlace = model.getGame().getCurrentPlayer().getPieceFromName(lastClickedPlaceablePiece.getName());

                                // Place on the field
                                pieceToPlace.placeOnField(field);

                                // Stop placing
                                if (piecesToPlace.get(pieceToPlace.getName()) == 1) {
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
            Piece piece = model.getGame().getCurrentPlayer().getPieceFromName(pieceName);

            /// Piece image
            ImageView ivPiece = new ImageView(piece.getImage());
            ivPiece.setPreserveRatio(true);
            ivPiece.setFitWidth(placeablePieceWidth * 0.5);

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
            pieceContainer.setPrefHeight(placeablePieceHeight);
            pieceContainer.setPrefWidth(placeablePieceWidth);

            pieceContainer.getChildren().addAll(ivPiece, lblPieceTitle, lblPlacable);
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
                StackPane fieldPane = generatePane(field);
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

            String pieceName = piece.getName();

            // Figure out if there's already something placed
            int amountPlacable = 0;
            if (piecesToPlace.containsKey(pieceName)) {
                amountPlacable = piecesToPlace.get(pieceName);
            }
            piecesToPlace.put(pieceName, amountPlacable + 1);
        }

    }

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
                if (field.getPiece().isDying()) {
                    System.out.println("This piece is dying!");
                    ivPiece.setOpacity(0.5);
                } else {
                    //Jonas
                }

                container.getChildren().add(ivPiece);
            }

        }

        return container;
    }
}