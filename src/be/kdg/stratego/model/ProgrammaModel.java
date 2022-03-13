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
    private TreeSet<Highscore> highscores;

    // Constructor
    public ProgrammaModel() {
        game = new Game();
        highscores = new TreeSet<Highscore>();
        updateHighscores();
    }

    // Methoden
    public void setNewHighscore(String name, int score) {
        ArrayList<String> lines = new ArrayList<String>();

        /// Add line to list
        lines.add(name + ";" + score);

        /// Write lines to file
        try {
            Files.write(this.highscoreFile.toPath(), lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateHighscores() {
        /// Deleting duplicates
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

    // Getters & setters
    public Game getGame() {
        return game;
    }

    public GameBoard getGameBoard() {
        return game.getGameBoard();
    }

    public TreeSet<Highscore> getHighscores() {
        return highscores;
    }
}
