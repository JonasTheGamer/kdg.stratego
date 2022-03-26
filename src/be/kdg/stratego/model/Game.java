/*
    Class: Game
    Responsiblity: Takes care of the game itself
 */

package be.kdg.stratego.model;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Game {
    private Player[] players;
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
    }

    public void stop(File winnersFile) {
        ongoing = false;

        // Save the winner
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
