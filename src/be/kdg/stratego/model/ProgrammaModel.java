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
        this.gameBoard = new GameBoard(fieldHeight, fieldWidth);
    }

    // Getters & setters
    public Player[] getPlayers() {
        return players;
    }
    public GameBoard getGameBoard() {
        return gameBoard;
    }
}
