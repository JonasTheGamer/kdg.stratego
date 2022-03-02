package be.kdg.stratego.model;

import java.util.ArrayList;

public class ProgrammaModel {
    private ArrayList<Piece> gamePieces = new ArrayList();
    private GameBoard gameBoard;

    public ProgrammaModel() {
        // Make a game board
        gameBoard = new GameBoard();



    }

    public ArrayList<Piece> getGamePieces() {
        return gamePieces;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
