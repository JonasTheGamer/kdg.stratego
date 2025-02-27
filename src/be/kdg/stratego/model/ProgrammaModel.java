/*
    Class: Programmamodel
    Responsiblity: The heart of our application
 */
package be.kdg.stratego.model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.TreeSet;

public class ProgrammaModel {
    private final File LEADERBOARD_FILE = new File("resources/leaderboard.csv");

    private Game game;
    private GameBoard gameBoard;
    private TreeSet<Highscore> highscores;
    private TreeSet<Lowturn> lowturns;

    // Constructor
    public ProgrammaModel() {
        highscores = new TreeSet<>();
        lowturns = new TreeSet<>();
    }

    // Methoden
    public void newGame(Player player1, Player player2) {
        game = new Game(player1, player2);
        gameBoard = game.getGameBoard();
    }

    public void updateLeaderboard() throws IOException {
        /// Clearing lists
        highscores.clear();
        lowturns.clear();

        /// Read lines from file
        List<String> lines = Files.readAllLines(this.LEADERBOARD_FILE.toPath());

        for (String currentLine : lines) {
            if (!currentLine.equals("")) {
                String spelernaam = currentLine.split(";")[0];
                int score = Integer.parseInt(currentLine.split(";")[1]);
                int turns = Integer.parseInt(currentLine.split(";")[2]);

                highscores.add(new Highscore(spelernaam, score));
                lowturns.add(new Lowturn(spelernaam, turns));
            }
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

    public TreeSet<Lowturn> getLowturns() {
        return lowturns;
    }

    public File getLeaderboardFile() {
        return LEADERBOARD_FILE;
    }
}
