package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class ProgrammaModel {
    // Constants
    private final double gameBoardFieldHeight;
    private final double gameBoardFieldWidth;

    private Game game;
    private GameBoard gameBoard;

    // Constructor
    public ProgrammaModel() {
        // Initialize player array
        game = new Game();

        // Set field height & width
        gameBoardFieldHeight = Style.size(50);
        gameBoardFieldWidth = Style.size(50);
    }

    public void createGameBoard() {
        this.gameBoard = new GameBoard(gameBoardFieldHeight, gameBoardFieldWidth);
    }

    // Getters & setters
    public Game getGame() {
        return game;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
