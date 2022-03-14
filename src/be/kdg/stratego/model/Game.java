package be.kdg.stratego.model;

import be.kdg.stratego.customUtil.AskUtility;
import be.kdg.stratego.view.Style;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

public class Game {
    private final double gameBoardFieldHeight = Style.size(50);
    private final double gameBoardFieldWidth = Style.size(50);

    private Player[] players;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean ongoing = false;
    private GameBoard gameBoard;

    public Game() {
        this.players = new Player[2];
        this.gameBoard = new GameBoard(gameBoardFieldHeight, gameBoardFieldWidth);
    }

    // Methods
    public void start() {
        ongoing = true;
        this.startTime = LocalDateTime.now();
    }

    public boolean save() {
        // Check to allow for a loop that keeps asking for a valid file location
        boolean fileOk = false;
        boolean fileSaved = false;

        // Check if the game has already been started
        if (!ongoing) {
            System.out.println("The game has to be started before you can save its progress.");
            return false;
        }

        // Initialize file path
        String filePath = "";

        // Find out which file we need to save to
        while (!fileOk) {
            // Ask for the the file location where we should save the game's progress (later on this will be done with a dialogbox)
            filePath = AskUtility.askString("Where do you want to save the progress? (please include a .txt filename)", true);
            System.out.println("Alright, saving to " + filePath + " .");

            // Create the file. If it already exists, ask if we can overwrite it.
            try {
                File myObj = new File(filePath);
                if (myObj.createNewFile()) {
                    // File was successfully created
                    fileOk = true;
                } else {
                    // File already exists. Ask user if they want to overwrite it.
                    fileOk = AskUtility.askBoolean("The file already exists. Do you want to overwrite it? (yes, no)", true);
                }
            } catch (IOException e) {
                System.out.println("An error occurred whilst trying to check if the file already exists. Please make sure the path is valid.");
                fileOk = false;
            }
        }

        // Let the saving begin!
        try {
            PrintWriter writer = new PrintWriter(filePath, StandardCharsets.UTF_8);
            writer.println("Hello, world!");
            writer.println("The writing works, woohoo!");
            writer.close();

            System.out.println("The game's progress has been saved successfully. Congratulations!");
            fileSaved = true;

        } catch (FileNotFoundException | UnsupportedEncodingException exc) {
            System.out.println("Something went wrong whilst trying to save your progress. Please try again at a later time.");
            fileSaved = false;
        } catch (IOException e) {
            e.printStackTrace();
            fileSaved = false;
        }

        if (fileSaved) {
            // Stop the game when it's saved.
            this.stop();
        }
        return fileSaved;
    }

    public void continueSave(GameBoard speelbord) {
        // Resume the timer
    }

    public void stop() {
        ongoing = false;
        this.endTime = LocalDateTime.now();
        System.out.println("The game has been stopped.");
    }

    // Getters
    public Player[] getPlayers() {
        return players;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    // Setters
    public void setPlayer1(Player player) {
        this.players[0] = player;
    }

    public void setPlayer2(Player player) {
        this.players[1] = player;
    }
}
