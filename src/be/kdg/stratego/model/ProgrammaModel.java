package be.kdg.stratego.model;

import be.kdg.stratego.view.Style;
import javafx.scene.paint.Color;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ProgrammaModel {
    private Game game;
    private Highscore[] highscore;

    // Constructor
    public ProgrammaModel() {
        game = new Game();
    }

    // Methoden

    // Getters & setters
    public Game getGame() {
        return game;
    }

    public GameBoard getGameBoard() {
        return game.getGameBoard();
    }
}
