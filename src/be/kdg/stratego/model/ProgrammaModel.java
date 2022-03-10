package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.scene.paint.Color;

public class ProgrammaModel {
    // Constants
    private final double gameBoardFieldHeight;
    private final double gameBoardFieldWidth;
    // Players
    private Player[] players;
    private GameBoard gameBoard;

    // Constructor
    public ProgrammaModel() {
        // Initialize player array
        players = new Player[2];

        // Set field height & width
        gameBoardFieldHeight = Style.size(50);
        gameBoardFieldWidth = Style.size(50);
    }

    public void createPlayer(int index, String name, Color color, String flag) {
        players[index] = null;
        players[index] = new Player(name, color);
        players[index].givePieces(flag);
    }

    public void createGameBoard() {
        this.gameBoard = new GameBoard(gameBoardFieldHeight, gameBoardFieldWidth);
    }

    // Getters & setters
    public Player[] getPlayers() {
        return players;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
