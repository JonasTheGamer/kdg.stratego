package be.kdg.Stratego.Klassen;
import be.kdg.Stratego.CustomUtil.AskUtility;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Game {
    private Speler[] spelers;
    private LocalDateTime startTijd;
    private LocalDateTime eindTijd;
    private Scanner keyboard = new Scanner(System.in);
    private boolean ongoing = false;
    private Speelbord speelbord;

    public Game() {
        this.speelbord = new Speelbord();
    }

    public Speler[] getSpelers() {
        return spelers;
    }

    public void setSpelers(Speler[] spelers) {
        this.spelers = spelers;
    }

    public LocalDateTime getStartTijd() {
        return startTijd;
    }

    public void setStartTijd(LocalDateTime startTijd) {
        this.startTijd = startTijd;
    }

    public LocalDateTime getEindTijd() {
        return eindTijd;
    }

    public void setEindTijd(LocalDateTime eindTijd) {
        this.eindTijd = eindTijd;
    }

    public void start() {
        ongoing = true;
        this.startTijd = LocalDateTime.now();
    }

    public boolean opslaan() {
        // Check to allow for a loop that keeps asking for a valid file location
        boolean fileOk = false;
        boolean fileSaved = false;

        // Check if the game has already been started
        if(!ongoing) {
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
        };

        // Let the saving begin!
        try {
            PrintWriter writer = new PrintWriter(filePath, StandardCharsets.UTF_8);
            writer.println("Hello, world!");
            writer.println("The writing works, woohoo!");
            writer.close();

            System.out.println("The game's progress has been saved successfully. Congratulations!");
            fileSaved = true;

        } catch (FileNotFoundException | UnsupportedEncodingException exc){
            System.out.println("Something went wrong whilst trying to save your progress. Please try again at a later time.");
            fileSaved = false;
        } catch (IOException e) {
            e.printStackTrace();
            fileSaved = false;
        }

        if(fileSaved) {
            // Stop the game when it's saved.
            this.stop();
        }
        return fileSaved;
    }

    public void hervat(Speelbord speelbord) {
        // Resume the timer
    }

    public void stop() {
        ongoing = false;
        this.eindTijd = LocalDateTime.now();
        System.out.println("The game has been stopped.");
    }
}
