package be.kdg.stratego.model;

import be.kdg.stratego.view.FieldType;
import be.kdg.stratego.view.Style;
import javafx.scene.paint.Color;

public class ProgrammaModel {
    // Constants
    private final double fieldHeight = Style.size(50);
    private final double fieldWidth = Style.size(50);
    // Players
    private Player[] players = new Player[2];
    private GameBoard gameBoard;

    public ProgrammaModel() {

    }


    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public void createPlayers(String plr1Name, Color plr1Color, String plr1Flag, String plr2name, Color plr2Color, String plr2Flag) {
        // Empty current players
        players[0] = null;
        players[1] = null;

        // Create the two players
        players[0] = new Player(plr1Name, plr1Color);
        players[1] = new Player(plr2name, plr2Color);

        // Give them all pieces they need, except the flags

        for (Player player : players) {

            String hallo = "hoi";
            System.out.println("player.getPieces() = " + player.getPieces());
            // 6 bombs
            for (int i = 0; i < 6; i++) {
                player.getPieces().add(new Bomb(player));
            }
            
            // 1 marshal
            player.getPieces().add(new Marshal(player));
            
            // 1 general
            player.getPieces().add(new General(player));
            
            // 2 colonels
            for (int i = 0; i < 2; i++) {
                player.getPieces().add(new Colonel(player));
            }

            // 3 majors
            for (int i = 0; i < 3; i++) {
                player.getPieces().add(new Major(player));
            }

            // 4 captains
            for (int i = 0; i < 4; i++) {
                player.getPieces().add(new Captain(player));
            }

            // 4 lieutenants
            for (int i = 0; i < 4; i++) {
                player.getPieces().add(new Lieutenant(player));
            }

            // 4 sergeants
            for (int i = 0; i < 4; i++) {
                player.getPieces().add(new Sergeant(player));
            }

            // 5 miners
            for (int i = 0; i < 5; i++) {
                player.getPieces().add(new Miner(player));
            }

            // 8 scouts
            for (int i = 0; i < 8; i++) {
                player.getPieces().add(new Scout(player));
            }

            // 1 Spy
            player.getPieces().add(new Spy(player));
        }

        // Give each player their flag
        players[0].getPieces().add(new Flag(players[0], plr1Flag));
        players[1].getPieces().add(new Flag(players[1], plr2Flag));
    }

    public void createGameBoard() {
        this.gameBoard = new GameBoard();
        this.InitializeBoard();
    }

    private void InitializeBoard() {
        int boardWidth = gameBoard.getGrootteX();
        int boardHeight = gameBoard.getGrootteY();

        int amountOfRowsPerPlayer = (boardHeight - 2) / 2;

        //// Add empty rows on top
        for (int posY = 0; posY < amountOfRowsPerPlayer; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }

        //// Add paths & swamps
        ////// Paths
        for (int posY = amountOfRowsPerPlayer; posY < amountOfRowsPerPlayer + 2; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }


        //// Swamps
        ////// Left swamp
        gameBoard.setSpeelveld(new Speelveld("swampx2y4", 2, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 3, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 2, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 3, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        ////// Right swamp
        gameBoard.setSpeelveld(new Speelveld("swamp", 6, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 7, amountOfRowsPerPlayer, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 6, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));
        gameBoard.setSpeelveld(new Speelveld("swamp", 7, amountOfRowsPerPlayer + 1, FieldType.water(fieldHeight, fieldWidth), false));

        //// Add empty rows on the bottom
        for (int posY = amountOfRowsPerPlayer + 2; posY < boardHeight; posY++) {
            // Per row
            for (int posX = 0; posX < boardWidth; posX++) {
                // Per column
                gameBoard.setSpeelveld(new Speelveld("grass", posX, posY, FieldType.grass(fieldHeight, fieldWidth), true));
            }
        }
    }

    public Player[] getPlayers() {
        return players;
    }
}
