package be.kdg.stratego.model;

import be.kdg.stratego.customUtil.AskUtility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {
    private Player[] players;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean ongoing = false;
    private GameBoard gameBoard;
    private Player currentPlayer;
    private Player nextPlayer;

    public Game(Player player1, Player player2) {
        this.players = new Player[]{player1, player2};
        this.currentPlayer = player1;
        this.nextPlayer = player2;
        this.gameBoard = new GameBoard();

        // Make their pieces visible
        currentPlayer.showPieces();
    }

    // Methods
    public void start() {
        //Set starting variables
        this.ongoing = true;
        this.startTime = LocalDateTime.now();
    }

    public boolean save(File winnersFile) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (fileSaved) {
            // Stop the game when it's saved.
            this.stop(winnersFile);
        }
        return fileSaved;
    }

    public void continueSave(GameBoard speelbord) {
        // Resume the timer
    }

    public void stop(File winnersFile) {
        ongoing = false;
        this.endTime = LocalDateTime.now();

        // Calculate the first score (sum of pieces left)
        int piecesSum = calculateLeftOverPiecesScore();

        // Second high score (amount of turns needed)
        currentPlayer.getAmountOfTurns();

        // Save the highscores
        registerWinner(winnersFile);
    }

    public void nextTurn() {
        if (ongoing) {
            nextPlayer.addTurn();
        }

        // Rotate gameboard
        gameBoard.rotate();

        // Hide the current player's pieces
        currentPlayer.hidePieces();

        // Switch player
        if (currentPlayer.equals(players[0])) {
            currentPlayer = players[1];
            nextPlayer = players[0];
        } else {
            currentPlayer = players[0];
            nextPlayer = players[1];
        }

        currentPlayer.showPieces();
    }

    public int calculateLeftOverPiecesScore() {
        int piecesSum = 0;
        for (Piece pieceOnBoard : currentPlayer.getPieces()) {
            if(pieceOnBoard.isOnField()) {
                piecesSum += pieceOnBoard.getRank();
            }
        }

        return piecesSum;
    }

    // Getters
    public Player[] getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player getNextPlayer() {
        return nextPlayer;
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void registerWinner(File winnersFile) {
        ArrayList<String> lines = new ArrayList<>();

        /// Add line to list
        lines.add(currentPlayer.getName() + ";" + calculateLeftOverPiecesScore() + ";" + currentPlayer.getAmountOfTurns());

        /// Write lines to file
        try {
            Files.write(winnersFile.toPath(), lines, StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
