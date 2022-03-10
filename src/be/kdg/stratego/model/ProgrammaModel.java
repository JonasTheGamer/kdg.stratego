package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;
import be.kdg.stratego.view.Style;
import javafx.scene.paint.Color;

public class ProgrammaModel {
    // Constants
    private final double fieldHeight = Style.size(50);
    private final double fieldWidth = Style.size(50);
    // Players
    private Player[] players;
    private GameBoard gameBoard;

    // Constructor
    public ProgrammaModel() {
        // Initialize player array
        players = new Player[2];
    }

    public void createPlayer(int index, String name, Color color, String flag) {
        players[index] = null;
        players[index] = new Player(name, color);
        players[index].givePieces(flag);
    }

    public void createGameBoard() {
        this.gameBoard = new GameBoard();
        this.InitializeBoard();
    }

    private void InitializeBoard() {
        int boardWidth = gameBoard.getGrootteX();
        int boardHeight = gameBoard.getGrootteY();

        int amountOfRowsPerPlayer = (boardHeight - 2) / 2;

        //// Add empty rows on top
        for (int posY = 0; posY < amountOfRowsPerPlayer; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setGameBoardView(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }

        //// Add paths & swamps
        ////// Paths
        for (int posY = amountOfRowsPerPlayer; posY < amountOfRowsPerPlayer + 2; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setGameBoardView(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }


        //// Swamps
        ////// Left swamp
        gameBoard.setGameBoardView(new GameBoardField("swampx2y4", 2, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 3, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 2, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 3, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        ////// Right swamp
        gameBoard.setGameBoardView(new GameBoardField("swamp", 6, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 7, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 6, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setGameBoardView(new GameBoardField("swamp", 7, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        //// Add empty rows on the bottom
        for (int posY = amountOfRowsPerPlayer + 2; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setGameBoardView(new GameBoardField("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }
    }

    // Getters & setters
    public Player[] getPlayers() {
        return players;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
