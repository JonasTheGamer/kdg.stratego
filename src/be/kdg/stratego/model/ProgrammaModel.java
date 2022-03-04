package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;

import java.util.ArrayList;

public class ProgrammaModel {
    private ArrayList<Piece> gamePieces = new ArrayList();
    private GameBoard gameBoard;

    // Constants
    private final int fieldHeight = 75;
    private final int fieldWidth = 75;

    public ProgrammaModel() {
        // Make a game board
        gameBoard = new GameBoard();

        // Fill board
        this.InitializeBoard();


    }

    public ArrayList<Piece> getGamePieces() {
        return gamePieces;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    private void InitializeBoard() {
        int fieldWidth = gameBoard.getGrootteX();
        int fieldHeight = gameBoard.getGrootteY();

        int amountOfRowsPerPlayer = (fieldHeight -2 ) / 2;

        //// Add empty rows on top
        for (int posX = 0; posX < amountOfRowsPerPlayer; posX++) {
            // Per row
            for (int posY = 0; posY < fieldWidth; posY++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld(posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }

        //// Add paths & swamps
        ////// Paths
        /////// Left path
        gameBoard.setSpeelveld(new Speelveld(0, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(1, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(0, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(1, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));

        /////// Middle path
        gameBoard.setSpeelveld(new Speelveld(4, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(5, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(4, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(5, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));

        /////// Right path
        gameBoard.setSpeelveld(new Speelveld(8, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(9, amountOfRowsPerPlayer, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(8, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(9, amountOfRowsPerPlayer + 1, FieldType.grass(fieldHeight, fieldWidth), true));

        //// Swamps
        ////// Left swamp
        gameBoard.setSpeelveld(new Speelveld(2, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(3, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(2, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(3, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), true));

        ////// Right swamp
        gameBoard.setSpeelveld(new Speelveld(6, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(7, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(6, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), true));
        gameBoard.setSpeelveld(new Speelveld(7, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), true));

        //// Add empty rows on the bottom
        for (int posX = amountOfRowsPerPlayer + 2; posX < gameBoard.getGrootteY(); posX++) {
            // Per row
            for (int posY = 0; posY < fieldWidth; posY++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld(posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }
    }
}
