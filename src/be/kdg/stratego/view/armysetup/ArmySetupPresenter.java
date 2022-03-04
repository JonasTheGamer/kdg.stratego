package be.kdg.stratego.view.armysetup;

import be.kdg.stratego.model.Piece;
import be.kdg.stratego.model.Player;
import be.kdg.stratego.model.ProgrammaModel;
import be.kdg.stratego.model.Speelveld;
import be.kdg.stratego.view.Style;
import be.kdg.stratego.view.newgame.NewGamePresenter;
import be.kdg.stratego.view.newgame.NewGameView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.HashMap;

public class ArmySetupPresenter {
    private ProgrammaModel model;
    private ArmySetupView view;
    private HashMap<String, Integer> piecesToPlace = new HashMap();
    private Player currentPlayer;

    private EventHandler onFieldClick = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent) {
            // Find out which of the fields actually got pressed. All we need to know is the coordinates.
            StackPane clickedButton = (StackPane) mouseEvent.getSource();
            String clickedId = clickedButton.getId();
            String[] idData = clickedId.split("-");

            int btnCol = Integer.parseInt(idData[1]);
            int btnRow = Integer.parseInt(idData[2]);

            // Currently, for debugging purposes, send an alert showing what the location is.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setTitle("Ding dong!");
            alert.setHeaderText(null);
            alert.setContentText("You clicked on me! My location is: \n\nX: " + btnCol + "\nY: " + btnRow + " and my type is " + idData[0]);
            alert.initOwner(view.getScene().getWindow());

            alert.showAndWait();
        }
    };

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view, Player currentPlayer) {
        this.model = model;
        this.view = view;
        this.currentPlayer = currentPlayer;

        // Setup piecesToPlace
        piecesToPlace.put("Bomb",6);
        piecesToPlace.put("Marshal",1);
        piecesToPlace.put("General",1);
        piecesToPlace.put("Colonel",2);
        piecesToPlace.put("Major",3);
        piecesToPlace.put("Captain",4);
        piecesToPlace.put("Lieutenant",4);
        piecesToPlace.put("Sergeant",4);
        piecesToPlace.put("Miner",5);
        piecesToPlace.put("Scout",8);
        piecesToPlace.put("Spy",1);
        piecesToPlace.put("Flag",1);

        this.addEventHandlers();
        this.updateView();
    }

    public ArmySetupPresenter(ProgrammaModel model, ArmySetupView view) {
        this(model, view, new Player("Test player 1", Color.WHITE));
    }
    private void addEventHandlers() {
        // Code
        view.getBtnBack().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NewGameView newGameView = new NewGameView();
                NewGamePresenter newGamePresenter = new NewGamePresenter(model, newGameView);
                view.getScene().setRoot(newGameView);
            }
        });

    }

    private void updateView() {
        // Initialize variables
        int placeablePieceHeight = 120;
        int placeablePieceWidth = 90;

        int boardWidth = model.getGameBoard().getGrootteX();
        int boardHeight = model.getGameBoard().getGrootteY();
        int amountOfRowsPerPlayer = (model.getGameBoard().getGrootteY() -2 ) / 2;

        // Set title
        view.getLblScreenTitle().setText(currentPlayer.getName() + ": Place your army");

        // Figure out which pieces can be placed
        //// Clear pieces to place
        piecesToPlace.clear();

        //// Add the fresh ones in
        for (Piece piece : currentPlayer.getPieces()) {
            // If the piece is already on the field, no need to figure everything out
            if(piece.isOnField()) {
                continue;
            }

            String pieceName = piece.getClass().getName();

            // Figure out if there's already something placed
            int amountPlacable = 0;
            if(piecesToPlace.containsKey(pieceName)) {
                amountPlacable = piecesToPlace.get(pieceName);
            }
            piecesToPlace.put(pieceName, amountPlacable + 1);
        }


        // Add them to the gridPane
        int posXCounter = 0;
        int posYCounter = 0;

        for (String pieceName : piecesToPlace.keySet()) {
            int amountPlacable = piecesToPlace.get(pieceName);
            // Get a random piece that has this name. We'll use it to find out the image
            Piece piece = getPieceFromName(pieceName);

            // Main piece container
            VBox pieceContainer = new VBox();
            pieceContainer.setBackground(Style.background);
            pieceContainer.setAlignment(Pos.CENTER);
            pieceContainer.setPrefHeight(placeablePieceHeight);
            pieceContainer.setPrefWidth(placeablePieceWidth);

            // Piece image
            ImageView ivPiece = new ImageView(piece.getImage());
            ivPiece.setFitWidth(placeablePieceWidth * 0.5);
            ivPiece.setFitHeight(placeablePieceWidth * 0.5);
            pieceContainer.getChildren().add(ivPiece);

            // Piece title
            Label lblPieceTitle = new Label(piece.getName());
            Style.txt(lblPieceTitle, 15);
            pieceContainer.getChildren().add(lblPieceTitle);

            // Amount left to place
            Label lblPlacable = new Label(Integer.toString(amountPlacable));
            Style.txt(lblPlacable, 15);
            pieceContainer.getChildren().add(lblPlacable);


            view.getGpPieces().add(pieceContainer, posXCounter, posYCounter);

            posXCounter++;

            if (posXCounter > 2) {
                posXCounter -= 3;
                posYCounter++;
            }
        }


        // Game board
        //// Empty game board
        view.getGpBoard().getChildren().clear();

        //// Fill the board & add them to the gridpane
        Speelveld[][] fields = model.getGameBoard().getSpeelvelden();
        for (int posX = 0; posX < model.getGameBoard().getGrootteX(); posX++) {
            for (int posY = 0; posY < model.getGameBoard().getGrootteY(); posY++) {
                Speelveld field = fields[posX][posY];
                field.getType().setId(field.getTypeName() + "-" + field.getPositionX() + "-" + field.getPositionY());

                field.getType().setOnMouseClicked(onFieldClick);
                view.getGpBoard().add(field.getType(), field.getPositionX(), field.getPositionY());
            }
        }


    }

    public void addWindowEventHandlers() {

    }

    public Piece getPieceFromName(String name) {
        Piece foundPiece = null;
        for (Piece piece: currentPlayer.getPieces()) {
            if(piece.getClass().getName().equals(name)) {
                foundPiece = piece;
                break;
            }
        }

        return foundPiece;
    }
}
