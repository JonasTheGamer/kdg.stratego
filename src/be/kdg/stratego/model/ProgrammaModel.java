package be.kdg.stratego.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class ProgrammaModel {
    private final File highscoreFile = new File("highscores.csv");

    private Game game;
    private GameBoard gameBoard;
    private TreeSet<Highscore> highscores;

    // Constructor
    public ProgrammaModel() {
        highscores = new TreeSet<>();
    }

    // Methoden
    public void newGame(Player player1, Player player2) {
        game = new Game(player1, player2);
        gameBoard = game.getGameBoard();
    }

    public void updateHighscores() {
        /// Clearing list
        highscores.clear();

        /// Read lines from file
        try {
            List<String> lines = Files.readAllLines(this.highscoreFile.toPath());

            for (String currentLine : lines) {
                String spelernaam = currentLine.split(";")[0];
                int score = Integer.parseInt(currentLine.split(";")[1]);

                highscores.add(new Highscore(spelernaam, score));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Getters
    public Game getGame() {
        return game;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public TreeSet<Highscore> getHighscores() {
        return highscores;
    }

    // Setter
    public void setNewHighscore(String name, int score) {
        ArrayList<String> lines = new ArrayList<>();

        /// Add line to list
        lines.add(name + ";" + score);

        /// Write lines to file
        try {
            Files.write(this.highscoreFile.toPath(), lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
