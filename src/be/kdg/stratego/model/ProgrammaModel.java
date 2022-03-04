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
        int boardWidth = gameBoard.getGrootteX();
        int boardHeight = gameBoard.getGrootteY();

        int amountOfRowsPerPlayer = (boardHeight -2 ) / 2;

        //// Add empty rows on top
        for (int posY = 0; posY < amountOfRowsPerPlayer; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("unknown",posX, posY, FieldType.unknownField(fieldHeight, fieldWidth), true));
            }
        }

        //// Add paths & swamps
        ////// Paths
        for (int posY = amountOfRowsPerPlayer; posY < amountOfRowsPerPlayer + 2; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("grass",posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }


        //// Swamps
        ////// Left swamp
        gameBoard.setSpeelveld(new Speelveld("swampx2y4",2, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",3, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",2, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",3, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), false));

        ////// Right swamp
        gameBoard.setSpeelveld(new Speelveld("swamp",6, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",7, amountOfRowsPerPlayer, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",6, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp",7, amountOfRowsPerPlayer + 1, FieldType.swamp(fieldHeight, fieldWidth), false));

        //// Add empty rows on the bottom
        for (int posY = amountOfRowsPerPlayer + 2; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("grass",posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }
    }
}
